## 개요

경북 소재 침대/매트리스 전문 기업 S*사의 주문 관리 서비스입니다. 네이버 스마트스토어, 매장 등 다양한 채널의 주문을 통합 관리하고, 엑셀 파일을 기반으로 주문 이행을 자동화하기 위해 개발했습니다.



## 배경

기존 OMS 시스템에 연동 문제를 개선하기 위해 시스템을 고도화했습니다.

- **AS-IS:** 스마트스토어 상품, 옵션 데이터를 개발자가 직접 연동
  - 신제품 출시, 제품정보 수정 시 수정이 늦어지거나, 안되는 경우가 많았습니다.
- **TO-BE:** 주문 데이터 시 자동으로 신규 상품, 옵션 데이터를 감지해 동기화

자체 배송팀을 위한 묶음배송 조회 기능을 고도화했습니다.

- **AS-IS:** 주문 데이터 뷰를 그대로 조회, 묶음배송인 경우 직접 필터링해 찾아야 함
- **TO-BE:** 묶음 배송 주문을 하나의 배송 데이터로 통합

우편번호 체계를 분석해, 배송 지역을 기존 `시/도` 레벨에서 `시/군/구` 레벨로 분류했습니다.

- **AS-IS:** 주소 앞 부분을 기반으로 `시/도`  파싱, 관리자가 직접 매핑해줘야 함
- **TO-BE:** 우편번호 기반으로 손쉽게 `시/군/구` 레벨로 자동 분류



## 기술 스택

- **Frontend**
  - **Template Engine:** Thymeleaf
  - **Design System:** Bootstrap5
- **Backend** 
  - **Application:** Spring Boot, Spring Sessions, Spring Security
  - **ORM:**  JPA(Hibernate)
- **Infrastructure** 
  - **Database:** MariaDB, Redis
  - **HA:** MHA(MariaDB), Redis Sentinel(Redis)



## 주요 기능

### 주문/상품관리 기능 
![인트로](https://github.com/user-attachments/assets/81602f02-729b-400a-a12a-2370eed8b935)

- 스마트스토어, 매장 등 여러 채널의 주문을 통합 관리
- 동적 페이징, 검색, 헤더 정렬 기능
- 스마트스토어/협력사 데이터 연동/내보내기
- 자체 매장 주문서 등록



### 배송조회 기능

- 직접배송 상품을 "묶음배송번호" 기준으로 그룹화한 조회 모델 생성
- 추가/반품 주문을 파악할 수 있도록 주문 상태
- 배송 건을 `시/도`와 `시/군/구` 단위로 분류



## 도메인 모델

![도메인 아키텍처](https://github.com/user-attachments/assets/f3493b51-630c-47be-bc11-b29d29ebe8e7)



## 인증 아키텍처

![인증 아키텍처](https://github.com/user-attachments/assets/e7347244-4c12-4ae3-87c0-cdcf4941a2e0)

백오피스 서비스 특성 상 예상 사용자가 적고, SSR 방식과 연동성이 좋은 세션 기반 인증 아키텍처를 채택했습니다.

- 로드 밸런스, 무중단 배포를 위해 세션 저장소를 외부 `Redis` 로 분리하여, `SecurityContext` 불일치 문제를 방지
- 자동 로그인에 `Persistent Token` 방식을 사용, Rotation 기법으로 유연하고 긴 유효 기간과 탈취 시 재사용에 대응



 ## 인프라 아키텍처

![인프라 아키텍처](https://github.com/user-attachments/assets/2393078a-9105-4d39-8d30-3f0b44cd59c6)

 한정된 예산 안에서 단일 가용 영역(AZ) 장애 시 대처하는 목표로 고가용성을 확보했습니다.

- **RDB:** MHA을 활용해 단일 MZ 장애 시 Replica 노드를 자동 승격하는 Failover 구성
- **Redis:** 1, 2번 AZ에 Redis와 Sentinel 노드, 3번 AZ에 Sentinel 노드 구성해 Redis Sentinel Quorum 구성




## 무중단 배포 아키텍처
![무중단 배포 아키텍처](https://github.com/user-attachments/assets/97a62474-371e-478c-9e52-7700d04bf8bc)

배송팀의 다운타임을 최소화하고, 운영 환경에서 잦은 피드백에 대응하기 위해 `Blue-Green` 무중단 배포를 도입했습니다.
- 인프라 환경을 분리하고, 클라우드 벤더 호환성을 위해 `CodePipeline` 을 사용
- 배포 후 롤백을 위해 기존 원본 그룹을 `48시간` 대기 후 삭제
