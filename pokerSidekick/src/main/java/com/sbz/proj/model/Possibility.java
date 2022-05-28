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
    public Card card1, card2, card3, card4, card5;
    public PokerHand pokerHand; // sta imamo
    public boolean weKnow;

    public Possibility(List<Card> cards, PokerHand pokerHand) {
        this.cards = cards.stream().sorted(Card::compareTo).collect(Collectors.toList());
        this.card1 = cards.get(0);
        this.card2 = cards.get(1);
        this.card3 = cards.get(2);
        this.card4 = cards.get(3);
        this.card5 = cards.get(4);

        this.pokerHand = pokerHand;
        this.weKnow = true;
    }

    public boolean sameSuit() {
        return cards.stream().filter(c -> c.getSuit() != cards.get(0).getSuit()).count() == 0;
    }

    public long howManyWithRank(Rank rank) {
        return cards.stream().filter(c -> c.getRank() == rank).count();
    }

    public long howManyWithSuit(Suit suit) {
        return cards.stream().filter(c -> c.getSuit() == suit).count();
    }

    public List<Suit> getAllSuits() {
        return cards.stream().map(Card::getSuit).collect(Collectors.toList());
    }

    public void setupCards() {
        this.cards = cards.stream().sorted(Card::compareTo).collect(Collectors.toList());
        this.card1 = cards.get(0);
        this.card2 = cards.get(1);
        this.card3 = cards.get(2);
        this.card4 = cards.get(3);
        this.card5 = cards.get(4);
    }

}
