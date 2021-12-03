package com.alex.top.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@RequiredArgsConstructor
@Document(collection = "celebs")
public class Celebrity {

    @Id
    private String id;

    private String name;

    // TODO we can use data structure with formatting
    private String dob;

    private Gender gender;

    // TODO we can specify list of exist jobs to extend in future and propose to order
    private List<String> jobs;

    private String imageURI;

}
