package kr.tatine.manibogo_oms_v2.fulfillment.query.dto;

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



    public static SmartStoreParcelDto fromFulfillmentDto(FulfillmentDto fulfillmentDto) {

        final SmartStoreParcelDto parcelDto = new SmartStoreParcelDto();

        parcelDto.setItemOrderNumber(fulfillmentDto.getOrderNumber());
        parcelDto.setShippingMethod(fulfillmentDto.getShippingMethod().getDescription());
        parcelDto.setShippingCompanyName(fulfillmentDto.getShippingCompany());
        parcelDto.setShippingTrackingNumber(fulfillmentDto.getShippingTrackingNumber());
        parcelDto.setCustomerName(fulfillmentDto.getCustomerName());
        parcelDto.setCustomerPhoneNumber(fulfillmentDto.getCustomerPhoneNumber());

        return parcelDto;
    }

}
