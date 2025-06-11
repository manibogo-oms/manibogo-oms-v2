package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCustomer extends BeanPath<Customer> {

    private static final long serialVersionUID = 231139730L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomer customer = new QCustomer("customer");

    public final StringPath message = createString("message");

    public final StringPath name = createString("name");

    public final kr.tatine.manibogo_oms_v2.common.model.QPhoneNumber phoneNumber;

    public QCustomer(String variable) {
        this(Customer.class, forVariable(variable), INITS);
    }

    public QCustomer(Path<? extends Customer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomer(PathMetadata metadata, PathInits inits) {
        this(Customer.class, metadata, inits);
    }

    public QCustomer(Class<? extends Customer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.phoneNumber = inits.isInitialized("phoneNumber") ? new kr.tatine.manibogo_oms_v2.common.model.QPhoneNumber(forProperty("phoneNumber")) : null;
    }

}

