package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFulfillmentDto is a Querydsl query type for FulfillmentDto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFulfillmentDto extends EntityPathBase<FulfillmentDto> {

    private static final long serialVersionUID = -904599199L;

    public static final QFulfillmentDto fulfillmentDto = new QFulfillmentDto("fulfillmentDto");

    public final StringPath adminMemo = createString("adminMemo");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DatePath<java.time.LocalDate> cancelledOn = createDate("cancelledOn", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> confirmedOn = createDate("confirmedOn", java.time.LocalDate.class);

    public final StringPath customerMemo = createString("customerMemo");

    public final StringPath customerName = createString("customerName");

    public final DatePath<java.time.LocalDate> dispatchDeadline = createDate("dispatchDeadline", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> dispatchedOn = createDate("dispatchedOn", java.time.LocalDate.class);

    public final NumberPath<Integer> itemOrderBundleCount = createNumber("itemOrderBundleCount", Integer.class);

    public final StringPath itemOrderNumber = createString("itemOrderNumber");

    public final EnumPath<kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState> itemOrderState = createEnum("itemOrderState", kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState.class);

    public final StringPath optionInfo = createString("optionInfo");

    public final StringPath orderNumber = createString("orderNumber");

    public final DatePath<java.time.LocalDate> placedOn = createDate("placedOn", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> preferredShipsOn = createDate("preferredShipsOn", java.time.LocalDate.class);

    public final StringPath productName = createString("productName");

    public final DatePath<java.time.LocalDate> purchasedOn = createDate("purchasedOn", java.time.LocalDate.class);

    public final StringPath purchaseMemo = createString("purchaseMemo");

    public final StringPath recipientName = createString("recipientName");

    public final DatePath<java.time.LocalDate> refundedOn = createDate("refundedOn", java.time.LocalDate.class);

    public final EnumPath<kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.SalesChannel> salesChannel = createEnum("salesChannel", kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.SalesChannel.class);

    public final DatePath<java.time.LocalDate> shippedOn = createDate("shippedOn", java.time.LocalDate.class);

    public final StringPath shippingMemo = createString("shippingMemo");

    public final StringPath shippingRegionName = createString("shippingRegionName");

    public final StringPath shippingTrackingNumber = createString("shippingTrackingNumber");

    public QFulfillmentDto(String variable) {
        super(FulfillmentDto.class, forVariable(variable));
    }

    public QFulfillmentDto(Path<? extends FulfillmentDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFulfillmentDto(PathMetadata metadata) {
        super(FulfillmentDto.class, metadata);
    }

}

