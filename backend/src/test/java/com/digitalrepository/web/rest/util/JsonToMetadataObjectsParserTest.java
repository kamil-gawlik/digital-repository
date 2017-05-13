package com.digitalrepository.web.rest.util;

import com.digitalrepository.domain.ReceivedCitation;
import com.digitalrepository.domain.ReceivedRecordHeader;
import com.digitalrepository.domain.SchemaOrgPerson;
import com.digitalrepository.domain.schemaorg.enums.CitationType;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Piotrek on 11.05.2017.
 */
public class JsonToMetadataObjectsParserTest {

    private static final SchemaOrgPerson creator = new SchemaOrgPerson("givenName",
        "additionalName",
        "familyName");

    private static final ReceivedRecordHeader header = new ReceivedRecordHeader("name",
        "about",
        "author",
        "description", creator
       );

    private static final ReceivedCitation citation = new ReceivedCitation(CitationType.CreativeWork,
        "name",
        "about",
        "author",
        creator,
        "fileFormat",
        "isbn",
        "numberOfPages",
        "bookEdition",
        "articleSection",
        "codeRepository",
        "programmingLanguage",
        "runtimePlatform",
        "byArtist",
        "inAlbum",
        "duration",
        creator,
        creator,
        "caption");

    private static final String citationListJsonString = "[\n" +
      citation.toString()+",\n" +
        citation.toString() + "\n" +
        "]";

    @Test
    public void shouldReturnValidReceivedRecordHeader () {
        JsonToMetadataObjectsParser parser = new JsonToMetadataObjectsParser();

        try {
            parser.setRecordHeader(header.toString());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(header, parser.getReceivedRecordHeader());
    }

    @Test
    public void shouldReturnValidReceivedCitationList() {
        JsonToMetadataObjectsParser parser = new JsonToMetadataObjectsParser();

        try {
            parser.setCitationList(citationListJsonString);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        List<ReceivedCitation> receivedCitationList = parser.getCitationList();
        assertEquals(citation,receivedCitationList.get(0));
        assertEquals(citation,receivedCitationList.get(1));
    }
}
