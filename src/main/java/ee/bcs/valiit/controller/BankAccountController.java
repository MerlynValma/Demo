package ee.bcs.valiit.controller;

import ee.bcs.valiit.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public static Map<String, Double> accountBalanceMap = new HashMap<>();

    @CrossOrigin
    // http://localhost:8080/Bank/list
    @GetMapping("Bank/list")
    public List<Bank_Data_SQL> bankList() { // kuna soovin tagastada palju ridu, siis tagastustüüp on list
        return bankAccountService.bankList();
    }

    // http://localhost:8080/Bank/transactions/EE12345678914
    @GetMapping("Bank/transactions/{accountNr}")
    public List<Transactions> transactionsList(@PathVariable("accountNr") String accountNr) {
        return bankAccountService.transactionList(accountNr);
    }

    @CrossOrigin // võib teiselt host´ilt/URL´ilt kasutada
    // URL: http://localhost:8080/Bank/add/EE12345678912/100.00
    @PostMapping("Bank/add/{accountNr}/{balance}") // Uue konto loomine
    public String newAccount(@PathVariable("accountNr") String accountNr,
                             @PathVariable("balance") double balance) {
        return bankAccountService.newAccount(accountNr, balance);
    }

    @CrossOrigin
    // URL: http://localhost:8080/Bank/getBalance/EE12345678911
    @GetMapping("Bank/getBalance/{accountNr}") // Kontojääk
    public String getBalance(@PathVariable("accountNr") String accountNr) {
        return bankAccountService.getBalance(accountNr);
    }

    @CrossOrigin
    // URL: http://localhost:8080/Bank/deposit/EE12345678911/100.0
    @GetMapping("Bank/deposit/{accountNr}/{balance}") // Raha sisse
    public String depositMoney(@PathVariable("accountNr") String accountNr,
                               @PathVariable("balance") double balance) {
        return bankAccountService.depositMoney(accountNr, balance);
    }

    @CrossOrigin
    // http://localhost:8080/Bank/withdraw/EE12345678912/400.0
    @GetMapping("Bank/withdraw/{accountNr}/{balance}") // Raha välja
    public String withdrawMoney(@PathVariable("accountNr") String accountNr,
                                @PathVariable("balance") double balance) {
        return bankAccountService.withdrawMoney(accountNr, balance);
    }

    @CrossOrigin
    // http://localhost:8080/Bank/transfer/EE12345678910/100.0/EE12345678914
    @GetMapping("Bank/transfer/{accountNr1}/{balance}/{accountNr2}") // Ülekanne
    public String transferMoney(@PathVariable("accountNr1") String accountNr1, @PathVariable("accountNr2") String accountNr2,
                                @PathVariable("balance") double balance) {
        return bankAccountService.transferMoney(accountNr1, balance, accountNr2);

    }

    @CrossOrigin
    // http://localhost:8080/Bank/accountClose/EE12345678910 // Konto lukku
    @PutMapping("Bank/accountClose/{accountNr}")
    public String accountClose(@PathVariable("accountNr") String accountNr) {
        return bankAccountService.accountClose(accountNr);
    }

    @CrossOrigin
    // http://localhost:8080/Bank/accountActive/EE12345678910 // Konto avatud
    @PutMapping("Bank/accountActive/{accountNr}")
    public String accountActive(@PathVariable("accountNr") String accountNr) {
        return bankAccountService.accountActive(accountNr);
    }
}

