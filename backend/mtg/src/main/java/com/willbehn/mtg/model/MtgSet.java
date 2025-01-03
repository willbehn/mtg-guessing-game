package com.willbehn.mtg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MtgSet {
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String setCode;

    @JsonProperty("released_at")
    private String releasedAt;

    @JsonProperty("icon_svg_uri")
    private String iconUri;


    @Override
    public String toString(){
        return  "Set {\n " +
                "    name: " + name + "\n" +
                "    releasedAt: " + releasedAt + "\n" + 
                "    iconUri: " + iconUri + "\n" +
                "}\n";           
    }

}
