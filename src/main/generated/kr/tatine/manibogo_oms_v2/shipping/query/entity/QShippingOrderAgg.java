package kr.tatine.manibogo_oms_v2.shipping.query.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShippingOrderAgg is a Querydsl query type for ShippingOrderAgg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShippingOrderAgg extends EntityPathBase<ShippingOrderAgg> {

    private static final long serialVersionUID = -644139903L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShippingOrderAgg shippingOrderAgg = new QShippingOrderAgg("shippingOrderAgg");

    public final kr.tatine.manibogo_oms_v2.common.model.QOrderNumber primaryOrderNumber;

    public final StringPath primaryOrderProduct = createString("primaryOrderProduct");

    public final NumberPath<Integer> primaryOrderQuantity = createNumber("primaryOrderQuantity", Integer.class);

    public final EnumPath<kr.tatine.manibogo_oms_v2.common.model.OrderState> primaryOrderState = createEnum("primaryOrderState", kr.tatine.manibogo_oms_v2.common.model.OrderState.class);

    public final kr.tatine.manibogo_oms_v2.common.model.QShippingNumber shippingNumber;

    public final NumberPath<Integer> totalOrderCount = createNumber("totalOrderCount", Integer.class);

    public final NumberPath<Integer> totalOrderQuantity = createNumber("totalOrderQuantity", Integer.class);

    public QShippingOrderAgg(String variable) {
        this(ShippingOrderAgg.class, forVariable(variable), INITS);
    }

    public QShippingOrderAgg(Path<? extends ShippingOrderAgg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShippingOrderAgg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShippingOrderAgg(PathMetadata metadata, PathInits inits) {
        this(ShippingOrderAgg.class, metadata, inits);
    }

    public QShippingOrderAgg(Class<? extends ShippingOrderAgg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.primaryOrderNumber = inits.isInitialized("primaryOrderNumber") ? new kr.tatine.manibogo_oms_v2.common.model.QOrderNumber(forProperty("primaryOrderNumber")) : null;
        this.shippingNumber = inits.isInitialized("shippingNumber") ? new kr.tatine.manibogo_oms_v2.common.model.QShippingNumber(forProperty("shippingNumber")) : null;
    }

}

