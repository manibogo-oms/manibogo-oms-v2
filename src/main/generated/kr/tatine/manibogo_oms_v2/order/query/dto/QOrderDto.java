package kr.tatine.manibogo_oms_v2.order.query.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderDto is a Querydsl query type for OrderDto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderDto extends EntityPathBase<OrderDto> {

    private static final long serialVersionUID = -1452729663L;

    public static final QOrderDto orderDto = new QOrderDto("orderDto");

    public final StringPath adminMemo = createString("adminMemo");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> cancelledAt = createDateTime("cancelledAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> confirmedAt = createDateTime("confirmedAt", java.time.LocalDateTime.class);

    public final StringPath customerMessage = createString("customerMessage");

    public final StringPath customerName = createString("customerName");

    public final StringPath customerTel = createString("customerTel");

    public final DatePath<java.time.LocalDate> dispatchDeadline = createDate("dispatchDeadline", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> dispatchedAt = createDateTime("dispatchedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> finalPrice = createNumber("finalPrice", Long.class);

    public final StringPath optionKey1 = createString("optionKey1");

    public final StringPath optionKey2 = createString("optionKey2");

    public final StringPath optionKey3 = createString("optionKey3");

    public final StringPath optionLabel1 = createString("optionLabel1");

    public final StringPath optionLabel2 = createString("optionLabel2");

    public final StringPath optionLabel3 = createString("optionLabel3");

    public final StringPath orderNumber = createString("orderNumber");

    public final EnumPath<kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState> orderState = createEnum("orderState", kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState.class);

    public final DateTimePath<java.time.LocalDateTime> placedAt = createDateTime("placedAt", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> preferredShippingDate = createDate("preferredShippingDate", java.time.LocalDate.class);

    public final StringPath productName = createString("productName");

    public final StringPath productNumber = createString("productNumber");

    public final DateTimePath<java.time.LocalDateTime> purchasedAt = createDateTime("purchasedAt", java.time.LocalDateTime.class);

    public final StringPath purchaseMemo = createString("purchaseMemo");

    public final StringPath recipientAddr1 = createString("recipientAddr1");

    public final StringPath recipientAddr2 = createString("recipientAddr2");

    public final StringPath recipientName = createString("recipientName");

    public final StringPath recipientTel1 = createString("recipientTel1");

    public final StringPath recipientTel2 = createString("recipientTel2");

    public final StringPath recipientZipCode = createString("recipientZipCode");

    public final DateTimePath<java.time.LocalDateTime> refundedAt = createDateTime("refundedAt", java.time.LocalDateTime.class);

    public final EnumPath<kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel> salesChannel = createEnum("salesChannel", kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel.class);

    public final DateTimePath<java.time.LocalDateTime> shippedAt = createDateTime("shippedAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> shippingBundleCount = createNumber("shippingBundleCount", Integer.class);

    public final StringPath shippingBundleNumber = createString("shippingBundleNumber");

    public final EnumPath<kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType> shippingChargeType = createEnum("shippingChargeType", kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType.class);

    public final StringPath shippingCompanyName = createString("shippingCompanyName");

    public final StringPath shippingMemo = createString("shippingMemo");

    public final EnumPath<kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod> shippingMethod = createEnum("shippingMethod", kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod.class);

    public final StringPath shippingTrackingNumber = createString("shippingTrackingNumber");

    public final StringPath sido = createString("sido");

    public final StringPath sigungu = createString("sigungu");

    public QOrderDto(String variable) {
        super(OrderDto.class, forVariable(variable));
    }

    public QOrderDto(Path<? extends OrderDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderDto(PathMetadata metadata) {
        super(OrderDto.class, metadata);
    }

}

