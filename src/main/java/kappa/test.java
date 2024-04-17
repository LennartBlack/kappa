package kappa;

import java.time.LocalDateTime;

public class test {

    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);
        date = date.plusMonths(3);
        System.out.println(date);

    }

}
