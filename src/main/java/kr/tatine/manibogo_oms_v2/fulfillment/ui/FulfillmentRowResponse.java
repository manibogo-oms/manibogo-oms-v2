package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FulfillmentRowResponse {

    private Map<String, FulfillmentRowResult> resultMap = new HashMap<>();

    public void addResult(String itemOrderNumber, FulfillmentRowResult result) {
        resultMap.put(itemOrderNumber, result);
    }

    public FulfillmentRowResult getResult(String itemOrderNumber) {
        return resultMap.get(itemOrderNumber);
    }

}
