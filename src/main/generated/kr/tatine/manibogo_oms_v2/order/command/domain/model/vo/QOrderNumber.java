package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderNumber is a Querydsl query type for OrderNumber
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOrderNumber extends BeanPath<OrderNumber> {

    private static final long serialVersionUID = -843397741L;

    public static final QOrderNumber orderNumber1 = new QOrderNumber("orderNumber1");

    public final StringPath orderNumber = createString("orderNumber");

    public QOrderNumber(String variable) {
        super(OrderNumber.class, forVariable(variable));
    }

    public QOrderNumber(Path<? extends OrderNumber> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderNumber(PathMetadata metadata) {
        super(OrderNumber.class, metadata);
    }

}

