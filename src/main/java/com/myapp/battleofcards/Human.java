package com.myapp.battleofcards;

public class Human extends Player {
    InputProvider inputProvider = new InputProvider();

        Human (){
            super();
        }

    @Override
    public String chooseStat() {
            System.out.println("Possibilities: Max speed, Acceleration, Horse power, Engine");
            final String stats = inputProvider.getIntInput("How do you wanna play this round?");
            return stats;
    }
}
