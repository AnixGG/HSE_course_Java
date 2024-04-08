package third_practice;

import java.io.IOException;
import java.time.LocalDate;

public class Date {
    private final int year;
    private final int month;
    private final int day;

    Date(String date) throws IOException {
        this.day = Integer.parseInt(date.substring(0, 2)); // дд/мм/гггг
        this.month = Integer.parseInt(date.substring(3, 5));
        this.year = Integer.parseInt(date.substring(6, 10));

        LocalDate now = LocalDate.now();
        int now_year = now.getYear();
        int now_month = now.getMonthValue();
        int now_day = now.getDayOfMonth();
        if (this.day < 1 || this.day > 31 || this.month < 0 || this.month > 12 || now_year < this.year){
            throw new IOException("Invalid date");
        }
        if (this.day > 30 && (this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11)){
            throw new IOException("Invalid date");
        }
        if (this.month == 2 && (this.day==29 && this.year % 4 != 0 || this.day>=30)){
            throw new IOException("Invalid date");
        }
        if (now_year == this.year) {
            if (now_month < this.month || (now_month == this.month && now_day < this.day)) {
                throw new IOException("Invalid date");
            }
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
