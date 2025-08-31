package kr.tatine.manibogo_oms_v2.common.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShippingNumber is a Querydsl query type for ShippingNumber
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QShippingNumber extends BeanPath<ShippingNumber> {

    private static final long serialVersionUID = -203059036L;

    public static final QShippingNumber shippingNumber1 = new QShippingNumber("shippingNumber1");

    public final StringPath shippingNumber = createString("shippingNumber");

    public QShippingNumber(String variable) {
        super(ShippingNumber.class, forVariable(variable));
    }

    public QShippingNumber(Path<? extends ShippingNumber> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShippingNumber(PathMetadata metadata) {
        super(ShippingNumber.class, metadata);
    }

}

