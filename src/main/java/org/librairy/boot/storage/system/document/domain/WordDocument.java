/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.boot.storage.system.document.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.librairy.boot.model.domain.resources.Resource;
import org.librairy.boot.model.domain.resources.Word;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by cbadenes on 22/12/15.
 */
@Document(indexName="research", type="words")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class WordDocument extends Word {

    @Override
    public Resource.Type getResourceType() {return Type.WORD;}

    private String domain;
}