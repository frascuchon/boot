/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.boot.storage.system.column.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.librairy.boot.model.domain.relations.Composes;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created on 15/04/16:
 *
 * @author cbadenes
 */
@Table(value = "composes")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ComposeColumn extends Composes {

}
