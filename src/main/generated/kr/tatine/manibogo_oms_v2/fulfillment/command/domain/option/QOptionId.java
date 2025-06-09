package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOptionId is a Querydsl query type for OptionId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOptionId extends BeanPath<OptionId> {

    private static final long serialVersionUID = -1318641203L;

    public static final QOptionId optionId = new QOptionId("optionId");

    public final StringPath key = createString("key");

    public final StringPath value = createString("value");

    public QOptionId(String variable) {
        super(OptionId.class, forVariable(variable));
    }

    public QOptionId(Path<? extends OptionId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOptionId(PathMetadata metadata) {
        super(OptionId.class, metadata);
    }

}

