package kr.tatine.manibogo_oms_v2.fulfillment.query.dao;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.variant.Variant;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.variant.VariantId;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.VariantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface VariantDao extends Repository<Variant, VariantId> {

    @Query("SELECT new kr.tatine.manibogo_oms_v2.fulfillment.query.dto.VariantDto(" +
            "   v.variantId.productNumber.productNumber, " +
            "   v.variantId.option.key, " +
            "   v.variantId.option.value, " +
            "   v.label) " +
            "FROM Variant v " +
            "WHERE v.variantId.productNumber.productNumber = :productNumber")
    Page<VariantDto> findAllByProductNumber(Pageable pageable, String productNumber);

}
