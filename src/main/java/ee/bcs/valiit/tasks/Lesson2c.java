package ee.bcs.valiit.tasks;

import java.util.Arrays;

public class Lesson2c {

    public static void main(String[] args) {

        //System.out.println(nextElement(10));
        //System.out.println(getSeqLength(10));
        //System.out.println(sequence3n(10, 10));
        System.out.println(Arrays.toString(sequence4n(10,20)));
    }

    // TODO
    // Täüsem lugemine: https://onlinejudge.org/external/1/100.pdf
    // Kujutame ette numbrite jada, kus juhul kui number on paaris arv siis me jagame selle 2-ga
    // Kui number on paaritu arv siis me korrutame selle 3-ga ja liidame 1. (3n+1)
    // Seda tegevust teeme me niikaua kuni me saame vastuseks 1
    // Näiteks kui sisend arv on 22, siis kogu jada oleks:
    // 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
    // Sellise jada pikkus on 16
    // Kirjutada programm, mis peab leidma kõige pikema jada pikkuse mis jääb kahe täis arvu vahele
    // Näiteks:
    // Sisend 10 20
    // Siis tuleb vaadata, kui pikk jada tuleb kui esimene numbr on 10. Järgmisen tuleb arvutada number 11 jada pikkus.
    // jne. kuni numbrini 20
    // Tagastada tuleb kõige pikem number
    // Näiteks sisendi 10 ja 20 puhul on vastus 20

    // TODO 4
    public static int[] sequence4n(int x, int y) { // int[], sest väljundiks on massiiv
                int suurimKordusteArv = x;
        for (int i = x; i <= y; i++) { // tsükkel x -> y
            if (getSeqLength(i) > getSeqLength(suurimKordusteArv)) {
                suurimKordusteArv = i;
            }
        }
        int kohti = 0;
            for (int i = x; i <= y; i++) { // tsükkel x -> y
            if (getSeqLength(i) == getSeqLength(suurimKordusteArv)) {
               kohti++; // mitu korda on maksimaalseid ühesuguseid väärtuseid
            }
        }
        int[] vastus = new int[kohti]; // uus massiiv, et salvestada maksimaalseid ühesuguseid väärtuseid
        int n = 0; //
               for (int i = x; i <= y; i++) { // tsükkel x -> y
            if (getSeqLength(i) == getSeqLength(suurimKordusteArv)) {
                vastus[n] = i; // määrab masiivi maksimaalsed väärtused
                n++; // määrab massiivi asukohtadele väärtused
            }
        }
        return vastus;
    }

    // TODO 3
    // tehke tsükkel x -> y
    // kutsuge iga väärtuse korral välja meetodit getSeqLength
    // salvestage maha kõige suurem ja funktsiooni lõpus tagastage see

    public static int sequence3n(int x, int y) {

        int suurimKordusteArv = x;
        for (int i = x; i <= y; i++) { // tsükkel x -> y
            getSeqLength(i);
            if (getSeqLength(i) > getSeqLength(suurimKordusteArv)) {
                suurimKordusteArv = i;
            }
        }
        return suurimKordusteArv;
    }

    // TODO 2
    // x = 1 ->1
    // x = 2 -> 2
    // kutsuge välja meetodit nextElement nii kaua kuni vastus tuleb 1
    // tagastage korduste arv + 1

    public static int getSeqLength(int x) {
        int kordusteArv = 1; // et salvestada korduste arvu
        for (int i = 1; x > 1; i++) { // i = 1, sest peame lugema korduseid; x > 1, sest peame jõudma 1-ni
            x = nextElement(x); // saame uue x-i väärtuse
            kordusteArv++; // suurendan 1 võrra
        }
        return kordusteArv;
    }

    // TODO 1 // TODO tagasta (return) sequence järgmine element
    // x = 1 -> 4
    // x = 2 -> 1
    // x = 3 -> 10
    public static int nextElement(int x) {
        if (x % 2 == 0) { // if kontrollib, kas on true või false // % annab jagatise jäägi ja kui jagatise jääki ei jää, siis on paaris arv
            return (x / 2); // nextElement(10) = 5
        } else {
            return (3 * x + 1); // kui x % 2 != 0, siis tee see tehe
        }
    }
}
