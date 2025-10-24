package kr.tatine.manibogo_oms_v2.shipping.ui.dto.in;

import kr.tatine.manibogo_oms_v2.common.model.ShippingMethod;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingState;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.in.DetailSearchType;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.in.ShippingQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@NoArgsConstructor
public class ShippingQueryForm {

    private ShippingState shippingState;

    private ShippingMethod shippingMethod;

    private DetailSearchType detailSearchType;

    private String keyword;

    private String sido;

    private String sigungu;

    public ShippingQuery toQuery() {
        return new ShippingQuery(
                detailSearchType, keyword, shippingState, shippingMethod, sido, sigungu);
    }

}
