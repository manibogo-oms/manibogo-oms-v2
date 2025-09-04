package kr.tatine.manibogo_oms_v2.member.ui;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ui.CommonResponse;
import kr.tatine.manibogo_oms_v2.common.ui.ErrorResult;
import kr.tatine.manibogo_oms_v2.common.utils.SelectableRowsFormUtils;
import kr.tatine.manibogo_oms_v2.member.command.application.EditMemberService;
import kr.tatine.manibogo_oms_v2.member.command.application.MemberNotFoundException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static kr.tatine.manibogo_oms_v2.common.utils.SelectableRowsFormUtils.getRowsFieldName;

@Controller
@RequestMapping("/v2/members")
@RequiredArgsConstructor
public class MemberController {

    private final EditMemberService editMemberService;

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

    @PostMapping("/edit")
    public String editMembers(@ModelAttribute("rowsForm") MemberRowsForm rowsForm, RedirectAttributes redirectAttributes) {

        final ErrorResult errorResult = new ErrorResult();

        SelectableRowsFormUtils.handle(rowsForm, errorResult, (i, row) -> {
            try {
                editMemberService.edit(row.toCommand());

            } catch (ValidationErrorException ex) {
                ex.getValidationErrors().forEach(error ->
                        errorResult.rejectValue(getRowsFieldName(i, error.getName()), error.getErrorCode()));

            } catch (MemberNotFoundException ex) {
                errorResult.reject("notFound.member");
            }
        });


        redirectAttributes.addFlashAttribute("response",
                new CommonResponse("complete.editMembers", errorResult));

        return "redirect:/v2/members";
    }

}
