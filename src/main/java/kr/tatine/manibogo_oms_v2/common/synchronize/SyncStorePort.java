package kr.tatine.manibogo_oms_v2.common.synchronize;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SyncStorePort extends Repository<Synchronize, SyncKey> {

    Synchronize save(Synchronize synchronize);

}
