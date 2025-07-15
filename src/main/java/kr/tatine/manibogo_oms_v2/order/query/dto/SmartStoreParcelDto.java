package kr.tatine.manibogo_oms_v2.order.query.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SmartStoreParcelDto {

    @JsonProperty("상품주문번호")
    private String itemOrderNumber;

    @JsonProperty("배송방법")
    private String shippingMethod;

    @JsonProperty("택배사")
    private String shippingCompanyName;

    @JsonProperty("송장번호")
    private String shippingTrackingNumber;

    @JsonProperty("구매자명")
    private String customerName;

    @JsonProperty("구매자연락처")
    private String customerPhoneNumber;



    public static SmartStoreParcelDto fromFulfillmentDto(OrderDto orderDto) {

        final SmartStoreParcelDto parcelDto = new SmartStoreParcelDto();

        parcelDto.setItemOrderNumber(orderDto.getOrderNumber());
        parcelDto.setShippingMethod(orderDto.getShippingMethod().getDescription());
        parcelDto.setShippingCompanyName(orderDto.getShippingCompany());
        parcelDto.setShippingTrackingNumber(orderDto.getShippingTrackingNumber());
        parcelDto.setCustomerName(orderDto.getCustomerName());
        parcelDto.setCustomerPhoneNumber(orderDto.getCustomerPhoneNumber());

        return parcelDto;
    }

}
