package com.sbz.proj.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TableState {

    private List<Player> players;
    private StageName currentStage;

    private List<Card> board;

}
