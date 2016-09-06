package org.librairy.model.domain.relations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by cbadenes on 17/02/16.
 */
@ToString(callSuper = true)
@EqualsAndHashCode(of={"uri"}, callSuper = true)
@XmlRootElement(name = "embeddedIn")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmbeddedIn extends Relation {

    @Override
    public org.librairy.model.domain.resources.Resource.Type getStartType() {
        return org.librairy.model.domain.resources.Resource.Type.WORD;
    }

    @Override
    public org.librairy.model.domain.resources.Resource.Type getEndType() {
        return org.librairy.model.domain.resources.Resource.Type.DOMAIN;
    }

    @Override
    public Double getWeight() {
        return 1.0;
    }

    @Override
    public Type getType() {return Type.EMBEDDED_IN;}
}