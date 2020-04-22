package com.myapp.battleofcards;

import java.util.Random;

public class Computer extends Player {

    Random random = new Random();

    Computer (){
        super();
    }

    @Override
    public String chooseStat() {
        String[] statsOptions = { "Max speed", "Acceleration", "Horse power", "Engine"};
        final String stats = statsOptions[random.nextInt(4)];
        return stats;
    }
}
