package kr.tatine.manibogo_oms_v2.region.query.dao;

import kr.tatine.manibogo_oms_v2.region.query.dao.RegionDao;
import kr.tatine.manibogo_oms_v2.region.query.dto.RegionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupRegionsService {

    private final RegionDao regionDao;

    @Transactional(readOnly = true)
    public Map<String, List<String>> group() {
        return regionDao.findDistinctAll().stream()
                .collect(Collectors.groupingBy(
                        RegionDto::getSido,
                        Collectors.mapping(RegionDto::getSigungu, Collectors.toList())));
    }

}
