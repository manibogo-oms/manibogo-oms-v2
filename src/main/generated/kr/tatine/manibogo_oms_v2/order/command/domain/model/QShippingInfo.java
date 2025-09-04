package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShippingInfo is a Querydsl query type for ShippingInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QShippingInfo extends BeanPath<ShippingInfo> {

    private static final long serialVersionUID = 275752205L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShippingInfo shippingInfo = new QShippingInfo("shippingInfo");

    public final EnumPath<kr.tatine.manibogo_oms_v2.common.model.ChargeType> chargeType = createEnum("chargeType", kr.tatine.manibogo_oms_v2.common.model.ChargeType.class);

    public final EnumPath<kr.tatine.manibogo_oms_v2.common.model.ShippingMethod> method = createEnum("method", kr.tatine.manibogo_oms_v2.common.model.ShippingMethod.class);

    public final kr.tatine.manibogo_oms_v2.common.model.QShippingNumber shippingBundleNumber;

    public QShippingInfo(String variable) {
        this(ShippingInfo.class, forVariable(variable), INITS);
    }

    public QShippingInfo(Path<? extends ShippingInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShippingInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShippingInfo(PathMetadata metadata, PathInits inits) {
        this(ShippingInfo.class, metadata, inits);
    }

    public QShippingInfo(Class<? extends ShippingInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shippingBundleNumber = inits.isInitialized("shippingBundleNumber") ? new kr.tatine.manibogo_oms_v2.common.model.QShippingNumber(forProperty("shippingBundleNumber")) : null;
    }

}

