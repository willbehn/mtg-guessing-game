package com.willbehn.mtg.model.cards;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//TODO set variables to private after testing
@JsonIgnoreProperties(ignoreUnknown = true) 
public class Card {

    @JsonProperty("object")
    public String object;

    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("mana_cost")
    public String manaCost;

    @JsonProperty("cmc")
    public Double cmc;

    @JsonProperty("type_line")
    public String typeLine;

    @JsonProperty("oracle_text")
    public String oracleText;

    @JsonProperty("power")
    public String power;

    @JsonProperty("rarity")
    public String rarity;

    @JsonProperty("toughness")
    public String toughness;

    @JsonProperty("colors")
    public List<String> colors;

    @JsonProperty("image_uris")
    public ImageUris imageUris;

    @JsonProperty("keywords")
    public List<String> keywords;

    @JsonProperty("edhrec_rank")
    public int edhrecRank;

    @JsonProperty("released_at")
    public String releasedAt;

    @JsonProperty("set_name")
    public String setName;

    @JsonProperty("set")
    public String setCode;

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
