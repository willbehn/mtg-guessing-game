package com.willbehn.mtg.model.hints;

import java.util.List;

public record CardHintResponse(String cardName, String imageUri, List<Hint> hints) {}
