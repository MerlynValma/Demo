package ee.bcs.valiit.tasks;

import java.util.Scanner;

// TODO kasuta if/else. Ära kasuta Math librarit
public class Lesson1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sisesta, millist funktiooni tahad käivitada");
        System.out.println("1 - min");
        System.out.println("2 - max");
        int functionNum = scanner.nextInt();

        if (functionNum == 1) {
            System.out.println("Sisesta 2 arvu");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println("Min.number on " + min(a, b));

        } else if (functionNum == 2) {
            System.out.println("Sisesta 2 arvu");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println("Min.number on " + max(a, b));

        }

        System.out.println(min(1, 3)); //trükib miinimumi 1 ja 3
        System.out.println(max(1, 3));
        System.out.println(abs(1));
        System.out.println(isEven(5));
        System.out.println(min3(4, 5, 3));
        System.out.println(max3(11, 5, 10));
    }

    // TODO tagasta a ja b väikseim väärtus
    public static int min(int a, int b) {

        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    // TODO tagasta a ja b suurim väärtus
    public static int max(int a, int b) {

        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    // TODO tagasta a absoluut arv
    public static int abs(int a) {
// kui arv on väiksem, kui 0
        if (a < 0) {
            return -a; // või return a * -1;
        } else {
            return a;
        }
    }

    // TODO tagasta true, kui a on paaris arv
    // tagasta false kui a on paaritu arv
    public static boolean isEven(int a) {
        // % annab jagatise jäägi ja kui jagatise jääki ei jää, siis on paaris arv
        if (a % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    // TODO tagasta kolmest arvust kõige väiksem
    public static int min3(int a, int b, int c) {
        if (a >= b) {
            if (b >= c) {
                return c;
            } else {
                return b;
            }
        } else if (a <= c) {
            return a;
        } else {
            return c;
        }
    }

    // TODO tagasta kolmest arvust kõige suurem
    public static int max3(int a, int b, int c) {
        if (a >= b && a >= c) { // või return max(max(a, b), c);
            return a;
        } else if (b >= a && b >= c) {
            return b;
        } else {
            return c;
        }
    }
}


