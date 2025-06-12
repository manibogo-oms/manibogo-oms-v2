package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.common.model.ErrorResult;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.SalesChannel;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.FulfillmentDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentDto;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentSortParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("/v2/fulfillment")
@RequiredArgsConstructor
public class FulfillmentController {

    private final FulfillmentDao fulfillmentDao;

    @ModelAttribute("itemOrderStates")
    public ItemOrderState[] orderStates() {
        return ItemOrderState.values();
    }

    @ModelAttribute("salesChannels")
    public SalesChannel[] salesChannels() {
        return SalesChannel.values();
    }

    @GetMapping
    @Transactional(readOnly = true)
    public String fulfillment(
            @PageableDefault Pageable pageable,
            Model model,
            @ModelAttribute SynchronizeResponse synchronizeResponse,
            @ModelAttribute ErrorResult errorResult) {


        model.addAttribute("synchronizeResponse", synchronizeResponse);
        model.addAttribute("errorResult", errorResult);


        final Page<FulfillmentDto> page = fulfillmentDao.findAll(pageable);
        final List<FulfillmentDto> fulfillmentList = page.getContent();

        model.addAttribute("page", page);
        model.addAttribute("fulfillmentList", fulfillmentList);

        model.addAttribute("rowsForm", initEditForm(fulfillmentList));
        calculatePageAttribute(model, page);

        model.addAttribute("nextSortParams", getNextSortParams(pageable.getSort()));

        return "fulfillment";
    }

    private ItemOrderRowsForm initEditForm(List<FulfillmentDto> fulfillmentList) {

        final ItemOrderRowsForm editForm = new ItemOrderRowsForm();

        editForm.setRows(fulfillmentList.stream().map(FulfillmentDto::toEditFormRow).toList());

        return editForm;
    }

    private Map<String, String> getNextSortParams(Sort currentSort) {

        Map<String , String> nextSortParams = new HashMap<>();

        for (FulfillmentSortParam sortField : FulfillmentSortParam.values()) {

            final Optional<Sort.Order> optionalOrder = currentSort.stream()
                    .filter(order -> order.getProperty().equals(sortField.name()))
                    .findFirst();

            // 현재 정렬 없음 -> 다음은 내림차순 (DESC)
            if (optionalOrder.isEmpty()) {
                nextSortParams.put(sortField.name(), sortField.name() + ",DESC");
                continue;
            }

            final Sort.Order order = optionalOrder.get();

            // 현재 오름차순(ASC) -> 정렬 해제(UNSORTED)
            if (order.getDirection() == Sort.Direction.ASC) {
                nextSortParams.put(sortField.name(), "UNSORTED");
                continue;
            }

            // 현재 내림차순(DESC) -> 다음은 오름차순(ASC)
            nextSortParams.put(sortField.name(), sortField.name() + ",ASC");
        }

        return nextSortParams;
    }

    private void calculatePageAttribute(Model model, Page<FulfillmentDto> page) {
        // 1-based 현재 페이지 번호
        int currentPage1Based = page.getPageable().getPageNumber() + 1;
        // 1-based 총 페이지 수
        int totalPages1Based = page.getTotalPages();

        // --- 페이징 UI에 표시할 페이지 번호 목록 계산 로직 ---
        List<Integer> pageNumbers = new ArrayList<>();
        final int DISPLAY_MIDDLE_RANGE = 2; // 현재 페이지를 기준으로 앞뒤로 보여줄 페이지 개수 (예: 2 -> 총 5개)
        final int MAX_DISPLAY_PAGES = 5; // 표시할 페이지 번호의 최대 개수 (1, ... , totalPage 포함하지 않음)

        // 항상 첫 페이지(1) 추가
        if (totalPages1Based > 0) {
            pageNumbers.add(1);
        }

        // 중간 페이지 범위 계산
        int startMiddlePage = Math.max(2, currentPage1Based - DISPLAY_MIDDLE_RANGE);
        int endMiddlePage = Math.min(totalPages1Based - 1, currentPage1Based + DISPLAY_MIDDLE_RANGE);

        // "..." (왼쪽) 표시 여부
        if (startMiddlePage > 2) { // 1 다음에 2가 아닌 다른 페이지가 오면 "..."
            // "..." 을 나타내는 특별한 값 (예: -1 또는 null)을 리스트에 추가하거나,
            // 템플릿에서 직접 조건부 렌더링으로 처리하는게 더 일반적입니다.
            // 여기서는 템플릿에서 처리할 수 있도록 startMiddlePage와 endMiddlePage만 전달합니다.
        }

        // 중간 페이지 번호 추가
        for (int i = startMiddlePage; i <= endMiddlePage; i++) {
            if (i > 1 && i < totalPages1Based) { // 1과 마지막 페이지는 이미 추가했으므로 제외
                pageNumbers.add(i);
            }
        }

        // "..." (오른쪽) 표시 여부
        if (endMiddlePage < totalPages1Based - 1) { // 마지막 페이지 이전에 마지막-1 페이지가 아닌 다른 페이지가 오면 "..."
            // 템플릿에서 조건부 렌더링
        }

        // 항상 마지막 페이지 추가 (총 페이지 수가 1이 아니고 이미 1이 아닌 경우)
        if (totalPages1Based > 1 && !pageNumbers.contains(totalPages1Based)) {
            pageNumbers.add(totalPages1Based);
        }



        // --- 복합 페이징 로직 끝 ---
        model.addAttribute("pageNumbers", pageNumbers); // UI에 표시할 페이지 번호 목록 (1-based)
        model.addAttribute("currentPage", currentPage1Based); // 1-based 현재 페이지
        model.addAttribute("pageSize", page.getPageable().getPageSize());
        model.addAttribute("totalPage", totalPages1Based); // 1-based 총 페이지 수 (템플릿 마지막 버튼 링크에 필요)



        // "..." 표시 여부를 위한 플래그 (템플릿에서 사용)
        model.addAttribute("showLeftDots", startMiddlePage > 2);
        model.addAttribute("showRightDots", endMiddlePage < totalPages1Based - 1);

    }

}
