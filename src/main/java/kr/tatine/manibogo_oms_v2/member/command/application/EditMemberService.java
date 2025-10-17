package kr.tatine.manibogo_oms_v2.member.command.application;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ValidationError;
import kr.tatine.manibogo_oms_v2.member.command.domain.Member;
import kr.tatine.manibogo_oms_v2.member.command.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EditMemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void edit(EditMemberCommand command) {

        final List<ValidationError> errors = new ArrayList<>();

        if (Strings.isBlank(command.username())) {
            errors.add(ValidationError.of("username", "required.username"));
        }
        if (Strings.isNotBlank(command.password())) {

            if (!Objects.equals(command.password(), command.repeatPassword())) {
                errors.add(ValidationError.of("repeatPassword", "notMatched.repeatPassword"));
            }
            if (command.password().length() > 4) {
                errors.add(ValidationError.of("password", "length.password"));
            }
        }

        if (!errors.isEmpty()) {
            throw new ValidationErrorException(errors);
        }

        final Member member = memberRepository.findById(command.username())
                .orElseThrow(MemberNotFoundException::new);

        member.changePassword(passwordEncoder.encode(command.password()));
    }

}
