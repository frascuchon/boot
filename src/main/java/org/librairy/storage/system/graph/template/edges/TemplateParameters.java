package org.librairy.storage.system.graph.template.edges;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by cbadenes on 09/03/16.
 */
public class TemplateParameters {

    @Getter
    private final org.librairy.model.domain.relations.Relation relation;

    @Getter
    Map<String,Object> params;

    Map<String,Integer> labels;

    private Integer counter;

    public TemplateParameters(org.librairy.model.domain.relations.Relation relation){
        params = new HashMap<>();
        labels = new HashMap<>();
        counter = 5;

        this.relation = relation;
        params.put("0",relation.getStartUri());
        params.put("1",relation.getEndUri());
        params.put("2",relation.getUri());
        params.put("3",relation.getCreationTime());
        params.put("4",relation.getWeight());
    }


    public TemplateParameters add(String label, Object value){
        params.put(String.valueOf(counter),value);
        labels.put(label,counter);
        counter++;
        return this;
    }


    public String toExpression(){
        return labels.keySet().stream().map(key -> new StringBuilder().append(key).append(" : ").append("{").append(labels.get(key)).append("}")).collect(Collectors.joining(", "));
    }

}
