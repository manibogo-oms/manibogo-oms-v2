package kr.tatine.manibogo_oms_v2.synchronize.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import kr.tatine.manibogo_oms_v2.order.command.application.OrderAlreadyPlacedException;
import kr.tatine.manibogo_oms_v2.order.command.application.OrderNotFoundException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.AlreadyShippedException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.StateAlreadyProceededException;
import kr.tatine.manibogo_oms_v2.synchronize.command.application.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/v2/synchronize")
@RequiredArgsConstructor
public class SynchronizeController {

    private final SyncExternalOrderService syncExternalOrderService;

    private final SyncOrderTrackingInfoService syncExternalParcelService;

    private final SyncOrderStateService syncOrderStateService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @PostMapping(value = "/external-item-orders")
    public String syncExternalItemOrders(
            @RequestParam("externalOrders") String externalOrders,
            RedirectAttributes redirectAttr
    ) {

        final List<ExternalItemOrderRequest> listRequest;

        final SynchronizeResponse response = new SynchronizeResponse();

        try {
            listRequest = objectMapper.readValue(externalOrders, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        log.debug("[SynchronizeController.syncExternalItemOrders] listRequest size = {}", listRequest.size());

        for (ExternalItemOrderRequest request : listRequest) {
            try {
                syncExternalOrderService.synchronize(request);
                response.success(new SynchronizeResult(request.itemOrderNumber()));
            } catch (OrderAlreadyPlacedException exception) {
                response.skip(new SynchronizeResult(request.itemOrderNumber()));
            } catch (ConstraintViolationException exception) {

                log.debug("[SynchronizeController.syncExternalItemOrders] Validation Error = {}", exception.getConstraintViolations());

                final List<String> errorMessages = exception
                        .getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .toList();

               response.error(new SynchronizeErrorResult(request.itemOrderNumber(), errorMessages));
            }
        }

        redirectAttr.addFlashAttribute("synchronizeResponse", response);

        return "redirect:/v2/fulfillment";
    }

    @PostMapping("/external-parcel")
    public String syncParcel(@RequestParam("externalParcel") String externalParcel, RedirectAttributes redirectAttr) {

        List<SyncOrderTrackingInfoCommand> listCommand = List.of();

        final SynchronizeResponse response = new SynchronizeResponse();

        try {
            listCommand = objectMapper.readValue(externalParcel, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error("[SynchronizeController.syncParcel] JSON 파싱 에러 = {}", e.getMessage());
            response.globalError("유효하지 않은 요청 형식입니다. 올바른 엑셀 파일을 업로드했는지 확인해주세요.");
        }

        for (final SyncOrderTrackingInfoCommand command : listCommand) {
            try {
                syncExternalParcelService.synchronize(command);
                response.success(new SynchronizeResult(command.orderNumber()));
            } catch (AlreadyShippedException | OrderNotFoundException exception) {
                response.skip(new SynchronizeResult(command.orderNumber()));
            } catch (ConstraintViolationException exception) {

                final List<String> errorMessages = exception
                        .getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .toList();

                response.error(new SynchronizeErrorResult(command.orderNumber(), errorMessages));
            }
        }

        redirectAttr.addFlashAttribute("synchronizeResponse", response);

        return "redirect:/v2/fulfillment";
    }

    @PostMapping("/external-item-order-state")
    public String syncItemOrderState(@RequestParam("externalItemOrderState") String externalItemOrderState, RedirectAttributes redirectAttr) {

        List<SyncOrderStateCommand> listCommand = List.of();

        final SynchronizeResponse response = new SynchronizeResponse();

        try {
            listCommand = objectMapper.readValue(externalItemOrderState, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error("[SynchronizeController.syncItemOrderState JSON 파싱 에러 = {}", e.getMessage());
            response.globalError("유효하지 않은 요청 형식입니다. 올바른 엑셀 파일을 업로드했는지 확인해주세요.");
        }

        for (final SyncOrderStateCommand command : listCommand) {
            try {
                syncOrderStateService.synchronize(command);
                response.success(new SynchronizeResult(command.orderNumber()));
            } catch (StateAlreadyProceededException | OrderNotFoundException exception) {
                response.skip(new SynchronizeResult(command.orderNumber()));
            } catch (ConstraintViolationException exception) {

                final List<String> errorMessages = exception
                        .getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .toList();

                response.error(new SynchronizeErrorResult(command.orderNumber(), errorMessages));
            }
        }

        redirectAttr.addFlashAttribute("synchronizeResponse", response);

        return "redirect:/v2/fulfillment";
    }
}
