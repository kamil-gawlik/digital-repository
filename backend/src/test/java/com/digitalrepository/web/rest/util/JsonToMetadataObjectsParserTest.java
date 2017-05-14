package com.digitalrepository.web.rest.util;

import com.digitalrepository.domain.CitationMetadata;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Piotrek on 11.05.2017.
 */
public class JsonToMetadataObjectsParserTest {

    private static final String creator = "{\"givenName\":\"givenName\",\"additionalName\":\"additionalName\",\"familyName\":\"familyName\"}";

    private static final String header = "{\"name\":\"name\",\"about\":\"about\",\"author\":\"author\",\"description\":\"description\", \"creator\":"+creator+"}";

    private static final String creativeWork = "{\"@type\":\"CreativeWork\",\"name\":\"name\",\"about\":\"about\",\"author\":\"author\", \"creator\":{\"givenName\":\"givenName\",\"additionalName\":\"additionalName\",\"familyName\":\"familyName\"}}";

    private static final String book = "{\"@type\":\"Book\",\"name\":\"name\",\"about\":\"about\",\"author\":\"author\",\"creator\":{\"givenName\":\"givenName\",\"additionalName\":\"additionalName\",\"familyName\":\"familyName\"},\"isbn\":\"isbn\",\"numberOfPages\":\"numberOfPages\",\"bookEdition\":\"bookEdition\"}";

    private static final String filesMetadata = "["+creativeWork+","+book+"]";

    private static final String result = "[{\"@context\":\"http://schema.org\",\"@type\":\"CreativeWork\",\"name\":\"name\",\"about\":\"about\",\"author\":\"author\",\"creator\":{\"@context\":\"http://schema.org\",\"@type\":\"Person\",\"givenName\":\"givenName\",\"additionalName\":\"additionalName\",\"familyName\":\"familyName\"}},{\"@context\":\"http://schema.org\",\"@type\":\"Book\",\"name\":\"name\",\"about\":\"about\",\"author\":\"author\",\"creator\":{\"@context\":\"http://schema.org\",\"@type\":\"Person\",\"givenName\":\"givenName\",\"additionalName\":\"additionalName\",\"familyName\":\"familyName\"},\"isbn\":\"isbn\",\"numberOfPages\":\"numberOfPages\",\"bookEdition\":\"bookEdition\"}]";

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
            parser.setCitationList(filesMetadata);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        List<CitationMetadata> receivedCitationList = parser.getCitationList();
        String res = "[";
        int i = 0;
        for (CitationMetadata metadata : receivedCitationList) {
            String s = metadata.toString();
            s = s.trim().replaceAll("\\s+","");
            res += s;
            if (i<receivedCitationList.size()-1)
                res += ",";
            i++;
        }
        res += "]";
        String s = result.trim().replaceAll("\\s+","");
        assertEquals(result, res);
    }
}
