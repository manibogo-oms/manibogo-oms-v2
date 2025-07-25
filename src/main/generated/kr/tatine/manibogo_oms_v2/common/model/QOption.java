package kr.tatine.manibogo_oms_v2.common.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOption is a Querydsl query type for Option
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOption extends BeanPath<Option> {

    private static final long serialVersionUID = -1282520190L;

    public static final QOption option = new QOption("option");

    public final StringPath key = createString("key");

    public final StringPath value = createString("value");

    public QOption(String variable) {
        super(Option.class, forVariable(variable));
    }

    public QOption(Path<? extends Option> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOption(PathMetadata metadata) {
        super(Option.class, metadata);
    }

}

