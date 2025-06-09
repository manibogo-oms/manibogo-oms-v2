package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPriority is a Querydsl query type for Priority
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPriority extends BeanPath<Priority> {

    private static final long serialVersionUID = 277529727L;

    public static final QPriority priority1 = new QPriority("priority1");

    public final NumberPath<Integer> priority = createNumber("priority", Integer.class);

    public QPriority(String variable) {
        super(Priority.class, forVariable(variable));
    }

    public QPriority(Path<? extends Priority> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPriority(PathMetadata metadata) {
        super(Priority.class, metadata);
    }

}

