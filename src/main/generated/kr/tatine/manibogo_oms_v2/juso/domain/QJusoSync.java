package kr.tatine.manibogo_oms_v2.juso.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJusoSync is a Querydsl query type for JusoSync
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJusoSync extends EntityPathBase<JusoSync> {

    private static final long serialVersionUID = 427328096L;

    public static final QJusoSync jusoSync = new QJusoSync("jusoSync");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> referenceTime = createDateTime("referenceTime", java.time.LocalDateTime.class);

    public final StringPath resultCode = createString("resultCode");

    public final StringPath resultMessage = createString("resultMessage");

    public QJusoSync(String variable) {
        super(JusoSync.class, forVariable(variable));
    }

    public QJusoSync(Path<? extends JusoSync> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJusoSync(PathMetadata metadata) {
        super(JusoSync.class, metadata);
    }

}

