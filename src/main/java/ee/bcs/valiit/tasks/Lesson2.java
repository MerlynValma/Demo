package ee.bcs.valiit.tasks;

import java.util.Arrays;

public class Lesson2 {

    public static int[] reverseArray;

    public static void main(String[] args) {
        // TODO siia saab kirjutada koodi testimiseks
        int returnArray[] = {1,2,3,4};
        System.out.println(Arrays.toString(reverseArray(returnArray)));
        System.out.println(Arrays.toString(evenNumbers(5)));

        int uusArray[] = {90,10,50,5};
        System.out.println(min(uusArray));

        int maxArray[] = {1,2,-1,5};
        System.out.println(max(maxArray));

        int ElementideSumma[] = {1,2,3,4,5};
        System.out.println(sum(ElementideSumma));

        multiplyTable(3, 3);
        System.out.println(fibonacci(10));

     //   System.out.println(sequence3n(10));

    }

        // TODO loe funktsiooni sisendiks on täisarvude massiiv
    // TODO tagasta massiiv mille elemendid on vastupidises järiekorras


    public static int[] reverseArray(int[] inputArray) {
        int[] uusArray = new int[inputArray.length];
        // for´i funktsiooni sees määratakse abimuutuja algväärtus; käsu kestvus; käsu tegevus
        for (int i = 0; i < inputArray.length; i++) { //ajutine muutuja; näitab kui kaua for käsku täidetakse; suurenda i väärtust 1x
            uusArray[i] = inputArray[inputArray.length-i-1];
        }
         return uusArray;
    }
// length = 4;
// For käsk hetkel teeb Arrays nii:
//    uusArray[0] = inputArray[3]; // i = 0
//    uusArray[1] = inputArray[2]; // i = 1
//    uusArray[2] = inputArray[1]; // i = 2
//    uusArray[3] = inputArray[0]; // i = 3

    // TODO tagasta massiiv mis sisaldab n esimest paaris arvu
    // Näide:
    // Sisend 5
    // Väljund 2 4 6 8 10
    public static int[] evenNumbers(int n) { //defineerin funktsiooni
        int[] paarisArv = new int[n]; //Array puhul vaja kasutada new

        for (int i = 0; i < n; i++) { // teeme for tsükli, sest meil on kindel algus ja lõpp
            paarisArv[i] = (i*2+2); // alustab kahest, sest enne paaritud arvud
        }
        return paarisArv;
    }

    // TODO, leia massiivi kõige väiksem element
    public static int min(int[] x) { // x tähistab, et Array on juba loodud
        int minElement = x[0]; //minElement on muutuja funktsiooni sees
        for (int i = 1; i < x.length; i++) {
            if (x[i] < minElement) {
                minElement = x[i];
            }
        }
        return minElement;
    }

    // TODO, leia massiivi kõige suurem element
    public static int max(int[] arrayNimetus) {
        int suurimElement = arrayNimetus[0];
        for (int i = 0; i < arrayNimetus.length; i++) {
            if (arrayNimetus[i] > suurimElement) {
                suurimElement = arrayNimetus[i];
            }
        }
        return suurimElement;
    }

    // TODO, leia massiivi kõigi elementide summa
    public static int sum(int[] x){
        int elementideSumma = x[0];
        for (int i = 1; i < x.length; i++) {
            elementideSumma = elementideSumma + x[i];
        }
        return elementideSumma;
    }

    // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
    // TODO näiteks x = 3 y = 3
    // TODO väljund
    // 1 2 3
    // 2 4 6
    // 3 6 9
    // TODO 1 trüki korrutustabeli esimene rida (numbrid 1 - x)
    // TODO 2 lisa + " " print funktsiooni ja kasuta print mitte println
    // TODO 3 trüki seda sama rida y korda
    // TODO 4 Kuskile võiks kirjutada System.out.println(),
    //  et saada taebli kuju
    // TODO 5 võrdle ridu. Kas on mingi seaduspärasus ridade vahel,
    // mis on ja mis võiks olla. Äkki tuleb mõni idee
    public static void multiplyTable(int x, int y) {
        for (int i = 1; i <= x; i++) {
            for (int k = 1; k <= y; k++) { //kordused kuni muutuja k on väiksem võrdne kui 3
                System.out.print(i*k);
            }
            System.out.println();
        }
    }

    // TODO
    // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
    // 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
    // Tagasta fibonacci jada n element. Võid eeldada, et n >= 0
    public static int fibonacci(int n) {
        int[] fiboncciArray = new int[n+2];
        fiboncciArray[0] = 0; // n = 0, sest alates kolmndast suudab genereerida
        fiboncciArray[1] = 1; // n = 1, sest alates kolmndast suudab genereerida
        // fibonacci[2] = fibonacci[1] + fibonacci[0];
        for(int i = 2; i <= n; i++) {
            fiboncciArray[i] =  fiboncciArray[i-1] + fiboncciArray[i-2];
        }
        return fiboncciArray[n];
    }

    // TODO
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

    // 2.
    //teen tsükli x->y
    //kutsun iga väärtuse korral välja mootodi getSeqLenght
    //salvestan maha kõige suurema väärtuse ja funktsiooni lõpus tagastan selle

 public static int sequence3n(int x, int y) {
     return 0;
    }
}
