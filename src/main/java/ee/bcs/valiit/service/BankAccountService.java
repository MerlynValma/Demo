package ee.bcs.valiit.service;

import ee.bcs.valiit.controller.Bank_Data_SQL;
import ee.bcs.valiit.controller.Transactions;
import ee.bcs.valiit.exeption.ApplicationExcpetion;
import ee.bcs.valiit.hibernate.Account;
import ee.bcs.valiit.hibernate.HibernateAccountRepository;
import ee.bcs.valiit.repository.BankAccountRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankAccountService {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private HibernateAccountRepository hibernateAccountRepository;
    @Autowired
    private BankAccountRespository bankAccountRespository;

    public List<Bank_Data_SQL> bankList() {
        return bankAccountRespository.bankList();
    }

    public String newAccount(String accountNr, double balance) {
        String sql = "INSERT INTO bank_table(account_number, balance, active) VALUES(:databaseAccountNr, :databaseBalance, true)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("databaseAccountNr", accountNr);
        paramMap.put("databaseBalance", balance);
        jdbcTemplate.update(sql, paramMap);
        return "New account is opened. Account number is " + accountNr;
    }

    public String getBalance(String accountNr) { //kontojäägi küsimine
        Account account = hibernateAccountRepository.getOne(accountNr);//defineerisime klassi, mis on seotud tabeliga
        if (bankAccountRespository.active(accountNr)) {
            return "Balance is " + account.getBalance() + " €";
        } else {
            throw new ApplicationExcpetion("Account is not active!"); //500 internal Server Error
        }
    }

    public String depositMoney(String accountNr, double balance) { //raha sisse
        Double currentBalance = bankAccountRespository.depositMoney(accountNr);
        double newBalance = balance + currentBalance;
        if (!bankAccountRespository.active(accountNr)) {
            throw new ApplicationExcpetion("Account is not active!"); //500 internal Server Error
        }
        bankAccountRespository.depositMoney(newBalance, accountNr);
        bankAccountRespository.depositMoney(balance, accountNr, "Raha sisse");
        return "New balance is " + (balance + currentBalance) + " €";
    }

    public String withdrawMoney(String accountNr, double balance) { //raha väja
        Double currentBalance = bankAccountRespository.depositMoney(accountNr);
        double newBalance = currentBalance - balance;
        if (!bankAccountRespository.active(accountNr)) {
            throw new ApplicationExcpetion("Account is not active!"); //500 internal Server Error
        } else {
            if (currentBalance >= balance) {
                bankAccountRespository.depositMoney(newBalance, accountNr);
                bankAccountRespository.depositMoney(balance, accountNr, "Raha välja");
                return "New balance is " + (currentBalance - balance) + " €";
            } else {
                return "Your are out of money!";
            }
        }
    }

    public String transferMoney(String accountNr1, double balance, String accountNr2) { //ülekanne
        if (bankAccountRespository.active(accountNr1)) {
            Double currentBalance = bankAccountRespository.depositMoney(accountNr1);
            if (currentBalance >= balance) {
                bankAccountRespository.depositMoney(currentBalance - balance, accountNr1);
                bankAccountRespository.depositMoney(currentBalance + balance, accountNr2);
                String selgitus = "Ülekanne kontole " + accountNr2;
                bankAccountRespository.depositMoney(-balance, accountNr1, selgitus);
                selgitus = "Ülekanne kontolt " + accountNr1;
                bankAccountRespository.depositMoney(balance, accountNr2, selgitus);
                return "Transfer " + balance + " € is done";
            } else {
                return "Your are out of money!";
            }
        } else {
            return "Account is not active!";
        }
    }

    public String accountClose(String accountNr) {
        bankAccountRespository.accountClose(accountNr);
        return "Account is closed!";
    }

    public String accountActive(String accountNr) {
        bankAccountRespository.accountActive(accountNr);
        return "Account is active!";
    }

    public List<Transactions> transactionList(String accountNr) { //tehingute list
        return bankAccountRespository.transactionList(accountNr);
    }
}
