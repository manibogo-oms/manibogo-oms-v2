package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemOrderNote is a Querydsl query type for ItemOrderNote
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QItemOrderNote extends BeanPath<ItemOrderNote> {

    private static final long serialVersionUID = 659637593L;

    public static final QItemOrderNote itemOrderNote = new QItemOrderNote("itemOrderNote");

    public final StringPath adminMemo = createString("adminMemo");

    public final StringPath purchaseMemo = createString("purchaseMemo");

    public final StringPath shippingMemo = createString("shippingMemo");

    public QItemOrderNote(String variable) {
        super(ItemOrderNote.class, forVariable(variable));
    }

    public QItemOrderNote(Path<? extends ItemOrderNote> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemOrderNote(PathMetadata metadata) {
        super(ItemOrderNote.class, metadata);
    }

}

