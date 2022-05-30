package com.sbz.proj.model;

import lombok.*;

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

}
