/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.boot.storage.system.column.repository;

import org.librairy.boot.storage.system.column.domain.SerializedObjectColumn;
import org.springframework.data.cassandra.repository.Query;

/**
 * Created by cbadenes on 21/12/15.
 */
public interface SerializedObjectColumnRepository extends BaseColumnRepository<SerializedObjectColumn> {

    //Future Version of Spring-Data-Cassandra will implements native queries

    @Query("select * from serializations where uri = ?0")
    Iterable<SerializedObjectColumn> findByUri(String uri);

    @Query("select * from serializations where creationTime = ?0")
    Iterable<SerializedObjectColumn> findByCreationTime(String creationTime);

    @Query("select * from serializations where startUri = ?0")
    Iterable<SerializedObjectColumn> findByStart(String uri);

    @Query("select * from serializations where endUri = ?0")
    Iterable<SerializedObjectColumn> findByEnd(String uri);

}
