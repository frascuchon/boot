/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.storage.system.document.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by cbadenes on 22/12/15.
 */
@Document(indexName="research", type="parts")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PartDocument extends org.librairy.model.domain.resources.Part {

    @Override
    public Type getResourceType() {return Type.PART;}

    private String domain;
}
