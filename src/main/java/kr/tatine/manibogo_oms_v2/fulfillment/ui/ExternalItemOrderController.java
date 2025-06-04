package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.fulfillment.command.application.SyncExternalItemOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v2/external-item-orders")
@RequiredArgsConstructor
public class ExternalItemOrderController {

    private final SyncExternalItemOrderService syncExternalItemOrderService;


    @GetMapping("/synchronize")
    public String getSynchronizeItemOrders() {
        return "popup/sync-smart-store-item-order";
    }

    @PostMapping("/synchronize")
    public String synchronizeItemOrders(
            @RequestBody List<SyncExternalItemOrderRequest> listRequest) {

        for (SyncExternalItemOrderRequest record : listRequest) {
            syncExternalItemOrderService.synchronize(record.toCommand());
        }

        return "redirect:/v2/item-orders/synchronize";
    }

}
