package kr.tatine.manibogo_oms_v2.region.query.dto;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "zip_region_map")
public class ZipRegionMapView {

    @EmbeddedId
    public ZipRegionCode code;

}
