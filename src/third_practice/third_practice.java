package third_practice;

import java.io.IOException;
import java.util.Scanner;

public class third_practice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Имя, фамилия, отчество: ");
        String name = scanner.next();
        String surname = scanner.next();
        String patronymic = scanner.next();
        System.out.print("Год рождения (дд/мм/гггг): ");
        String input_date = scanner.next();
        try{
            Person person = new Person(name, surname, patronymic, input_date);
            person.printPerson();
        } catch (IOException err){
            System.out.print(err.getMessage());
        }
        scanner.close();
    }
}
