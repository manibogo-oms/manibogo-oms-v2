package kr.tatine.manibogo_oms_v2.juso.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJusoIntegration is a Querydsl query type for JusoIntegration
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJusoIntegration extends EntityPathBase<JusoSync> {

    private static final long serialVersionUID = 650098287L;

    public static final QJusoIntegration jusoIntegration = new QJusoIntegration("jusoIntegration");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> integratedOn = createDate("integratedOn", java.time.LocalDate.class);

    public final ListPath<Juso, QJuso> jusos = this.<Juso, QJuso>createList("jusos", Juso.class, QJuso.class, PathInits.DIRECT2);

    public final StringPath resultCode = createString("resultCode");

    public final StringPath resultMessage = createString("resultMessage");

    public QJusoIntegration(String variable) {
        super(JusoSync.class, forVariable(variable));
    }

    public QJusoIntegration(Path<? extends JusoSync> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJusoIntegration(PathMetadata metadata) {
        super(JusoSync.class, metadata);
    }

}

