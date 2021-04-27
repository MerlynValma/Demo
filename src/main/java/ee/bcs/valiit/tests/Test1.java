package ee.bcs.valiit.tests;

import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) {
        System.out.println(newfunction(3));
        System.out.println(newfunction(21));

        int array[] = {1, 2, 3};
        System.out.println(Arrays.toString(addToArray(array, 5)));
    }

    public static boolean newfunction(int x) { // kõigepealt defineerin funktsiooni
        if ((x % 3 == 0 && x % 7 != 0) || (x % 7 == 0 && x % 3 != 0)) {
            return true;
        } else {
            return false;
        }
    }


//    ÜL 1
//    Tee funktsioon, mis tagastab boolean väärtuse ja võtab sisse ühe parameetri
//    funktsioon peab tagastama
//		true: kui sisend parameeter jagub 3 või 7 (aga ei jagu mõlema numbriga)
//            false: kui sisend parameeter ei jagu 3 ega 7 või jagub mõlema numbriga


    // ÜL2
    // lisa x igale array elemendile
    // Näiteks
    // sisend [1,2,3], 5
    // vastus [6,7,8]

    public static int[] addToArray(int[] array, int x) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] + x);
        }
        return array;
    }
}
