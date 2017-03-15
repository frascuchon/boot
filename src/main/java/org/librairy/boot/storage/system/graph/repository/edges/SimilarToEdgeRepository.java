/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.boot.storage.system.graph.repository.edges;

import org.librairy.boot.storage.system.graph.domain.edges.SimilarToEdge;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by cbadenes on 22/12/15.
 */
@Repository
public interface SimilarToEdgeRepository extends RelationGraphRepository<SimilarToEdge> {

    // To avoid a class type exception
    SimilarToEdge findOneByUri(String uri);

    @Query("match (node1:{uri:{0}})-[r:SIMILAR_TO]-(node2:{uri:{1}}) return r")
    Iterable<SimilarToEdge> findByNodes(String start, String end);

}