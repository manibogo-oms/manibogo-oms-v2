package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemOrder is a Querydsl query type for ItemOrder
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemOrder extends EntityPathBase<ItemOrder> {

    private static final long serialVersionUID = -1449441894L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemOrder itemOrder = new QItemOrder("itemOrder");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DatePath<java.time.LocalDate> dispatchDeadline = createDate("dispatchDeadline", java.time.LocalDate.class);

    public final kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QItemOrderNote note;

    public final kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QItemOrderNumber number;

    public final kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QOrderNumber orderNumber;

    public final DatePath<java.time.LocalDate> preferredShipsOn = createDate("preferredShipsOn", java.time.LocalDate.class);

    public final kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.QProductNumber productNumber;

    public final kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QShippingInfo shippingInfo;

    public final EnumPath<kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState> state = createEnum("state", kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState.class);

    public final kr.tatine.manibogo_oms_v2.common.model.QMoney totalPrice;

    public final kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QTrackingInfo trackingInfo;

    public final ListPath<kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.VariantId, kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.QVariantId> variants = this.<kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.VariantId, kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.QVariantId>createList("variants", kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.VariantId.class, kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option.QVariantId.class, PathInits.DIRECT2);

    public QItemOrder(String variable) {
        this(ItemOrder.class, forVariable(variable), INITS);
    }

    public QItemOrder(Path<? extends ItemOrder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemOrder(PathMetadata metadata, PathInits inits) {
        this(ItemOrder.class, metadata, inits);
    }

    public QItemOrder(Class<? extends ItemOrder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.note = inits.isInitialized("note") ? new kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QItemOrderNote(forProperty("note")) : null;
        this.number = inits.isInitialized("number") ? new kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QItemOrderNumber(forProperty("number")) : null;
        this.orderNumber = inits.isInitialized("orderNumber") ? new kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QOrderNumber(forProperty("orderNumber")) : null;
        this.productNumber = inits.isInitialized("productNumber") ? new kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.QProductNumber(forProperty("productNumber")) : null;
        this.shippingInfo = inits.isInitialized("shippingInfo") ? new kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QShippingInfo(forProperty("shippingInfo")) : null;
        this.totalPrice = inits.isInitialized("totalPrice") ? new kr.tatine.manibogo_oms_v2.common.model.QMoney(forProperty("totalPrice")) : null;
        this.trackingInfo = inits.isInitialized("trackingInfo") ? new kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.QTrackingInfo(forProperty("trackingInfo")) : null;
    }

}

