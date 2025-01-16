package com.willbehn.mtg.model.hints;

import java.time.LocalDate;
import java.util.List;

import com.willbehn.mtg.model.symbols.SymbolList;

public record CardHintResponse(String cardName, String edhrecUrl,String imageUri,LocalDate date, List<Hint> hints, SymbolList currentSymbols) {}
