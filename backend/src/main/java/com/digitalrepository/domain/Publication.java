package com.digitalrepository.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by Adas on 2017-03-20.
 */
public class Publication {

    @Id
    public String id;
    public String author;
    public String description;

    public Publication() {
    }

    public Publication(String author, String description) {
        this.author = author;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
            "Publication[id=%s, author='%s', description='%s']",
            id, author, description);
    }
}
