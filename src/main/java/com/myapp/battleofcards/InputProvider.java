package com.myapp.battleofcards;

import java.util.Scanner;

public class InputProvider {
    Scanner scanner = new Scanner(System.in);

    public int getIntInput(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }
}
