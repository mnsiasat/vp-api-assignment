package com.vp.api.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
@Data
@Builder
public class Movie{
    @Id
    private String id;
    private String title;
    private String image;
    private String comment;
    private boolean watched;
}
