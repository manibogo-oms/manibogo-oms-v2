package kr.tatine.manibogo_oms_v2.fulfillment.command.domain.option;

import kr.tatine.manibogo_oms_v2.common.model.Option;
import org.springframework.data.repository.CrudRepository;

public interface VariantRepository extends CrudRepository<Variant, VariantId> { }
