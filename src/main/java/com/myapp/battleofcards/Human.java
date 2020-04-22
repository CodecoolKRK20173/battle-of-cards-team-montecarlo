package com.myapp.battleofcards;

public class Human extends Player {
    InputProvider inputProvider = new InputProvider();

        Human (){
            super();
        }

    @Override
    public String chooseStat() {
            System.out.println("Possibilities: \n 1.Max speed \n 2.Acceleration \n 3.Horse power \n  4.Engine");
            final String stats = inputProvider.getIntInput("How do you wanna play this round?");
            return stats;
    }
}
