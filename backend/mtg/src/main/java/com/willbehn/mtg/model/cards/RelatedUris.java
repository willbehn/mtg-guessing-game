package com.willbehn.mtg.model.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class RelatedUris {
        @JsonProperty("edhrec")
        private String edhrec;

    
        String getEdhUri(){
            return edhrec;
        }

}