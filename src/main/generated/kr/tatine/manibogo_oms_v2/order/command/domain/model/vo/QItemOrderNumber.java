package kr.tatine.manibogo_oms_v2.order.command.domain.model.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemOrderNumber is a Querydsl query type for ItemOrderNumber
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QItemOrderNumber extends BeanPath<ItemOrderNumber> {

    private static final long serialVersionUID = 613108352L;

    public static final QItemOrderNumber itemOrderNumber1 = new QItemOrderNumber("itemOrderNumber1");

    public final StringPath itemOrderNumber = createString("itemOrderNumber");

    public QItemOrderNumber(String variable) {
        super(ItemOrderNumber.class, forVariable(variable));
    }

    public QItemOrderNumber(Path<? extends ItemOrderNumber> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemOrderNumber(PathMetadata metadata) {
        super(ItemOrderNumber.class, metadata);
    }

}

