/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.storage.system.column;

import org.librairy.storage.system.column.domain.DomainColumn;
import org.librairy.storage.system.column.repository.BaseColumnRepository;
import org.librairy.storage.system.column.repository.DomainColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cbadenes on 22/12/15.
 */
public class DomainUnifiedColumnRepositoryTest extends BaseColumnRepositoryTest<DomainColumn> {

    @Autowired
    DomainColumnRepository repository;

    @Override
    public BaseColumnRepository<DomainColumn> getRepository() {
        return repository;
    }

    @Override
    public DomainColumn getEntity() {
        DomainColumn column = new DomainColumn();
        column.setUri("domains/72ce5395-6268-439a-947e-802229e7f022");
        column.setCreationTime("2015-12-21T16:18:59Z");
        column.setDescription("for testing purposes");
        column.setName("test");
        return column;
    }
}
