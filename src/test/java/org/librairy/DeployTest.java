package org.librairy;

import es.cbadenes.lab.test.IntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cbadenes on 15/03/16.
 */
@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@TestPropertySource(properties = {
        "librairy.cassandra.contactpoints = 192.168.99.100",
        "librairy.cassandra.port = 5011",
        "librairy.cassandra.keyspace = research",
        "librairy.elasticsearch.contactpoints = 192.168.99.100",
        "librairy.elasticsearch.port = 5021",
        "librairy.neo4j.contactpoints = 192.168.99.100",
        "librairy.neo4j.port = 5030",
        "librairy.eventbus.host = 192.168.99.100",
        "librairy.eventbus.port = 5041"
})
public class DeployTest {

    @Test
    public void start(){
        Assert.assertTrue(true);
    }

}