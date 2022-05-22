package com.sbz.proj.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Player {

    private Card card1, card2;
    private List<Action> action;
    private Integer money;
    private Blind blind;

}
