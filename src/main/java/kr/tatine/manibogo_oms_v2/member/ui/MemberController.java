package kr.tatine.manibogo_oms_v2.member.ui;

import kr.tatine.manibogo_oms_v2.member.command.domain.Role;
import kr.tatine.manibogo_oms_v2.member.query.MemberDao;
import kr.tatine.manibogo_oms_v2.member.query.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v2/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberDao memberDao;

    @ModelAttribute("roles")
    public Role[] roles() {
        return Role.values();
    }

    @GetMapping
    @Transactional(readOnly = true)
    public String members(Model model, @PageableDefault Pageable pageable) {

        final Page<MemberDto> page = memberDao.findAll(pageable);

        final MemberRowsForm rowsForm = new MemberRowsForm();
        rowsForm.setRows(page.getContent().stream().map(MemberRowsForm.Row::of).toList());

        model.addAttribute("rowsForm", rowsForm);
        model.addAttribute("page", page);

        return "members";
    }

}
