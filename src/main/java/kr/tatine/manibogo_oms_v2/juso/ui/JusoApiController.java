package kr.tatine.manibogo_oms_v2.juso.ui;

import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;
import kr.tatine.manibogo_oms_v2.juso.application.port.out.IntegratedJusoQueryPort;
import kr.tatine.manibogo_oms_v2.juso.infra.JusoDao;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v2/juso")
@RequiredArgsConstructor
public class JusoApiController {

    private final IntegratedJusoQueryPort integratedJusoQueryPort;

    @GetMapping
    @Transactional(readOnly = true)
    public List<JusoView> findAllByIntegratedFrom(LocalDate integratedFrom) {

        return integratedJusoQueryPort.findAllByIntegratedFrom(integratedFrom);
    }

}
