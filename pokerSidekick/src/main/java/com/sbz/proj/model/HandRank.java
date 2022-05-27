package com.sbz.proj.model;

public enum HandRank {
    ROYAL_FLUSH(10),
    STRAIGHT_FLUSH(9),
    FOUR_OF_A_KIND(8),
    FULL_HOUSE(7),
    FLUSH(6),
    STRAIGHT(5),
    THREE_OF_A_KIND(4),
    TWO_PAIRS(2),
    PAIR(1),
    HIGH_CARD(0);

    public Integer strength;

    HandRank(Integer strength){
        this.strength = strength;
    }
}
