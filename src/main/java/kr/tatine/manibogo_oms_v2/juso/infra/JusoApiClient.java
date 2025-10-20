package kr.tatine.manibogo_oms_v2.juso.infra;

import kr.tatine.manibogo_oms_v2.common.contract.out.JusoView;
import kr.tatine.manibogo_oms_v2.juso.application.port.out.JusoQueryPort;
import kr.tatine.manibogo_oms_v2.juso.domain.JusoCode;
import kr.tatine.manibogo_oms_v2.shipping.infra.adapter.dto.JusoApiSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@Order(2)
@Repository
public class JusoApiClient implements JusoQueryPort {

    private final RestClient restClient = RestClient.create();

    private final String apiKey;

    public JusoApiClient(@Value("${juso.search.key}") String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Optional<JusoView> findByAddress(String address) {
        final URI uri = UriComponentsBuilder
                .fromUriString("https://business.juso.go.kr/addrlink/addrLinkApi.do")
                .queryParam("currentPage", "1")
                .queryParam("countPerPage", "1")
                .queryParam("resultType", "json")
                .queryParam("confmKey", apiKey)
                .queryParam("keyword", address)
                .build()
                .toUri();

        final JusoApiSearchResponse response = restClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(JusoApiSearchResponse.class);

        if (Objects.isNull(response)) return Optional.empty();

        return Optional.ofNullable(response.results())
                .map(JusoApiSearchResponse.Results::juso)
                .map(juso -> juso.get(0))
                .map(JusoApiClient::createJuso);
    }

    private static JusoView createJuso(JusoApiSearchResponse.Results.Juso juso) {
        return new JusoView(createJusoCode(juso), juso.admCd(), juso.roadAddr(), juso.siNm(), juso.sggNm());
    }

    private static JusoCode createJusoCode(JusoApiSearchResponse.Results.Juso juso) {
        final String sggCode = juso.rnMgtSn().substring(0, 4);
        final String roadNo = juso.rnMgtSn().substring(5);

        return JusoCode.of(sggCode, juso.emdNo(), roadNo, juso.udrtYn(), juso.buldMnnm(), juso.buldSlno());
    }

}
