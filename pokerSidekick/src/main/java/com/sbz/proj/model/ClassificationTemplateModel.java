package com.sbz.proj.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassificationTemplateModel {

    private PokerHand hand;
    private Rank card_1_rank, card_2_rank, card_3_rank, card_4_rank, card_5_rank;
    private boolean sameSuit;

}
