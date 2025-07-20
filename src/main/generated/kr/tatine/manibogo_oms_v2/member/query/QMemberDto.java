package kr.tatine.manibogo_oms_v2.member.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberDto is a Querydsl query type for MemberDto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberDto extends EntityPathBase<MemberDto> {

    private static final long serialVersionUID = 347050054L;

    public static final QMemberDto memberDto = new QMemberDto("memberDto");

    public final BooleanPath isEnabled = createBoolean("isEnabled");

    public final StringPath password = createString("password");

    public final StringPath role = createString("role");

    public final StringPath username = createString("username");

    public QMemberDto(String variable) {
        super(MemberDto.class, forVariable(variable));
    }

    public QMemberDto(Path<? extends MemberDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberDto(PathMetadata metadata) {
        super(MemberDto.class, metadata);
    }

}

