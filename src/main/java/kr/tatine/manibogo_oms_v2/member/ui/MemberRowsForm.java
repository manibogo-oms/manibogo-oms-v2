package kr.tatine.manibogo_oms_v2.member.ui;

import kr.tatine.manibogo_oms_v2.common.model.SelectableRow;
import kr.tatine.manibogo_oms_v2.common.model.SelectableRowsForm;
import kr.tatine.manibogo_oms_v2.member.query.MemberDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberRowsForm implements SelectableRowsForm<MemberRowsForm.Row> {

    private List<Row> rows = new ArrayList<>();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class Row implements SelectableRow {

        private Boolean isSelected = false;

        private String username;

        private String role;

        private String newPassword;

        private String repeatNewPassword;

        private Boolean isEnabled;

        public static MemberRowsForm.Row of(MemberDto memberDto) {
            final Row row = new Row();

            row.setUsername(memberDto.getUsername());
            row.setRole(memberDto.getRole());
            row.setIsEnabled(memberDto.getIsEnabled());

            return row;
        }

    }

}
