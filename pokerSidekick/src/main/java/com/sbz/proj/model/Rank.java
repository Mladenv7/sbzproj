package com.sbz.proj.model;

public enum Rank {
    ACE(15),
    KING(14),
    QUEEN(13),
    JACK(12),
    R10(10),
    R9(9),
    R8(8),
    R7(7),
    R6(6),
    R5(5),
    R4(4),
    R3(3),
    R2(2);

    public Integer rank;

    Rank(Integer rank){
        this.rank = rank;
    }
}
