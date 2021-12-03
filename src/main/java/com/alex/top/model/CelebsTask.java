package com.alex.top.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "task")
public class CelebsTask {

    @Id
    private String id;

    private String resetTop;
}
