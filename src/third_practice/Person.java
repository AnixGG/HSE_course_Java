package third_practice;

import java.io.IOException;
import java.time.LocalDate;

public class Person {
    private final String name;
    private final String surname;
    private final String patronymic;
    private String gender = "мужчина";
    private int full_age;
    Date birth_date;

    public Person(String name, String surname, String patronymic, String birthdate) throws IOException {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        if (patronymic.endsWith("а")) {
            this.gender = "женщина";
        }
        this.birth_date = new Date(birthdate);
        this.countFullAge();
    }

    private void countFullAge() {
        LocalDate now = LocalDate.now();
        int now_year = now.getYear();
        int now_month = now.getMonthValue();
        int now_day = now.getDayOfMonth();
        this.full_age = now_year - this.birth_date.getYear();
        if (this.birth_date.getMonth() > now_month) {
            this.full_age--;
        } else if (this.birth_date.getMonth() == now_month && this.birth_date.getDay() > now_day) {
            this.full_age--;
        }
    }

    public void printPerson() {
        System.out.printf("%s %s.%s., %s, %s", this.surname, this.name.charAt(0), this.patronymic.charAt(0), this.gender, this.full_age);
    }
}
