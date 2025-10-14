package kr.tatine.manibogo_oms_v2.common.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipient is a Querydsl query type for Recipient
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRecipient extends BeanPath<Recipient> {

    private static final long serialVersionUID = -555173908L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipient recipient = new QRecipient("recipient");

    public final QAddress address;

    public final StringPath name = createString("name");

    public final QPhoneNumber phoneNumber1;

    public final QPhoneNumber phoneNumber2;

    public QRecipient(String variable) {
        this(Recipient.class, forVariable(variable), INITS);
    }

    public QRecipient(Path<? extends Recipient> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipient(PathMetadata metadata, PathInits inits) {
        this(Recipient.class, metadata, inits);
    }

    public QRecipient(Class<? extends Recipient> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.phoneNumber1 = inits.isInitialized("phoneNumber1") ? new QPhoneNumber(forProperty("phoneNumber1")) : null;
        this.phoneNumber2 = inits.isInitialized("phoneNumber2") ? new QPhoneNumber(forProperty("phoneNumber2")) : null;
    }

}

