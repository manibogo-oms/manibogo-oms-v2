package kr.tatine.manibogo_oms_v2.shipping.command.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDirectShipping is a Querydsl query type for DirectShipping
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDirectShipping extends EntityPathBase<DirectShipping> {

    private static final long serialVersionUID = 834486041L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDirectShipping directShipping = new QDirectShipping("directShipping");

    public final QShipping _super;

    //inherited
    public final EnumPath<kr.tatine.manibogo_oms_v2.common.model.ChargeType> chargeType;

    // inherited
    public final kr.tatine.manibogo_oms_v2.common.model.QShippingNumber number;

    // inherited
    public final kr.tatine.manibogo_oms_v2.common.model.QRecipient recipient;

    //inherited
    public final EnumPath<ShippingState> state;

    public QDirectShipping(String variable) {
        this(DirectShipping.class, forVariable(variable), INITS);
    }

    public QDirectShipping(Path<? extends DirectShipping> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDirectShipping(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDirectShipping(PathMetadata metadata, PathInits inits) {
        this(DirectShipping.class, metadata, inits);
    }

    public QDirectShipping(Class<? extends DirectShipping> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QShipping(type, metadata, inits);
        this.chargeType = _super.chargeType;
        this.number = _super.number;
        this.recipient = _super.recipient;
        this.state = _super.state;
    }

}

