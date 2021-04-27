package ee.bcs.valiit.tasks;

import java.util.HashMap;
import java.util.Scanner;

public class Lesson4 {
    public static HashMap<String, Double> accountBalanceMap = new HashMap<>(); // loon uue tühja HashMap´i
    public static Scanner scanner = new Scanner(System.in); // standardsisendist lugemiseks System.in
    // Store account nr as a key and account balance as value
    // HashMap<String, Account> accountBalanceMap = new HashMap<>();

    public static void main(String[] args) {
        accountBalanceMap.put("EE123", 100.0); // tekitab map´i key ja value
        accountBalanceMap.put("EE333", 0.0);

        while (true) { // loop´i kuni break´ini // vajalik, et command line töötaks
            System.out.println("insert command");
            String line = scanner.nextLine(); // String line saab väärtuse, mille sisestan paneeli
            if (line.equalsIgnoreCase("exit")) { // equals vaatab, kas võrdne. IgnoreCase ignoreerib suuri/väikeseid algustähti
                break;
            } else if (line.equalsIgnoreCase("createAccount")) { //TODO 1
                System.out.println("Please enter account nr");
                String accountNr = scanner.nextLine();
                System.out.println("Please insert initial balance");
                Double balance = scanner.nextDouble();
                scanner.nextLine();
                accountBalanceMap.put(accountNr, balance); //salvestab sisendi map´i
            } else if (line.equalsIgnoreCase("getBalance")) { //TODO 2
                System.out.println("Please enter account nr");
                String accountNr = scanner.nextLine(); // sisendi küsimiseks konsoolist
                System.out.println(accountBalanceMap.get(accountNr)); // väljastan map´ist väärtuse vastavalt key´le
            } else if (line.equalsIgnoreCase("depositMoney")) { //TODO 3
                System.out.println("Please enter account nr");
                String accountNr = scanner.nextLine();
                while (true) { // hoiab loop´is kuni summa on positiivne
                    System.out.println("Please specify amount of money to account");
                    Double amount = scanner.nextDouble();
                    scanner.nextLine();
                    if (amount > 0) { // sisestatud summa peab olema positiivne
                        accountBalanceMap.replace(accountNr, amount + accountBalanceMap.get(accountNr)); // liidab kontol olevale summale uue summa juurde
                        break;
                    } else {
                        System.out.println("The amount of money need to be more than 0");
                    }
                }
            } else if (line.equalsIgnoreCase("withdrawMoney")) { //TODO 4
                System.out.println("Please enter account nr");
                String accountNr = scanner.nextLine();
                while (true) { // hoiab loop´is kuni summa on positiivne
                    System.out.println("Please specify amount of money to withdraw");
                    Double amount = scanner.nextDouble();
                    scanner.nextLine();
                    if (amount > 0) { // sisestatud summa peab olema positiivne
                        if (amount > accountBalanceMap.get(accountNr)) {
                            System.out.println("Error! You do not have enough money!");
                        } else {
                            accountBalanceMap.replace(accountNr, accountBalanceMap.get(accountNr) - amount);
                            break;
                        }
                    } else {
                        System.out.println("The amount of money need to be more than 0");
                    }
                }
            } else if (line.equalsIgnoreCase("transfer")) { // TODO 5
                while (true) { // while, kui error, siis küsib uuesti account numbrit
                    System.out.println("Please enter your account nr");
                    String accountNr = scanner.nextLine();
                    if (accountBalanceMap.get(accountNr) == null) { // kui pangakontot ei ole, siis error
                        System.out.println("ERROR! Do not have account! Please contact the Bank!");
                    } else {
                        System.out.println("Please specify amount of money to transfer");
                        Double amount = scanner.nextDouble();
                        scanner.nextLine();
                        if (amount > 0) { // transfeeritav summa peab olema suurem kui 0
                            if (amount > accountBalanceMap.get(accountNr)) { // kui pole piisavalt raha, et ülekanne teha
                                System.out.println("Error! You do not have enough money!");
                            } else {
                                System.out.println("Please enter account number where to tranfer the money");
                                String toAccount = scanner.nextLine();
                                if (accountBalanceMap.get(toAccount) == null) { // kui pangakontot ei ole, siis error
                                    System.out.println("ERROR! Do not have account! Please contact the Bank!");
                                } else {
                                    accountBalanceMap.replace(accountNr, accountBalanceMap.get(accountNr) - amount); // vähendab ühel kontol
                                    accountBalanceMap.replace(toAccount, amount + accountBalanceMap.get(toAccount)); // suurendab teisel kontol
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Amount of money cant be negative"); // // transfeeritav summa peab olema suurem kui 0
                        }
                    }
                }
            }

            // TODO 1
            // Add command: "createAccount ${accountNr}"
            // this has to store accountNr with 0 balance
            // TODO 2
            // Add command: "getBalance ${accountNr}"
            // this has to display account balance of specific acount
            // TODO 3
            // Add command: "depositMoney ${accountNr} ${amount}
            // this has to add specified amount of money to account
            // You have to check that amount is positive number
            // TODO 4
            // Add command: "withdrawMoney ${accountNr} ${amount}
            // This has to remove specified amount of money from account
            // You have to check that amount is positive number
            // You may not allow this transaction if account balance would become negative
            // TODO 5
            // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
            // This has to remove specified amount from fromAccount and add it to toAccount
            // Your application needs to check that toAccount is positive
            // And from account has enough money to do that transaction
            else {
                System.out.println("Unknown command");
            }
        }
    }
}
