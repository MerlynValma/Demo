package ee.bcs.valiit.tasks;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lesson3 {

    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(reverseString("Mari"));
        System.out.println(reverseString2("Mari"));
        System.out.println(isPrime(11));
        System.out.println(evenFibonacci(40));
        System.out.println(evenFibonacci2(40));
        System.out.println(morseCode("Merlyn"));

        int[] array = {8,1,4,10,0};
        System.out.println(Arrays.toString(sort(array)));
    }

    // TODO tagasta x faktoriaal. //
    // Näiteks
    // x = 5
    // return 5*4*3*2*1 = 120
    public static int factorial(int x) {
        int faktoriaal = 1;
        for (int i = 1; i <= x; i++) {
            faktoriaal = (faktoriaal * i); // 1 loop = 1*1; 2 loop = 1*2; 3 loop = 2*3; 4 loop = 6*4; 5 loop = 24*5 (v:120)
        }
        return faktoriaal;
    }

    // TODO tagasta string tagurpidi 1
    public static String reverseString(String a) {
        String reverse = "";
        for (int i = a.length() - 1; i >= 0; i--) {
            reverse = reverse + a.charAt(i); // The charAt() method returns the character at the specified index in a string.
            System.out.println("i asukoht: " + i + " ja täht selles kohas: " + a.charAt(i));
        }
        System.out.println("a.length väärtus: " + a.length());
        return reverse;
    }

    // TODO tagasta string tagurpidi 2
    public static String reverseString2(String a) {
        String reverse = "";
        for (int i = 0; i < a.length(); i++) {
            reverse = reverse + a.charAt(a.length() - i - 1); // i=0 korral tähemärk asukohas 4-0-1=3 ehk nime Mari puhul i
            System.out.println("i asukoht: " + i + " ja täht selles kohas: " + a.charAt(i));
        }
        System.out.println("a.length väärtus: " + a.length());
        return reverse;
    }

    // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)
    public static boolean isPrime(int x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    // TODO sorteeri massiiv suuruse järgi. Kasuta tsükleid, ära kasuta ühtegi olemasolevat sort funktsiooni
    public static int[] sort(int[] a) {
        int ajutine = 0;
        for (int i = 0; i < a.length; i++ ) { // uus loop -> otsi kõige väiksem
            for (int j = i+1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    ajutine = a[j];
                    a[j] = a[i];
                    a[i] = ajutine;
                }
            }
        }
        return a;
    }
    // TODO 1. liida kokku kõik paaris fibonacci arvud kuni numbrini x
    public static int evenFibonacci(int x) { // 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
        int total = 0;
        int[] fiboncciArray = new int[x+2]; // [x+2] teeb liiga pika Array - mis on lahendus?
        fiboncciArray[0] = 0; // x = 0, sest alates kolmndast suudab genereerida
        fiboncciArray[1] = 1; // x = 1, sest alates kolmndast suudab genereerida
        // fibonacci[2] = fibonacci[1] + fibonacci[0];
        for(int i = 2; fiboncciArray[i-1] <= x; i++) { // -1, sest i saab väärtuse aga fibonacci[i] ei ole saanud väärtuse
            fiboncciArray[i] =  fiboncciArray[i-1] + fiboncciArray[i-2];
            if (fiboncciArray[i] % 2 == 0) {
                total = total + fiboncciArray[i];
            }
        }

        return total;
    }
    // TODO 2. liida kokku kõik paaris fibonacci arvud kuni numbrini x
    public static int evenFibonacci2(int x) { // 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
        int total = 0;

        int fib0 = 0;
        int fib1 = 1;
        int fib2 = fib0 + fib1;
        for(int i = 2; fib2 <= x; i++) { // -1, sest i saab väärtuse aga fibonacci[i] ei ole saanud väärtuse
            fib0 = fib1; // liigun ühe võrra edasi
            fib1 = fib2; // liigun ühe võrra edasi
            fib2 = fib0 + fib1;
            if (fib2 % 2 == 0) {
                total = total + fib2;
            }
        }
        return total;
    }
    // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
            // Kasuta sümboleid . ja - ning eralda kõik tähed tühikuga
           //return a.trim() <- eemaldab tühiku.
    public static String morseCode(String text) { // kasutada map´i, substring //Merlyn
        HashMap<String, String> Morse = new HashMap<>(); // loon uue tühja HashMap´i
        Morse.put("L", ".-..");
        Morse.put("M", "--");
        Morse.put("E", ".");
        Morse.put("Y", "-.--");
        Morse.put("N", "-.");
        Morse.put("R", ".-.");
        String uusMorse = ""; // uus muutuja, et salvestada morsekood
        for (int i = 0; i<text.length();i++) { // loop, et
                uusMorse = uusMorse + Morse.get(Character.toString(text.toUpperCase().charAt(i))) + " "; // Stringi puhul kasutan charAt, et väljastada character asukohas i.
        } // kuna võtab ühe muutuja characteri korraga, siis vaja muuta String´iks (Character.toString).
        return uusMorse;
    }
}
