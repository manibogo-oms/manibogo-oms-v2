package kr.tatine.manibogo_oms_v2.location.domain.juso;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJusoIntegration is a Querydsl query type for JusoIntegration
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJusoIntegration extends EntityPathBase<JusoIntegration> {

    private static final long serialVersionUID = 627023026L;

    public static final QJusoIntegration jusoIntegration = new QJusoIntegration("jusoIntegration");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> integratedOn = createDate("integratedOn", java.time.LocalDate.class);

    public final StringPath resultCode = createString("resultCode");

    public final StringPath resultMessage = createString("resultMessage");

    public QJusoIntegration(String variable) {
        super(JusoIntegration.class, forVariable(variable));
    }

    public QJusoIntegration(Path<? extends JusoIntegration> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJusoIntegration(PathMetadata metadata) {
        super(JusoIntegration.class, metadata);
    }

}

