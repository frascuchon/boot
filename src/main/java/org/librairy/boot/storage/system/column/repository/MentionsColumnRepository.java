/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.boot.storage.system.column.repository;

import org.librairy.boot.storage.system.column.domain.MentionsColumn;
import org.springframework.data.cassandra.repository.Query;

/**
 * Created by cbadenes on 21/12/15.
 */
public interface MentionsColumnRepository extends BaseColumnRepository<MentionsColumn> {

    //Future Version of Spring-Data-Cassandra will implements native queries

    @Query("select * from mentions where startUri = ?0")
    Iterable<MentionsColumn> findByTopic(String uri);

    @Query("select * from mentions where startUri = ?0")
    Iterable<MentionsColumn> findByTerm(String uri);

    @Query("select * from mentions where endUri = ?0")
    Iterable<MentionsColumn> findByWord(String uri);

    @Query("select * from mentions where startUri = ?0")
    Iterable<MentionsColumn> findByStart(String uri);

    @Query("select * from mentions where endUri = ?0")
    Iterable<MentionsColumn> findByEnd(String uri);

}
