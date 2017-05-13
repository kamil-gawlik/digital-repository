package com.digitalrepository.web.rest.util;

import com.digitalrepository.domain.*;
import com.digitalrepository.domain.schemaorg.enums.CitationType;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;

/**
 * Created by Piotrek on 13.05.2017.
 */
public class SchemaOrgHeaderFactoryTest {

    private static final SchemaOrgPerson creator = new SchemaOrgPerson("givenName",
        "additionalName",
        "familyName");

    private static final ReceivedRecordHeader header = new ReceivedRecordHeader("name",
        "about",
        "author",
        "description",
        creator
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

    @Test
    public void shouldReturnValidSchemaOrgHeader() {
        LinkedList<ReceivedCitation> receivedCitations = new LinkedList<>();
        receivedCitations.add(citation);
        receivedCitations.add(citation);

        MultipartFile file1 = Mockito.mock(MockMultipartFile.class);
        Mockito.when(file1.getOriginalFilename()).thenReturn("file1.pdf");

        MultipartFile file2 = Mockito.mock(MockMultipartFile.class);
        Mockito.when(file2.getOriginalFilename()).thenReturn("file2.java");

        LinkedList<MultipartFile> files = new LinkedList<>();
        files.add(file1);
        files.add(file2);

        try {
            SchemaOrgHeader schemaOrgHeader = SchemaOrgHeaderFactory.createSchemaOrgHeader(header,receivedCitations,files);
            System.out.print(schemaOrgHeader);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }
}
