package kr.tatine.manibogo_oms_v2.order.query.dto;

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

    public static List<PurchaseOrderDto> FromFulfillmentDtoList(List<OrderDto> orderDtoList) {

        final List<PurchaseOrderDto> purchaseOrders = new ArrayList<>();

        for (int i = 0; i < orderDtoList.size(); i ++) {
            purchaseOrders.add(fromFulfillmentDto(i, orderDtoList.get(i)));

        }

        return purchaseOrders;
    }

    private static PurchaseOrderDto fromFulfillmentDto(int index, OrderDto orderDto) {
        PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();

        purchaseOrderDto.setSequence(index + 1);
        purchaseOrderDto.setItemOrderNumber(orderDto.getOrderNumber());
        purchaseOrderDto.setCustomerName(orderDto.getCustomerName());
        purchaseOrderDto.setCustomerPhoneNumber(orderDto.getCustomerPhoneNumber());
        purchaseOrderDto.setRecipientName(orderDto.getRecipientName());
        purchaseOrderDto.setRecipientPhoneNumber1(orderDto.getRecipientPhoneNumber1());
        purchaseOrderDto.setRecipientPhoneNumber2(orderDto.getRecipientPhoneNumber2());
        purchaseOrderDto.setProductName(orderDto.getProductName());

        List<String> optionValues = Arrays.stream(orderDto.getOptionInfo().trim().split(", ")).toList();

        if (!optionValues.isEmpty()) purchaseOrderDto.setOption1(optionValues.get(0));
        if (optionValues.size() > 1) purchaseOrderDto.setOption1(optionValues.get(1));
        if (optionValues.size() > 2) purchaseOrderDto.setOption1(optionValues.get(2));

        purchaseOrderDto.setItemOrderBundleCount(orderDto.getItemOrderBundleCount());
        purchaseOrderDto.setAmount(orderDto.getAmount());
        purchaseOrderDto.setShippingRegionName(orderDto.getSido());
        purchaseOrderDto.setRecipientFullAddress("%s %s".formatted(orderDto.getRecipientAddress(), orderDto.getRecipientDetailAddress()));
        purchaseOrderDto.setDispatchDeadline(orderDto.getDispatchDeadline());
        purchaseOrderDto.setPreferredShipsOn(orderDto.getPreferredShippingDate());

        if (orderDto.getPurchasedAt() != null) {
            purchaseOrderDto.setPurchasedOn(orderDto.getPurchasedAt().toLocalDate());
        }
        if (orderDto.getDispatchedAt() != null) {
            purchaseOrderDto.setDispatchedOn(orderDto.getDispatchedAt().toLocalDate());
        }
        if (orderDto.getShippedAt() != null) {
            purchaseOrderDto.setShippedOn(orderDto.getShippedAt().toLocalDate());
        }

        purchaseOrderDto.setShippingMethod(orderDto.getShippingMethod().getDescription());
        purchaseOrderDto.setShippingChargeType(orderDto.getShippingChargeType().getDescription());
        purchaseOrderDto.setShippingCompany(orderDto.getShippingCompany());
        purchaseOrderDto.setShippingTrackingNumber(orderDto.getShippingTrackingNumber());
        purchaseOrderDto.setCustomerMemo(orderDto.getCustomerMessage());
        purchaseOrderDto.setPurchaseMemo(orderDto.getPurchaseMemo());
        purchaseOrderDto.setItemOrderState(orderDto.getOrderState().getDescription());

        return purchaseOrderDto;
    }


}
