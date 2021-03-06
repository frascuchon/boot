/*
 * Copyright (c) 2016. Universidad Politecnica de Madrid
 *
 * @author Badenes Olmedo, Carlos <cbadenes@fi.upm.es>
 *
 */

package org.librairy.boot.storage;

import es.cbadenes.lab.test.IntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.librairy.boot.Config;
import org.librairy.boot.model.Event;
import org.librairy.boot.model.domain.relations.HypernymOf;
import org.librairy.boot.model.domain.relations.Relation;
import org.librairy.boot.model.domain.resources.Document;
import org.librairy.boot.model.domain.resources.Domain;
import org.librairy.boot.model.domain.resources.Resource;
import org.librairy.boot.model.domain.resources.Source;
import org.librairy.boot.model.modules.BindingKey;
import org.librairy.boot.model.modules.EventBus;
import org.librairy.boot.model.modules.EventBusSubscriber;
import org.librairy.boot.model.modules.RoutingKey;
import org.librairy.boot.storage.generator.URIGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cbadenes on 01/01/16.
 */
@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@TestPropertySource(properties = {
        "librairy.columndb.host= localhost",
        "librairy.columndb.port= 9042",
        "librairy.eventbus.host = local"
})
public class UDMTest {

    private static final Logger LOG = LoggerFactory.getLogger(UDMTest.class);

    @Autowired
    UDM udm;

    @Autowired
    Helper helper;

    @Autowired
    EventBus eventBus;

    @Autowired
    URIGenerator uriGenerator;

    @Test
    public void saveSourceMinimal(){

        LOG.info("Getting sources..");
        List<Resource> sources = udm.find(Resource.Type.SOURCE).all();
        LOG.info("Sources: " + sources);

        Source source = Resource.newSource("default");
        source.setUrl("file://default");
        udm.save(source);
        LOG.info("Source created");

        LOG.info("Getting sources..");
        sources = udm.find(Resource.Type.SOURCE).all();
        LOG.info("Sources: " + sources);
    }

    @Test
    public void readDomains(){
        List<Resource> resources = udm.find(Resource.Type.DOMAIN).all();
        System.out.println(resources);
    }

    @Test
    public void summary(){
        Arrays.stream(Resource.Type.values()).forEach(type -> System.out.println(">"+type.name() + ": " + udm.find(type).all().size() ));
        Arrays.stream(Relation.Type.values()).forEach(type -> System.out.println(">"+type.name() + ": " + udm.find(type).all().size() ));
    }

    @Test
    public void fixModel(){

        Source casaSource = Resource.newSource("casa");
        casaSource.setDescription("casa 2016 papers");
        casaSource.setUrl("file://casa");
        udm.save(casaSource);

        Source siggraphSource = Resource.newSource("siggraph");
        siggraphSource.setDescription("siggraph 2002-2015 papers");
        siggraphSource.setUrl("file://siggraph");
        udm.save(siggraphSource);

    }


    @Test
    public void purge(){

        udm.delete(Resource.Type.ANY).all();
        udm.delete(Relation.Type.ANY).all();
    }


    @Test
    public void relations(){

        _saveRelation(Relation.newAggregates("u1","u2"));
        _saveRelation(Relation.newAppearedIn("u1","u2"));
        _saveRelation(Relation.newBundles("u1","u2"));
        _saveRelation(Relation.newComposes("u1","u2"));
        _saveRelation(Relation.newContains("u1","u2"));
        _saveRelation(Relation.newContains("u1","u2"));
        _saveRelation(Relation.newDealsWithFromDocument("u1","u2"));
        _saveRelation(Relation.newDealsWithFromItem("i1","i2"));
        _saveRelation(Relation.newDealsWithFromPart("p1","p2"));
        _saveRelation(Relation.newDescribes("u1","u2"));
        _saveRelation(Relation.newEmbeddedIn("u1","u2"));
        _saveRelation(Relation.newEmergesIn("u1","u2"));
        _saveRelation(Relation.newHypernymOf("u1","u2","d1"));
        _saveRelation(Relation.newMentionsFromTerm("u1","u2"));
        _saveRelation(Relation.newMentionsFromTopic("t1","t2"));
        _saveRelation(Relation.newPairsWith("u1","u2","d1"));
        _saveRelation(Relation.newProvides("u1","u2"));
    }

    private void _saveRelation(Relation relation){
        LOG.info("Saving: " + relation.getType() + " ...");
        relation.setUri(UUID.randomUUID().toString());
        helper.getUnifiedColumnRepository().save(relation);
        LOG.info("Saved: " + relation.getType());
    }


    @Test
    public void save(){


        List<Relation> relations = udm.find(Relation.Type.BUNDLES).all();

        relations.stream().parallel().forEach(relation -> {
            LOG.info("saving relation: " + relation.getUri());
            helper.getUnifiedColumnRepository().save(relation);
        });







//        String document = "http://librairy.org/documents/07043092";
//        String item     = "http://librairy.org/items/b4e1576bf9fb01271e78c52947b12644";
//        Bundles bundle = Relation.newBundles(document,item);
//        udm.save(bundle);


//        Document doc = Resource.newDocument();
//        doc.setUri("http://librairy.org/documents/06856810");
//        doc.setCreationTime("2016-04-12T16:26+0000");
//        udm.save(doc);


    }



    @Test

    public void findFrom(){
        String uri = "http://drinventor.eu/items/a5179367d5ebf825f01d9247dacae66";
        List<Resource> domains = udm.find(Resource.Type.DOMAIN).from(Resource.Type.ITEM, uri);
        System.out.println("Domains: " + domains);
    }


    @Test
    public void hypernym(){

        String domainUri = uriGenerator.from(Resource.Type.DOMAIN, "sample-domain");
        HypernymOf hypernym = Relation.newHypernymOf("http://librairy" +
                ".org/terms/3324e10e-87c5-49a5-a9ba-c79adf3beba0", "http://librairy" +
                ".org/terms/4cbd8d67-05d1-4d0b-b1da-7ccd3244298a",domainUri);
        hypernym.setDomain("http://librairy.org/domains/28cd53dc-bc1c-417d-9ae5-2b5a7052d819");
        hypernym.setWeight(0.04766949152542373);
        udm.save(hypernym);

        assert true;
    }


    @Test
    public void saveSource(){
        AtomicInteger counter = new AtomicInteger(0);

        eventBus.subscribe(new EventBusSubscriber() {
            @Override
            public void handle(Event event) {
                LOG.info("Handle Event: " + event);
                counter.incrementAndGet();
            }
        }, BindingKey.of(RoutingKey.of(Resource.Type.SOURCE, Resource.State.CREATED),"test"));


        Source source = Resource.newSource
                ("test-source");
        source.setUri("http://librairy.org/sources/0b3e80ae-d598-4dd4-8c54-38e2229f0bf8");
        source.setUrl("file://opt/librairy/inbox/upm");
        source.setProtocol("file");
        source.setCreationTime("20160101T22:02");
        source.setDescription("testing purposes");

        LOG.info("Saving source: " + source);
        udm.save(source);
        LOG.info("source saved!");

        Optional<Resource> source2 = udm.read(Resource.Type.SOURCE).byUri(source.getUri());
        Assert.assertTrue(source2.isPresent());
        Assert.assertEquals(source.getUri(),source2.get().getUri());
        Assert.assertEquals(source.getName(),source2.get().asSource().getName());

        LOG.info("Deleting source: " + source);
        udm.delete(Resource.Type.SOURCE).byUri(source.getUri());
        LOG.info("source deleted!");

        Optional<Resource> source3 = udm.read(Resource.Type.SOURCE).byUri(source.getUri());
        Assert.assertFalse(source3.isPresent());

    }

    @Test
    public void findDocumentsInDomain(){
        // Source
        Source source = Resource.newSource("s");
        udm.save(source);

        // Domain
        Domain domain = Resource.newDomain("d");
        udm.save(domain);

        // Document 1
        Document doc1 = Resource.newDocument
                ("d1");
        udm.save(doc1);
        // -> document1 in source
        udm.save(Relation.newProvides(source.getUri(),doc1.getUri()));
        // -> document1 in domain
        udm.save(Relation.newContains(domain.getUri(),doc1.getUri()));

        // Document 2
        Document doc2 = Resource.newDocument
                ("d2");
        udm.save(doc2);
        // -> document2 in source
        udm.save(Relation.newProvides(source.getUri(),doc2.getUri()));
        // -> document2 in domain
        udm.save(Relation.newContains(domain.getUri(),doc2.getUri()));

        // Getting Documents
        List<Resource> documents = udm.find(Resource.Type.DOCUMENT).from(Resource.Type.DOMAIN,domain.getUri());

        // Delete
        udm.delete(Resource.Type.SOURCE).byUri(source.getUri());
        udm.delete(Resource.Type.DOMAIN).byUri(domain.getUri());
        udm.delete(Resource.Type.DOCUMENT).byUri(doc1.getUri());
        udm.delete(Resource.Type.DOCUMENT).byUri(doc2.getUri());

        Assert.assertTrue(documents != null);
        Assert.assertEquals(2,documents.size());
    }


    @Test
    public void getTopicDistributionOfDocumentsInDomain(){

        String domain = "http://librairy.org/domains/72dba453-eaba-4cb6-99f3-a456e96f3768";

        Iterable<Relation> relations = udm.find(Relation.Type.DEALS_WITH_FROM_DOCUMENT).from(Resource.Type.DOMAIN, domain);
        LOG.info("Result: " + relations);

    }

    @Test
    public void deleteAndCreateEmbeddedINRelations(){

        try {
            String domain = "http://librairy.org/domains/d4a5f93d-fc90-453e-a2d5-7ca27dfb4e29";
            String word = "http://librairy.org/words/67f76420-4d11-42d5-a692-f2bd5c353ac9";

            LOG.info("First loop");
            udm.find(Relation.Type.EMBEDDED_IN)
                    .from(Resource.Type.DOMAIN, domain)
                    .parallelStream()
                    .forEach(rel -> udm.delete(Relation.Type.EMBEDDED_IN).byUri(rel.getUri()));


            Iterable<Relation> pairs = udm.find(Relation.Type.PAIRS_WITH).from(Resource.Type.DOMAIN, domain);
            if (pairs != null){
                for (Relation pair : pairs) {
                    udm.delete(Relation.Type.PAIRS_WITH).byUri(pair.getUri());
                }
            }

            udm.save(Relation.newEmbeddedIn(word,domain));


            LOG.info("Second loop");
            udm.find(Relation.Type.EMBEDDED_IN)
                    .from(Resource.Type.DOMAIN, domain)
                    .parallelStream()
                    .forEach(rel -> udm.delete(Relation.Type.EMBEDDED_IN).byUri(rel.getUri()));

            udm.find(Relation.Type.PAIRS_WITH)
                    .from(Resource.Type.DOMAIN, domain)
                    .parallelStream()
                    .forEach(rel -> udm.delete(Relation.Type.PAIRS_WITH).byUri(rel.getUri()));
            udm.save(Relation.newEmbeddedIn(word,domain));

            LOG.info("Third loop");
            udm.find(Relation.Type.EMBEDDED_IN)
                    .from(Resource.Type.DOMAIN, domain)
                    .parallelStream()
                    .forEach(rel -> udm.delete(Relation.Type.EMBEDDED_IN).byUri(rel.getUri()));
            udm.find(Relation.Type.PAIRS_WITH)
                    .from(Resource.Type.DOMAIN, domain)
                    .parallelStream()
                    .forEach(rel -> udm.delete(Relation.Type.PAIRS_WITH).byUri(rel.getUri()));
            udm.save(Relation.newEmbeddedIn(word,domain));


        }catch (Exception e){
            LOG.error("Error",e);
        }
    }

    public void deleteAll(){
        udm.delete(Resource.Type.ANY).all();
    }


    @Test
    public void findWordsInDomains(){

        List<String> domains = Arrays.asList(new String[]{
                "http://librairy.org/domains/e19f1196-9233-41be-9eb0-f3ee5895998d",
                "http://librairy.org/domains/031a7709-17d0-4b53-9982-4feac9281082",
                "http://librairy.org/domains/6be14ee9-72ac-4c35-868f-9735851b1042",
                "http://librairy.org/domains/cb0b34ba-9401-48da-8a7a-c435361516a4"
        });

        domains.forEach(domain -> {
            List<Resource> wordsByDomain = udm.find(Resource.Type.WORD).from(Resource.Type.DOMAIN,domain);
            LOG.info("Domain '" + domain + "' contains: " + wordsByDomain.size() + " words");
        });


        List<Resource> words = udm.find(Resource.Type.WORD).all();
        LOG.info("Total words: " + words.size());

    }

    @Test
    public void readADomain(){

        Optional<Resource>  domain = udm.read(Resource.Type.DOMAIN).byUri("http://librairy.org/domains/4830d78e-a6c4-440a-951c-6afe080d41f4");
        LOG.info("Domain: " + domain);
    }


}
