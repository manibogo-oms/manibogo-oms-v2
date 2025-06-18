package kr.tatine.manibogo_oms_v2.fulfillment.query.dao;

import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.ProductDto;

import java.util.List;


public interface ProductDao {

    List<ProductDto> findAll();

    List<ProductDto> findEnabled();

}
