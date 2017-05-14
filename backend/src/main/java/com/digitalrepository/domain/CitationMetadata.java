package com.digitalrepository.domain;

import com.digitalrepository.domain.schemaorg.enums.CitationType;
import com.digitalrepository.web.rest.util.SchemaOrgTagsSelector;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Piotrek on 14.05.2017.
 */
public class CitationMetadata {

    private DBObject metadata;
    private List<String> tagsList;
    private List<String> addedTags;

    public CitationMetadata(CitationType type) {
        this.metadata = new BasicDBObject();
        this.metadata.put("@context", "http://schema.org");
        this.metadata.put("@type", type.toString());
        this.addedTags = new LinkedList<>();
        this.addedTags.add("@context");
        this.addedTags.add("@type");

        SchemaOrgTagsSelector selector = new SchemaOrgTagsSelector(CitationType.CreativeWork);
        this.tagsList = selector.getTags();
        if (type != CitationType.CreativeWork) {
            selector = new SchemaOrgTagsSelector(type);
            this.tagsList.addAll(selector.getTags());
        }
        this.tagsList.add("fileName");
        this.tagsList.add("recordName");
        this.tagsList.add("recordId");
    }

    public DBObject getMetadata() {
        return metadata;
    }

    public String getType() {
        return (String) this.metadata.get("@type");
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
        if (value != null) {
            this.addedTags.add(key);
        }
    }

    public Object getValue(String key) throws Exception {
        if (!isValidTag(key))
            throw new Exception(CitationMetadata.class.getName() + ": invalid tag in getValue() method!");
        return this.metadata.get(key);
    }

    public List<String> getTagsList() {
        return tagsList;
    }

    @Override
    public String toString() {
        String result = "{\n";
        int i = 0;
        for (String tag : this.addedTags) {
            Object o = this.metadata.get(tag);
            if (o != null) {
                if (i !=  0)
                    result+=",\n";
                if ("exifData".equals(tag) || "creator".equals(tag) || "actor".equals(tag) || "director".equals(tag))
                    result += "\"" + tag + "\":" + o.toString();
                else
                    result += "\"" + tag + "\":\"" + o + "\"";
            }
            i++;
        }

        result += "\n}";
        return result;
    }
}
