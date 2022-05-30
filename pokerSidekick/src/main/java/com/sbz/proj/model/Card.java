package com.sbz.proj.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card implements Comparable<Card>{
    private Rank rank;
    private Suit suit;


    @Override
    public int compareTo(Card o) {
        if (this.rank.rank > o.rank.rank) {
            return 1;
        } else if (this.rank.rank == o.rank.rank) {
            if (this.suit.getRank() > o.suit.getRank())
                return 1;
            else if (this.suit.getRank() == o.suit.getRank())
                return 0;
            else return -1;
        } else {
            return -1;
        }
    }

    public static int compareLists(List<Card> list1, List<Card> list2) {

        list1 = list1.stream().sorted(Card::compareTo).collect(Collectors.toList());
        list2 = list2.stream().sorted(Card::compareTo).collect(Collectors.toList());

        boolean sameCards = true;
        for (int i = 0; i < 5; i++) {
            if (list1.get(i) != list2.get(i)) {
                sameCards = false;
                break;
            }
        }
        if (sameCards) return 0;

        if (list1.get(4).compareTo(list2.get(4)) == 0)
            if (list1.get(3).compareTo(list2.get(3)) == 0)
                if (list1.get(2).compareTo(list2.get(2)) == 0)
                    if (list1.get(1).compareTo(list2.get(1)) == 0)
                        return list1.get(0).compareTo(list2.get(0));
                    else
                        return list1.get(1).compareTo(list2.get(1));
                else
                    return list1.get(2).compareTo(list2.get(2));
            else
                return list1.get(3).compareTo(list2.get(3));
        else
            return list1.get(4).compareTo(list2.get(4));
    }

    @Override
    public String toString() {
        String rank;
        if (this.rank.getRank() > 10) rank = String.valueOf(this.rank.name().charAt(0));
        else rank = this.rank.name().split("R")[1];
        String suit = String.valueOf(this.suit.name().charAt(0));
        return rank + suit;
    }

    public static void logCards(Logger LOGGER, List<Card> cards) {
        LOGGER.info("\u001B[33m==========================================\u001B[0m");
        LOGGER.info("CARDS: " + cards.toString());
        LOGGER.info("-  -   -   -   -   -   -   -   -   -   -");
    }
}
