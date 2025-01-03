package com.willbehn.mtg.model.cards;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
    public class ImageUris {
        @JsonProperty("small")
        private String small;

        @JsonProperty("normal")
        private String normal;

        @JsonProperty("large")
        private String large;

        @JsonProperty("png")
        private String png;

        @JsonProperty("art_crop")
        private String artCrop;

        @JsonProperty("border_crop")
        private String borderCrop;

        String getNormal(){
            return normal;
        }

}
