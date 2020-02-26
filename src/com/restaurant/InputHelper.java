package com.restaurant;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {
    Scanner input = new Scanner(System.in);

    public int readInt() {
        while (true) {
            int number = 0;
            try {
                number = input.nextInt();
                this.throwIfLessThan1(number);
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("wrong input.. try again!");
                continue;
            }
            return number;
        }
    }

    private void throwIfLessThan1(int number) {
        if (number < 1)
            throw new InputMismatchException();
    }
}
