package ee.bcs.valiit.tasks;

import java.util.Arrays;

public class Lesson2b {
    public static void main(String[] args) {
        exercise1(5);
        System.out.println();
        System.out.println(Arrays.toString(sampleArray()));
        System.out.println(Arrays.toString(generateArray(5)));
        System.out.println(Arrays.toString(decreasingArray(-3)));
        System.out.println(Arrays.toString(yl3(10)));
    }

    // TODO trüki välja n arvu
    // näiteks
    // sisend: 5
    // trüki välja: 1 2 3 4 5
    public static void exercise1(int n) { //kõige tavalisel tsükkel (for)
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " ");
        }
    }

    // TODO tagasta massiiv milles oleks numbrid 1,2,3,4,5
    public static int[] sampleArray() {
        int[] uusMassiiv = new int[5]; //lihtsaim variant on -> return new int[] {1,2,3,4,5};
        for (int i = 0; i < uusMassiiv.length; i++) { // i = 0 on loopi/tsükli algus; i++ läheb uuele ringile/tsüklisse
            uusMassiiv[i] = (i + 1); // Array esimene väärtus jadas, näiteks 0+1=1
        }
        return uusMassiiv;
    }

    // TODO loo massiiv mis saab sisendiks n ja tagastab massiivi suurusega n
    // Kus esimene element on 1 ja iga järnev arv on 1 võrra suurem
    // näiteks:
    // sisend: 5
    // vastus: {1, 2, 3, 4, 5}
    public static int[] generateArray(int n) {
        int[] newMassiiv = new int[n];
        for (int i = 0; i < newMassiiv.length; i++) {
            newMassiiv[i] = (i + 1);
        }
        return newMassiiv;
    }

    // TODO
    // Tagastada massiiv kus oleks numbrid n-ist 0 ini
    // Näiteks: sisend: 5
    // Väljund: 5, 4, 3, 2, 1, 0

    // Näide2: siend: -3
    // Väljund: -3, -2, -1, 0
    public static int[] decreasingArray(int n) {

        if (n > 0) { //sest kõigepealt positiivne tsükkel
            int[] decrease = new int[n+1]; //n+1, sest massiivis on 6 kohta
            for (int i = 0; i <= n; i++) {
                decrease[i] = (n - i);
            }
            return decrease;
        } else {
            int[] decrease = new int[-n+1]; //-n+1, sest massiivis on 4 kohta
            for (int i = 0; i <= -n; i++) {
                decrease[i] = (n + i);
            }
            return decrease;
        }
    }
        // TODO
        // tagasta massiiv pikkusega n, mille kõigi elementide väärtused on 3
        public static int[] yl3 (int n){
        int[] masiiv = new int[n];
        for (int i = 0; i < n; i++) {
            masiiv[i] = 3;
        }
            return masiiv;
        }
    }