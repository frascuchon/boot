package org.librairy.storage.system.column.repository;

import org.librairy.storage.system.column.domain.BundleColumn;
import org.springframework.data.cassandra.repository.Query;

/**
 * Created by cbadenes on 21/12/15.
 */
public interface BundleColumnRepository extends BaseColumnRepository<BundleColumn> {

    //Future Version of Spring-Data-Cassandra will implements native queries

    @Query("select * from bundles where startUri = ?0")
    Iterable<BundleColumn> findByDocument(String uri);

    @Query("select * from bundles where endUri = ?0")
    Iterable<BundleColumn> findByItem(String uri);

}
