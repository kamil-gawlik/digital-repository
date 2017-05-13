package com.digitalrepository.web.rest.util;

import com.digitalrepository.domain.schemaorg.enums.*;
import org.apache.commons.lang.enums.Enum;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Piotrek on 13.05.2017.
 */
public class SchemaOrgTagsSelector {

    private List<String> tags;
    public SchemaOrgTagsSelector(CitationType citationType) {
        tags = new LinkedList<>();
        switch (citationType) {
            case Article: {
                for (ArticleTags tag : ArticleTags.values())
                    tags.add(tag.toString());
            }
            case AudioObject: {
                for (AudioObjectTags tag : AudioObjectTags.values())
                    tags.add(tag.toString());
            }
            case Book: {
                for (BookTags tag : BookTags.values())
                    tags.add(tag.toString());
            }
            case CreativeWork: {
                for (CreativeWorkTags tag : CreativeWorkTags.values())
                    tags.add(tag.toString());
            }
            case ImageObject: {
                for (ImageObjectTags tag : ImageObjectTags.values())
                    tags.add(tag.toString());
            }
            case MusicRecording: {
                for (MusicRecordingTags tag : MusicRecordingTags.values())
                    tags.add(tag.toString());
            }
            case SoftwareSourceCode: {
                for (SoftwareSourceCodeTags tag : SoftwareSourceCodeTags.values())
                    tags.add(tag.toString());
            }
            case VideoObject: {
                for (VideoObjectTags tag : VideoObjectTags.values())
                    tags.add(tag.toString());
            }
        }

    }
    public List<String> getTags() {
        return this.tags;
    }
}
