package com.willbehn.mtg.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) 
public class Card {

    @JsonProperty("object")
    private String object;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("mana_cost")
    private String manaCost;

    @JsonProperty("cmc")
    private Double cmc;

    @JsonProperty("type_line")
    private String typeLine;

    @JsonProperty("oracle_text")
    private String oracleText;

    @JsonProperty("power")
    private String power;

    @JsonProperty("rarity")
    private String rarity;

    @JsonProperty("toughness")
    private String toughness;

    @JsonProperty("colors")
    private List<String> colors;

    @JsonProperty("image_uris")
    private ImageUris imageUris;

    @JsonProperty("keywords")
    private List<String> keywords;

    @JsonProperty("edhrec_rank")
    private int edhrecRank;

    @JsonProperty("released_at")
    private String releasedAt;

    @JsonProperty("set_name")
    private String setName;

    @JsonProperty("set")
    private String setCode;

    public String getNormalImageUrl() {
        return imageUris != null ? imageUris.getNormal() : null;
    }

    @Override
    public String toString() {
    return "Card {\n" +
           "    id='" + id + "'\n" +
           "    name='" + name + "'\n" +
           "    manaCost='" + manaCost + "'\n" +
           "    cmc=" + cmc + "\n" +
           "    typeLine='" + typeLine + "'\n" +
           "    oracleText='" + oracleText + "'\n" +
           "    power='" + power + "'\n" +
           "    toughness='" + toughness + "'\n" +
           "    colors=" + colors + "\n" +
           "    releasedAt: " + releasedAt + "\n"+
           "    setName:  " + setName + "\n"+
           "    rarity: " + rarity+ "\n" + 
           "}\n";
    }
}
