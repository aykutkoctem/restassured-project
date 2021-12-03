package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter @Setter @NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Places {
/*
  - name as String
     *      - postCode as int
     *      - latitude as float
     *      - longitude as float
     *      {
     *             "place name": "Fairfax",
     *             "longitude": "-77.3242",
     *             "post code": "22030",
     *             "latitude": "38.8458"
     *         }
 */
@JsonProperty("place name")
private String placeName;
private float longitude;
@JsonProperty("post code")
private int postCode;
    private float latitude;

}
