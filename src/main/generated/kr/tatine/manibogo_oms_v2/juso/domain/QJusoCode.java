package kr.tatine.manibogo_oms_v2.juso.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJusoCode is a Querydsl query type for JusoCode
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QJusoCode extends BeanPath<JusoCode> {

    private static final long serialVersionUID = 426841522L;

    public static final QJusoCode jusoCode = new QJusoCode("jusoCode");

    public final StringPath code = createString("code");

    public QJusoCode(String variable) {
        super(JusoCode.class, forVariable(variable));
    }

    public QJusoCode(Path<? extends JusoCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJusoCode(PathMetadata metadata) {
        super(JusoCode.class, metadata);
    }

}

