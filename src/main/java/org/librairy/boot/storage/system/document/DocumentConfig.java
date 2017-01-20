/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.boot.storage.system.document;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.TimeUnit;

/**
 * Created by cbadenes on 21/12/15.
 */
@Configuration("org.librairy.boot.storage.system.document")
@ComponentScan({"org.librairy.boot.storage.system.document.repository"})
@EnableElasticsearchRepositories(basePackages = {"org.librairy.boot.storage.system.document.repository"})
@EnableTransactionManagement
public class DocumentConfig {

    private Logger LOG = LoggerFactory.getLogger(DocumentConfig.class);


    @Value("#{environment['LIBRAIRY_DOCUMENTDB_HOST']?:'${librairy.documentdb.host}'}")
    String hosts;

    @Value("#{environment['LIBRAIRY_DOCUMENTDB_PORT']?:${librairy.documentdb.port}}")
    Integer port;

    @Bean(name = "elasticsearchClient")
    public TransportClient client(){

        LOG.info("Initializying Elasticsearch connection to: " + hosts + " " + port + " ..");

        Settings settings = ImmutableSettings.settingsBuilder().
//                        put("cluster.name", "drinventor").
                        put("client.transport.sniff", false).
                        put("client.transport.ping_timeout", 30, TimeUnit.SECONDS).
                build();

        TransportClient client = new TransportClient(settings);
        TransportAddress address = new InetSocketTransportAddress(hosts,port);
        client.addTransportAddress(address);

        LOG.info("Initialized Elasticsearch connection to: " + hosts + " " + port);
        return client;
    }

    @Bean(name = "elasticsearchTemplate")
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
}