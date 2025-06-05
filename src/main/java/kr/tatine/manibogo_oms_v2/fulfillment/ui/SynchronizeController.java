package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.fulfillment.command.application.ExternalItemOrderRequest;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.SyncExternalItemOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v2/synchronize")
@RequiredArgsConstructor
public class SynchronizeController {

    private final SyncExternalItemOrderService syncExternalItemOrderService;

    @GetMapping("/external-item-orders")
    public String getSyncExternalItemOrders() {
        return "popup/sync-smart-store-item-order";
    }

    @PostMapping("/external-item-orders")
    public String syncExternalItemOrders(
            @RequestBody List<ExternalItemOrderRequest> listRequest) {

        for (ExternalItemOrderRequest request : listRequest) {
            syncExternalItemOrderService.synchronize(request);
        }

        return "redirect:/v2/synchronize/external-item-orders";
    }

}
