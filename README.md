## 도메인 모델
<img width="9380" height="7140" alt="image (3)" src="https://github.com/user-attachments/assets/f3493b51-630c-47be-bc11-b29d29ebe8e7" />

- 도메인 이벤트로 중복 로직을 제거하고, 수정 시 트랜잭션 락으로 인한 블로킹 시간을 최소화헀습니다.
    - 주문 상태 변경 시 이벤트로 로깅을 생성해, 인증 정보 등 로직에 불필요한 데이터를 인터페이스에서 분리했습니다.
    - 합배송 처리된 주문 정보를 수정할 시, 합배송된 타 주문의 정보를 비동기로 수정했습니다. (예정)
- **CQRS**로 복잡한 조회 로직(검색, 쿼리, 정렬)을 분리하여 개발 편의성 및 유지보수성을 높였습니다.
    - 데이터 동기화의 복잡성을 줄이기 위해 단일 데이터베이스 내에서 CQRS를 구성했습니다.
    - N+1 문제와 지연 로딩 등 복잡한 이슈를 해결하고, JPQL의 제한을 극복하기 위해 복잡한 조회 로직에 Native SQL을 사용했습니다.

 

## 인증 아키텍처
<img width="5564" height="3564" alt="image" src="https://github.com/user-attachments/assets/e7347244-4c12-4ae3-87c0-cdcf4941a2e0" />

고객 개인정보를 안전하게 보호하기 위해 Spring Security와 세션 기반 인증 방식을 도입했습니다. 기본적으로 CSRF, XSS 공격 등을 보호할 수 있고, Server-Side Rendering 환경에서 빠르고 간편하게 개발할 수 있기 때문입니다.
- 자동 로그인 토큰을 Persistent Token 방식으로 저장 및 관리했습니다.
    - 조회 성능이 높고 TTL(Time-To-Live) 설정이 간편한 Redis를 활용했습니다.
    - 해시 방식과 달리 로그인 시 토큰을 Rotation해 탈취 위협을 줄이고, 유효 기간을 유동적으로 관리할 수 있습니다.
- 서비스의 확장성과 고가용성을 고려해 Redis로 세션 저장소를 분리했습니다.
    - 향후 스케일 아웃 및 무중단 배포를 계획하고 있어, WAS 내부에 세션을 보관하는 기본 방식은 세션 불일치 문제를 야기할 수 있기 때문입니다.
 

 ## 인프라 아키텍처
<img width="5364" height="4444" alt="image (1)" src="https://github.com/user-attachments/assets/2393078a-9105-4d39-8d30-3f0b44cd59c6" />

 한정된 예산 안에서 단일 가용 영역(AZ) 장애 시 대처하는 목표로 고가용성을 확보했습니다.
1. **Web Application Server + Web Server(Spring Boot)**
    - L7 로드밸런서와 **Auto Scaling 그룹**을 활용하여 Spring 노드를 2개 AZ에 분산 배치했습니다.
    - CloudWatch 커스텀 지표를 수집해, 활성 스레드 수에 따라 최대 4개의 인스턴스로 **자동 스케일 아웃**되도록 구성할 예정입니다.
2. **Relational Database(MariaDB)**
    - MHA를 도입해 Master 장애 시 Replica를 자동 승격하는 Failover를 구성했습니다.
        - 1, 2번 AZ에 RDB Replica 노드를 분산 배치하고, 3번 AZ에 MHA Manager를 구성했습니다.
    - AWS SDN 환경의 제약으로 L2 기반의 VIP를 사용할 수 없기에, VPC **라우트 테이블에 VIP를 등록**하여 Master 노드로 동적 라우팅하도록 했습니다.
    - Failover 시 AWS CLI를 통해 라우트 대상을 신규 Master 노드로 변경하여 무중단 전환을 구현했습니다.
3. **In-Memory Database(Redis)**
    - Redis Sentinel을 활용해 Redis 노드의 고가용성을 확보했습니다.
    - 한정된 예산에 맞추기 위해 1, 2번 AZ에 Replica와 Sentinel 노드, 3번 AZ에는 Sentinel 노드를 단독 배치해 Quorum을 구성헀습니다.
  

## 무중단 배포 아키텍처
<img width="3812" height="3324" alt="image (2)" src="https://github.com/user-attachments/assets/97a62474-371e-478c-9e52-7700d04bf8bc" />

배송팀의 다운타임을 최소화하고, 운영 환경에서 잦은 피드백에 대응하기 위해 `Blue-Green` 무중단 배포를 도입했습니다.
- 개발 환경과 인프라를 분리하고, 벤더 호환성이 우수한 `CodePipeline` 라인을 사용했습니다.
- `Green` 그룹은 즉시 배포하고, 롤백에 대비해 `Blue` 그룹은 약 `2일` 대기 후 삭제했습니다.
