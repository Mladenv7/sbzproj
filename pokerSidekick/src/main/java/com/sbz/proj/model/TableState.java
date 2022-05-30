package com.sbz.proj.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableState {

    public List<Player> players;
    public StageName currentStage;

    public List<Card> board;

}
