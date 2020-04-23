package com.myapp.battleofcards;

import java.util.Scanner;

public class InputProvider {
    Scanner scanner = new Scanner(System.in);

    public String getIntInput(String message) {
        System.out.println(message);
        String input = scanner.next();
        while (!("01234567891011121314151617181920212223245010015020025050075010001500".contains(input))){System.out.println(message);input = scanner.next();}
        return input;
    }
}
