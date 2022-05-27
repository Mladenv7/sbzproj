package com.sbz.proj.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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
}
