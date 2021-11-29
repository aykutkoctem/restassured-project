package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

@JsonIgnoreProperties(ignoreUnknown = true)
public class Driver {

//@JsonProperty to instruct jackson is not needed
    private String driverId;
    private String url;
    private String givenName;
    private String familyName;
    private String dateOfBirth;
    private String nationality;
}
