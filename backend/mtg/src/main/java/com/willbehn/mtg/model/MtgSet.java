package com.willbehn.mtg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MtgSet {
    
    @JsonProperty("name")
    public String name;

    @JsonProperty("code")
    public String setCode;

    @JsonProperty("released_at")
    public String releasedAt;

    @JsonProperty("icon_svg_uri")
    public String iconUri;


    @Override
    public String toString(){
        return  "Set {\n " +
                "    name: " + name + "\n" +
                "    releasedAt: " + releasedAt + "\n" + 
                "    iconUri: " + iconUri + "\n" +
                "}\n";           
    }

}
