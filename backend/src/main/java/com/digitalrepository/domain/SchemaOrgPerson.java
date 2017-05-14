package com.digitalrepository.domain;

import com.digitalrepository.domain.schemaorg.enums.PersonTags;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Piotrek on 10.05.2017.
 */
public class SchemaOrgPerson {

    private DBObject metadata;
    private final List<String> tagsList;
    private final List<String> addedTagsList;

    public SchemaOrgPerson() {
        this.metadata = new BasicDBObject();
        this.tagsList = new LinkedList<>();
        this.addedTagsList = new LinkedList<>();
        this.addedTagsList.add("@context");
        this.addedTagsList.add("@type");
        this.metadata.put("@context", "http://schema.org");
        this.metadata.put("@type", "Person");
        for (PersonTags tag : PersonTags.values()) {
            tagsList.add(tag.toString());
        }

    }

//    public SchemaOrgPerson(String givenName, String additionalName, String familyName) {
//        this();
//        this.metadata.put("givenName", givenName);
//        this.metadata.put("additionalName", additionalName);
//        this.metadata.put("familyName", familyName);
//    }

    public List<String> getTagsList() {
        return tagsList;
    }

    public DBObject getMetadata() {
        return this.metadata;
    }

    private boolean isValidTag(String tag) {
        for (String t : this.tagsList) {
            if (t.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    public void put(String key, Object value) throws Exception {
        if (!isValidTag(key))
            throw new Exception(CitationMetadata.class.getName() + ": invalid tag in put() method!");
        this.metadata.put(key,value);
        this.addedTagsList.add(key);
    }

    @Override
    public String toString() {
        String result = "{\n";
        int i = 0;
        for (String tag : this.addedTagsList) {
            Object o = this.metadata.get(tag);
            if(o.getClass() != String.class)
                result += "\"" + tag + "\":" + o.toString();
            else
                result += "\"" + tag + "\":\"" + o + "\"";
            if (i < this.addedTagsList.size()-1)
                result += ",\n";
            i++;
        }

        result += "\n}";
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchemaOrgPerson that = (SchemaOrgPerson) o;

        return metadata != null ? metadata.equals(that.metadata) : that.metadata == null;
    }
}
