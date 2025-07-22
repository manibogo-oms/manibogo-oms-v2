package kr.tatine.manibogo_oms_v2.logistics.query.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLogisticsDto is a Querydsl query type for LogisticsDto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLogisticsDto extends EntityPathBase<LogisticsDto> {

    private static final long serialVersionUID = 1481168887L;

    public static final QLogisticsDto logisticsDto = new QLogisticsDto("logisticsDto");

    public final NumberPath<Integer> cancelledCount = createNumber("cancelledCount", Integer.class);

    public final StringPath customerMessage = createString("customerMessage");

    public final StringPath customerName = createString("customerName");

    public final StringPath customerTel = createString("customerTel");

    public final NumberPath<Integer> dispatchedCount = createNumber("dispatchedCount", Integer.class);

    public final NumberPath<Integer> purchasedCount = createNumber("purchasedCount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> recentlyPlacedAt = createDateTime("recentlyPlacedAt", java.time.LocalDateTime.class);

    public final StringPath recipientAddr1 = createString("recipientAddr1");

    public final StringPath recipientName = createString("recipientName");

    public final StringPath recipientTel1 = createString("recipientTel1");

    public final StringPath recipientTel2 = createString("recipientTel2");

    public final NumberPath<Integer> refundedCount = createNumber("refundedCount", Integer.class);

    public final NumberPath<Integer> shippedCount = createNumber("shippedCount", Integer.class);

    public final StringPath shippingBundleNumber = createString("shippingBundleNumber");

    public final StringPath shippingMemo = createString("shippingMemo");

    public final StringPath sido = createString("sido");

    public final StringPath sigungu = createString("sigungu");

    public QLogisticsDto(String variable) {
        super(LogisticsDto.class, forVariable(variable));
    }

    public QLogisticsDto(Path<? extends LogisticsDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLogisticsDto(PathMetadata metadata) {
        super(LogisticsDto.class, metadata);
    }

}

