package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShippingBundleNumber is a Querydsl query type for ShippingBundleNumber
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QShippingBundleNumber extends BeanPath<ShippingBundleNumber> {

    private static final long serialVersionUID = 835092426L;

    public static final QShippingBundleNumber shippingBundleNumber1 = new QShippingBundleNumber("shippingBundleNumber1");

    public final StringPath shippingBundleNumber = createString("shippingBundleNumber");

    public QShippingBundleNumber(String variable) {
        super(ShippingBundleNumber.class, forVariable(variable));
    }

    public QShippingBundleNumber(Path<? extends ShippingBundleNumber> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShippingBundleNumber(PathMetadata metadata) {
        super(ShippingBundleNumber.class, metadata);
    }

}

