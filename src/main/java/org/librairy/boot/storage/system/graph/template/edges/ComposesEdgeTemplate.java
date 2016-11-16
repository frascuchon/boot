/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.boot.storage.system.graph.template.edges;

import org.librairy.boot.model.domain.relations.Relation;
import org.librairy.boot.model.domain.resources.Resource;
import org.springframework.stereotype.Component;

/**
 * Created by cbadenes on 28/02/16.
 */
@Component
public class ComposesEdgeTemplate extends EdgeTemplate {

    public ComposesEdgeTemplate() {
        super(Relation.Type.COMPOSES);
    }

    @Override
    protected String label() {
        return "COMPOSES";
    }

    @Override
    protected String pathBy(Resource.Type type) {
        switch (type){
            case ANY:           return "(s:Source)-[r:COMPOSES]->(e:Domain)";
            case DOMAIN:        return "(e:Domain{uri:{0}})<-[r:COMPOSES]-(s:Source)";
            case SOURCE:        return "(s:Source{uri:{0}})-[r:COMPOSES]->(e:Domain)";
            default: throw new RuntimeException("Path for " + type.name() + " not implemented yet");
        }
    }

    @Override
    protected String pathBy(Relation.Type type) {
        switch (type){
            case COMPOSES:   return "(s:Source{uri:{0}})-[r:COMPOSES]->(e:Domain{uri:{1}})";
            default: throw new RuntimeException("Path for " + type.name() + " not implemented yet");
        }
    }

    @Override
    protected TemplateParameters paramsFrom(Relation relation) {
        return new TemplateParameters(relation);
    }


}