/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.boot.storage.actions;

import org.librairy.boot.model.domain.resources.Resource;
import org.librairy.boot.storage.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Created by cbadenes on 04/02/16.
 */
public class ReadResourceAction{

    private static final Logger LOG = LoggerFactory.getLogger(ReadResourceAction.class);

    private final Helper helper;
    private final Resource.Type type;

    public ReadResourceAction(Helper helper, Resource.Type type){
        this.helper = helper;
        this.type = type;
    }

    /**
     * Save a resource
     */
    public Optional<Resource> byUri(String uri){
        try{
            return helper.getUnifiedColumnRepository().read(type,uri);
        }catch (Exception e){
            LOG.error("Unexpected error while checking resource: "+uri,e);
            return Optional.empty();
        }
    }


}
