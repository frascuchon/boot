/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cbadenes on 15/03/16.
 */
@Configuration("librairy.boot.test")
@ComponentScan({"org.librairy.boot"})
public class ConfigTest {
}
