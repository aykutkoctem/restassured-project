package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@ToString

@JsonIgnoreProperties(ignoreUnknown = true)

public class Character {

    @JsonProperty("char_id")
    private  int id;
    private  String name;
    private String[] occupation;
    private String status;
    private String nickname;
    private List<String> appearance;
}
