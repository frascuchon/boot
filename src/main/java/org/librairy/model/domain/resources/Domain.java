/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.model.domain.resources;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by cbadenes on 22/12/15.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(of = "uri", callSuper = true)
@XmlRootElement(name = "domain")
@XmlAccessorType(XmlAccessType.FIELD)
public class Domain extends Resource {

    @Override
    public Resource.Type getResourceType() {return Type.DOMAIN;}

    public static final String NAME="name";
    private String name;

    public static final String DESCRIPTION="description";
    private String description;

}
