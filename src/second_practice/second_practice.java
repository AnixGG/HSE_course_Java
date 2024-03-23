package second_practice;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class second_practice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input file: ");
        String input = scanner.nextLine();
        System.out.print("Output file: ");
        String output = scanner.nextLine();
        try{
            new CounterLetters(new File(input), new File(output));
        } catch (IOException err){
            System.out.print(err.getMessage());
        }
    }
}
