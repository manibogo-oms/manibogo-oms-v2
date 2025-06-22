package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseOrderDto {

    @JsonProperty("번호")
    private int sequence;

    @JsonProperty("상품주문번호")
    private String itemOrderNumber;

    @JsonProperty("구매자명")
    private String customerName;

    @JsonProperty("구매자연락처")
    private String customerPhoneNumber;

    @JsonProperty("수취인명")
    private String recipientName;

    @JsonProperty("수취인연락처1")
    private String recipientPhoneNumber1;

    @JsonProperty("수취인연락처2")
    private String recipientPhoneNumber2;

    @JsonProperty("상품명")
    private String productName;

    @JsonProperty("옵션1")
    private String option1;

    @JsonProperty("옵션2")
    private String option2;

    @JsonProperty("옵션3")
    private String option3;

    @JsonProperty("합배송수")
    private int itemOrderBundleCount;

    @JsonProperty("수량")
    private int amount;

    @JsonProperty("지역")
    private String shippingRegionName;

    @JsonProperty("주소")
    private String recipientFullAddress;

    @JsonProperty("발송기한")
    private LocalDate dispatchDeadline;

    @JsonProperty("희망배송일")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate preferredShipsOn;

    @JsonProperty("발주일")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchasedOn;

    @JsonProperty("출고일")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dispatchedOn;

    @JsonProperty("배송일")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shippedOn;

    @JsonProperty("배송방법")
    private String shippingMethod;

    @JsonProperty("배송비")
    private String shippingChargeType;

    @JsonProperty("택배사")
    private String shippingCompany;

    @JsonProperty("송장번호")
    private String shippingTrackingNumber;

    @JsonProperty("고객메모")
    private String customerMemo;

    @JsonProperty("발주메모")
    private String purchaseMemo;

    @JsonProperty("상태")
    private String itemOrderState;

    public static List<PurchaseOrderDto> FromFulfillmentDtoList(List<FulfillmentDto> fulfillmentDtoList) {

        final List<PurchaseOrderDto> purchaseOrders = new ArrayList<>();

        for (int i = 0; i < fulfillmentDtoList.size(); i ++) {
            purchaseOrders.add(fromFulfillmentDto(i, fulfillmentDtoList.get(i)));

        }

        return purchaseOrders;
    }

    private static PurchaseOrderDto fromFulfillmentDto(int index, FulfillmentDto fulfillmentDto) {
        PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();

        purchaseOrderDto.setSequence(index + 1);
        purchaseOrderDto.setItemOrderNumber(fulfillmentDto.getItemOrderNumber());
        purchaseOrderDto.setCustomerName(fulfillmentDto.getCustomerName());
        purchaseOrderDto.setCustomerPhoneNumber(fulfillmentDto.getCustomerPhoneNumber());
        purchaseOrderDto.setRecipientName(fulfillmentDto.getRecipientName());
        purchaseOrderDto.setRecipientPhoneNumber1(fulfillmentDto.getRecipientPhoneNumber1());
        purchaseOrderDto.setRecipientPhoneNumber2(fulfillmentDto.getRecipientPhoneNumber2());
        purchaseOrderDto.setProductName(fulfillmentDto.getProductName());

        List<String> optionValues = Arrays.stream(fulfillmentDto.getOptionInfo().trim().split(", ")).toList();

        if (!optionValues.isEmpty()) purchaseOrderDto.setOption1(optionValues.get(0));
        if (optionValues.size() > 1) purchaseOrderDto.setOption1(optionValues.get(1));
        if (optionValues.size() > 2) purchaseOrderDto.setOption1(optionValues.get(2));

        purchaseOrderDto.setItemOrderBundleCount(fulfillmentDto.getItemOrderBundleCount());
        purchaseOrderDto.setAmount(fulfillmentDto.getAmount());
        purchaseOrderDto.setShippingRegionName(fulfillmentDto.getSido());
        purchaseOrderDto.setRecipientFullAddress("%s %s".formatted(fulfillmentDto.getRecipientAddress(), fulfillmentDto.getRecipientDetailAddress()));
        purchaseOrderDto.setDispatchDeadline(fulfillmentDto.getDispatchDeadline());
        purchaseOrderDto.setPreferredShipsOn(fulfillmentDto.getPreferredShipsOn());

        if (fulfillmentDto.getPurchasedAt() != null) {
            purchaseOrderDto.setPurchasedOn(fulfillmentDto.getPurchasedAt().toLocalDate());
        }
        if (fulfillmentDto.getDispatchedAt() != null) {
            purchaseOrderDto.setDispatchedOn(fulfillmentDto.getDispatchedAt().toLocalDate());
        }
        if (fulfillmentDto.getShippedAt() != null) {
            purchaseOrderDto.setShippedOn(fulfillmentDto.getShippedAt().toLocalDate());
        }

        purchaseOrderDto.setShippingMethod(fulfillmentDto.getShippingMethod().getDescription());
        purchaseOrderDto.setShippingChargeType(fulfillmentDto.getShippingChargeType().getDescription());
        purchaseOrderDto.setShippingCompany(fulfillmentDto.getShippingCompany());
        purchaseOrderDto.setShippingTrackingNumber(fulfillmentDto.getShippingTrackingNumber());
        purchaseOrderDto.setCustomerMemo(fulfillmentDto.getCustomerMessage());
        purchaseOrderDto.setPurchaseMemo(fulfillmentDto.getPurchaseMemo());
        purchaseOrderDto.setItemOrderState(fulfillmentDto.getItemOrderState().getDescription());

        return purchaseOrderDto;
    }


}
