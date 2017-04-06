package com.digitalrepository.web.rest.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Class with static method for converting MultipartFiles to Files
 * Created by Piotrek on 06.04.2017.
 */
public class MultipartToFileConverter {
    public static File convert(MultipartFile multipart) throws IllegalStateException, IOException
    {
        File convFile = new File( multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
}
