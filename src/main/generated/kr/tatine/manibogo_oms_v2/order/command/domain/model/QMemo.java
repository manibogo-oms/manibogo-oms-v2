package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemo is a Querydsl query type for Memo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemo extends BeanPath<Memo> {

    private static final long serialVersionUID = 1805298635L;

    public static final QMemo memo = new QMemo("memo");

    public final StringPath adminMemo = createString("adminMemo");

    public final StringPath purchaseMemo = createString("purchaseMemo");

    public final StringPath shippingMemo = createString("shippingMemo");

    public QMemo(String variable) {
        super(Memo.class, forVariable(variable));
    }

    public QMemo(Path<? extends Memo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemo(PathMetadata metadata) {
        super(Memo.class, metadata);
    }

}

