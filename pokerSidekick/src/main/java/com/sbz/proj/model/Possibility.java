package com.sbz.proj.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Possibility {
    public List<Card> cards; // 5 karata
    public PokerHand pokerHand; // sta imamo

    public Possibility(List<Card> cards) {
        // DROOOOOOOOOOOOLS
    }

    public boolean sameSuit() {
        return cards.stream().filter(c -> c.getSuit() != cards.get(0).getSuit()).count() == 0;
    }

}
