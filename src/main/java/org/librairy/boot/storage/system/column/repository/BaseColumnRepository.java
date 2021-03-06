/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.boot.storage.system.column.repository;


import org.librairy.boot.model.domain.LinkableElement;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by cbadenes on 21/12/15.
 */
@NoRepositoryBean
public interface BaseColumnRepository<T extends LinkableElement> extends CassandraRepository<T> {


    Iterable<T> findByStart(String uri);

    Iterable<T> findByEnd(String uri);

}
