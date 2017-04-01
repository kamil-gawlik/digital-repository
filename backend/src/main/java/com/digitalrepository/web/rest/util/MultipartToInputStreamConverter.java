package com.digitalrepository.web.rest.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by Piotrek on 31.03.2017.
 */
public class MultipartToInputStreamConverter {
    public static InputStream convert (List<MultipartFile> filesList) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for(MultipartFile file : filesList)
            baos.write(file.getBytes());

        return new ByteArrayInputStream(baos.toByteArray());
    }
}
