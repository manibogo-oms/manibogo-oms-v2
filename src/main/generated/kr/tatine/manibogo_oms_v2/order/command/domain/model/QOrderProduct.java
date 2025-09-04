package kr.tatine.manibogo_oms_v2.order.command.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderProduct is a Querydsl query type for OrderProduct
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOrderProduct extends BeanPath<OrderProduct> {

    private static final long serialVersionUID = 1255087954L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderProduct orderProduct = new QOrderProduct("orderProduct");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final kr.tatine.manibogo_oms_v2.common.model.QOption option1;

    public final kr.tatine.manibogo_oms_v2.common.model.QOption option2;

    public final kr.tatine.manibogo_oms_v2.common.model.QOption option3;

    public final kr.tatine.manibogo_oms_v2.common.model.QMoney price;

    public final kr.tatine.manibogo_oms_v2.product.command.domain.QProductNumber productNumber;

    public QOrderProduct(String variable) {
        this(OrderProduct.class, forVariable(variable), INITS);
    }

    public QOrderProduct(Path<? extends OrderProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderProduct(PathMetadata metadata, PathInits inits) {
        this(OrderProduct.class, metadata, inits);
    }

    public QOrderProduct(Class<? extends OrderProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.option1 = inits.isInitialized("option1") ? new kr.tatine.manibogo_oms_v2.common.model.QOption(forProperty("option1")) : null;
        this.option2 = inits.isInitialized("option2") ? new kr.tatine.manibogo_oms_v2.common.model.QOption(forProperty("option2")) : null;
        this.option3 = inits.isInitialized("option3") ? new kr.tatine.manibogo_oms_v2.common.model.QOption(forProperty("option3")) : null;
        this.price = inits.isInitialized("price") ? new kr.tatine.manibogo_oms_v2.common.model.QMoney(forProperty("price")) : null;
        this.productNumber = inits.isInitialized("productNumber") ? new kr.tatine.manibogo_oms_v2.product.command.domain.QProductNumber(forProperty("productNumber")) : null;
    }

}

