/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.boot.model.domain.relations;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.librairy.boot.model.domain.resources.Resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by cbadenes on 17/02/16.
 */
@ToString(callSuper = true)
@EqualsAndHashCode(of={"uri"}, callSuper = true)
@XmlRootElement(name = "similarTo")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimilarToDocuments extends SimilarTo {

    @Override
    public Resource.Type getResourceType() {
        return Resource.Type.DOCUMENT;
    }

    @Override
    public Type getType() {return Type.SIMILAR_TO_DOCUMENTS;}
}
