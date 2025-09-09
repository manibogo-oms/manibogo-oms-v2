package kr.tatine.manibogo_oms_v2.shipping.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShippingOrderAggView is a Querydsl query type for ShippingOrderAggView
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShippingOrderAggView extends EntityPathBase<ShippingOrderAggView> {

    private static final long serialVersionUID = 290718225L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShippingOrderAggView shippingOrderAggView = new QShippingOrderAggView("shippingOrderAggView");

    public final EnumPath<kr.tatine.manibogo_oms_v2.common.model.OrderState> primaryOrderState = createEnum("primaryOrderState", kr.tatine.manibogo_oms_v2.common.model.OrderState.class);

    public final StringPath primaryProductName = createString("primaryProductName");

    public final NumberPath<Integer> primaryProductQuantity = createNumber("primaryProductQuantity", Integer.class);

    public final kr.tatine.manibogo_oms_v2.common.model.QShippingNumber shippingNumber;

    public final NumberPath<Integer> totalOrderCount = createNumber("totalOrderCount", Integer.class);

    public final NumberPath<Integer> totalQuantity = createNumber("totalQuantity", Integer.class);

    public QShippingOrderAggView(String variable) {
        this(ShippingOrderAggView.class, forVariable(variable), INITS);
    }

    public QShippingOrderAggView(Path<? extends ShippingOrderAggView> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShippingOrderAggView(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShippingOrderAggView(PathMetadata metadata, PathInits inits) {
        this(ShippingOrderAggView.class, metadata, inits);
    }

    public QShippingOrderAggView(Class<? extends ShippingOrderAggView> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shippingNumber = inits.isInitialized("shippingNumber") ? new kr.tatine.manibogo_oms_v2.common.model.QShippingNumber(forProperty("shippingNumber")) : null;
    }

}

