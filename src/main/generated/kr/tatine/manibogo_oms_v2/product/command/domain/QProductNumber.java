package kr.tatine.manibogo_oms_v2.product.command.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductNumber is a Querydsl query type for ProductNumber
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProductNumber extends BeanPath<ProductNumber> {

    private static final long serialVersionUID = -1267927573L;

    public static final QProductNumber productNumber1 = new QProductNumber("productNumber1");

    public final StringPath productNumber = createString("productNumber");

    public QProductNumber(String variable) {
        super(ProductNumber.class, forVariable(variable));
    }

    public QProductNumber(Path<? extends ProductNumber> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductNumber(PathMetadata metadata) {
        super(ProductNumber.class, metadata);
    }

}

