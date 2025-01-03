package com.willbehn.mtg.model.symbols;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Symbol {
    @JsonProperty("symbol")
    public String symbol;

    @JsonProperty("svg_uri")
    public String symbolUri;

    @JsonProperty("enlgish")
    public String description;


    @Override
    public String toString(){
        return  "Symbol {\n " +
                "    symbol: " + symbol + "\n" +
                "    symbolUri: " + symbolUri + "\n" + 
                "    description: " + description + "\n" +
                "}\n";           
    }
}
