package com.myapp.battleofcards;

public class Human extends Player {
    InputProvider inputProvider = new InputProvider();

    Human (String name){
        super(name);
    }

    @Override
    public String chooseStat() {
            String input = "";
            String[] statsOptions = { "Max speed", "Acceleration", "Horse power", "Engine"};
            System.out.println("Choose one of following stats to compare with other players: \n" +
                                "0: Max speed \n" +
                                "1: Acceleration \n" +
                                "2: Horse power \n" +
                                "3: Engine");
            while (input.length() == 0 || !("0123".contains(input))) {
                input = inputProvider.getIntInput("How do you wanna play this round?");
            }
            final String stats = statsOptions[Integer.parseInt(input)];

        return stats;
    }
}
