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
public class DealsPartEdgeTemplate extends EdgeTemplate {

    public DealsPartEdgeTemplate() {
        super(Relation.Type.DEALS_WITH_FROM_PART);
    }

    @Override
    protected String label() {
        return "DEALS_WITH";
    }

    @Override
    protected String pathBy(Resource.Type type) {
        switch (type){
            case ANY:           return "(s:Part)-[r:DEALS_WITH]->(e:Topic)";
            case DOMAIN:        return "(domain:Domain{uri:{0}})-[c:CONTAINS]->(s:Part)-[r:DEALS_WITH]->(e:Topic)";
            case TOPIC:         return "(e:Topic{uri:{0}})<-[r:DEAL_WITH]-(s:Part)";
            case PART:          return "(e:Topic)<-[r:DEALS_WITH]-(s:Part{uri:{0}})";
            default: throw new RuntimeException("Path for " + type.name() + " not implemented yet");
        }
    }

    @Override
    protected String pathBy(Relation.Type type) {
        switch (type){
            case DEALS_WITH_FROM_PART:      return "(s:Part{uri:{0}})-[r:DEALS_WITH]->(e:Topic{uri:{1}})";
            default: throw new RuntimeException("Path for " + type.name() + " not implemented yet");
        }
    }

    @Override
    protected TemplateParameters paramsFrom(Relation relation) {
        return new TemplateParameters(relation);
    }

}