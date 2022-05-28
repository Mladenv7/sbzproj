package com.sbz.proj.model;

import lombok.Getter;

@Getter
public enum Suit {
    SPADES(3),
    HEARTS(2),
    DIAMONDS(1),
    CLUBS(0);

    private Integer rank;

    Suit(Integer rank){
        this.rank = rank;
    }
}
