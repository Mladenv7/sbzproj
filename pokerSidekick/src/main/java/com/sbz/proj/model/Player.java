package com.sbz.proj.model;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Player {

    private Card card1, card2;
    private List<Action> action;
    private Integer money;
    private Blind blind;


    public Double moneyRatio(Double otherPot){
        BigDecimal bd = new BigDecimal(Double.toString(otherPot/this.money));
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
