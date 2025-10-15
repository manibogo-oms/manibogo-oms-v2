package kr.tatine.manibogo_oms_v2.common.synchronize;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SyncQueryPort extends Repository<Synchronize, SyncKey> {

    Optional<Synchronize> findByKey(SyncKey key);

}
