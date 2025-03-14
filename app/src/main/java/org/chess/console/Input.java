package org.chess.console;

import org.chess.utils.StringUtils;

import java.util.Scanner;

public class Input {

    private final Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public boolean suggestGame() {
        System.out.println("Enter Start and End");
        while (true) {
            try {
                String cmd = scanner.nextLine();
                if (cmd.equalsIgnoreCase("end")) {
                    return false;
                }

                if (cmd.equalsIgnoreCase("start")) {
                    return true;
                }
                throw new IllegalArgumentException("only start and exit");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String[] getMoves() {
        System.out.println("Enter Move");
        while (true) {
            try {
                System.out.println("Example: move a2 a3");
                String cmd = scanner.nextLine();

                if (cmd.startsWith("move ")) {
                    return StringUtils.getMovePositions(cmd);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
