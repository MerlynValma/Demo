//package ee.bcs.valiit.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class Bank_Account_Controller_copy {
//
//    public static Map<String, Double> accountBalanceMap = new HashMap<>();
//    @Autowired
//    private NamedParameterJdbcTemplate jdbcTemplate;
//
//    private class ObjectRowMapper implements RowMapper<Bank_Data_SQL> {
//        @Override // kontrollib, kas järgnevad read vastavad RowMapperile
//        public Bank_Data_SQL mapRow(ResultSet resultSet, int i) throws SQLException { // resultSet on üks kogu info ühe rea kohta; int i (ehk teist) parameetrit väga ei kasuta
//            Bank_Data_SQL bankAccounts = new Bank_Data_SQL(); // resultSet tuleb tagastada objektina
//            bankAccounts.setAccountNr(resultSet.getString("account_number")); // objekti tüüp, millena soovime vastust saada ja tulba nimi kust
//            bankAccounts.setBalance(resultSet.getDouble("balance"));
//            bankAccounts.setId(resultSet.getInt("id"));
//            bankAccounts.setActive(resultSet.getBoolean("active"));
//            return bankAccounts;
//        }
//    }
//
//    // http://localhost:8080/Bank/list
//    @GetMapping("Bank/list")
//    public List<Bank_Data_SQL> bankList() { // kuna soovin tagastada palju ridu, siis tagastustüüp on list
//        String sql = "SELECT account_number, balance, id, active FROM bank_table WHERE active = :active"; // saab ka, SELECT * FROM account_number
//        Map<String, Object> paramMap = new HashMap<>(); // String objektidest
//        paramMap.put("active", true);
//        List<Bank_Data_SQL> accountList = jdbcTemplate.query(sql, paramMap, new ObjectRowMapper()); // RowMapper selleks, et luua klass, mis oskaks ära mappida sql info. Näiteks: getLocalDate saab kasutada kuupäeva puhul (vaste: resultSer.getObject)
//        return accountList;
//    }
//
//    private class TransactionRowMatter implements RowMapper<Transactions> {
//        @Override
//        public Transactions mapRow(ResultSet resultSet, int i) throws SQLException {
//            Transactions transactions = new Transactions();
//            transactions.setId(resultSet.getInt("id"));
//            transactions.setAccountNr(resultSet.getString("account_number"));
//            transactions.setBalance(resultSet.getDouble("balance"));
//            transactions.setSelgitus(resultSet.getString("selgitus"));
//            return transactions;
//        }
//    }
//
//    // http://localhost:8080/Bank/transactions/EE12345678914
//    @GetMapping("Bank/transactions/{accountNr}")
//    public List<Transactions> transactionsList(@PathVariable("accountNr") String accountNr) {
//        String sql = "SELECT account_number, balance, selgitus, id FROM tehingud_table WHERE account_number = :databaseAccountNr";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("databaseAccountNr", accountNr);
//        List<Transactions> transactionsList = jdbcTemplate.query(sql, paramMap, new TransactionRowMatter());
//        return transactionsList;
//    }
//
//    // URL: http://localhost:8080/Bank/add/EE12345678912/100.00
//    @GetMapping("Bank/add/{accountNr}/{balance}") // Uue konto loomine ja peab olema @GetMapping
//    public String newAccount(@PathVariable("accountNr") String accountNr, @PathVariable("balance") double balance) {
//        String sql = "INSERT INTO bank_table(account_number, balance) VALUES(:databaseAccountNr, :databaseBalance)";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("databaseAccountNr", accountNr); //ParamMap´i panema kaks rida, muutuja databaseAccountNr ja muutuja :databaseBalance kohta
//        paramMap.put("databaseBalance", balance);
//        jdbcTemplate.update(sql, paramMap); // kuna INSERT sql´is ei tagasta midagi, siis kasutame päringut .update() ja kuna ei tagasta, siis kolmandat muutujat vaja ei ole
//        return "New account is opened. Account number is " + accountNr;
//    }
//
//    // URL: http://localhost:8080/Bank/getBalance/EE12345678911
//    @GetMapping("Bank/getBalance/{accountNr}") // Kontojääk
//    public String getBalance(@PathVariable("accountNr") String accountNr) {
//        String sql = "SELECT balance FROM bank_table WHERE account_number = :databaseAccountNr"; // defineerin String tüüpi muutuja, mille sql´i soovime tööle panna
//        Map<String, Object> paramMap = new HashMap<>(); // String objektidest
//        paramMap.put("databaseAccountNr", accountNr); // defineerime map´is, et sql muutuja "databaseAccountNr" omab väärtust AccountNr
//        Double balance = jdbcTemplate.queryForObject(sql, paramMap, Double.class); // queryForObject võtab sisse sql´i, paraMap´i ja tagastustüübi Double
//        return "Account balance is " + balance + " EUR";
//    }
//
//    // URL: http://localhost:8080/Bank/deposit/EE12345678911/100.0
//    @GetMapping("Bank/deposit/{accountNr}/{balance}") // Raha sisse
//    public String depositMoney(@PathVariable("accountNr") String accountNr, @PathVariable("balance") double balance) {
//        String sql = "SELECT balance FROM bank_table WHERE account_number = :databaseAccountNr";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("databaseAccountNr", accountNr);
//        Double currentBalance = jdbcTemplate.queryForObject(sql, paramMap, double.class); // küsib konto jääki
//        sql = "UPDATE bank_table SET balance = :newBalance WHERE account_number = :databaseAccountNr";
//        paramMap.put("newBalance", currentBalance + balance); // raha juurde
//        jdbcTemplate.update(sql, paramMap); // koodi execute
//
//        // andmed tabelisse tehingud_table
//        sql = "INSERT INTO tehingud_table (account_number, balance, selgitus) VALUES (:databaseAccountNr, :dbBalance, 'Raha sisse')";
//        paramMap.put("dbBalance", balance);
//        jdbcTemplate.update(sql, paramMap);
//        // andmed tabelisse tehingud_table
//
//        return "New account balance is " + (balance + currentBalance) + " EUR";
//    }
//
//    // http://localhost:8080/Bank/withdraw/EE12345678912/400.0
//    @GetMapping("Bank/withdraw/{accountNr}/{balance}") // Raha välja
//    public String withdrawMoney(@PathVariable("accountNr") String accountNr, @PathVariable("balance") double balance) {
//        String sql = "SELECT balance FROM bank_table WHERE account_number = :databaseAccountNr";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("databaseAccountNr", accountNr);
//        Double currentBalance = jdbcTemplate.queryForObject(sql, paramMap, double.class); // küsib konto jääki
//        sql = "UPDATE bank_table SET balance = :newBalance WHERE account_number = :databaseAccountNr";
//        paramMap.put("newBalance", currentBalance - balance); // raha juurde
//        jdbcTemplate.update(sql, paramMap); // koodi execute
//
//        // andmed tabelisse tehingud_table
//        sql = "INSERT INTO tehingud_table (account_number, balance, selgitus) VALUES (:databaseAccountNr, :dbBalance, 'Raha välja')";
//        paramMap.put("dbBalance", -balance); // -balance kirjeldab miinus summa tehingute_tabelis
//        jdbcTemplate.update(sql, paramMap); // võtab paramMap´i info ja saadab sql´i
//        // andmed tabelisse tehingud_table
//
//        return "New account balance is " + (currentBalance - balance) + " EUR";
//    }
//
//    // http://localhost:8080/Bank/transfer/EE12345678910/100.0/EE12345678914
//    @GetMapping("Bank/transfer/{accountNr1}/{balance}/{accountNr2}") // Ülekanne
//    public String transferMoney(@PathVariable("accountNr1") String accountNr1, @PathVariable("accountNr2") String accountNr2, @PathVariable("balance") double balance) {
//        String sql = "SELECT balance FROM bank_table WHERE account_number = :databaseAccountNr"; // küsib konto jääki
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("databaseAccountNr", accountNr1);
//        Double currentBalance = jdbcTemplate.queryForObject(sql, paramMap, double.class); // küsib konto jääki
//        if (currentBalance >= 0) {
//            sql = "UPDATE bank_table SET balance = :newBalance WHERE account_number = :databaseAccountNr";
//            paramMap.put("newBalance", currentBalance - balance); // miinus x€ kontolt
//            jdbcTemplate.update(sql, paramMap); // koodi execute
//
//            // andmed tabelisse tehingud_table
//            sql = "INSERT INTO tehingud_table (account_number, balance, selgitus) VALUES (:databaseAccountNr, :dbBalance, :dbSelgitus)"; // dbSelgitus tähendab, et kuhu kontole -> accountNr2
//            String selgitus = "Ülekanne kontole " + accountNr2; // uus muutuja
//            paramMap.put("dbBalance", -balance); // miinus x€ kontolt
//            paramMap.put("dbSelgitus", selgitus);
//            jdbcTemplate.update(sql, paramMap);
//            // andmed tabelisse tehingud_table
//
//            sql = "SELECT balance FROM bank_table WHERE account_number = :databaseAccountNr"; // küsib konto jääki
//            paramMap.put("databaseAccountNr", accountNr2);
//            currentBalance = jdbcTemplate.queryForObject(sql, paramMap, double.class); // küsib konto jääki
//            sql = "UPDATE bank_table SET balance = :newBalance WHERE account_number = :databaseAccountNr";
//            paramMap.put("newBalance", currentBalance + balance); // pluss x€ kontole
//            jdbcTemplate.update(sql, paramMap); // koodi execute
//
//            // andmed tabelisse tehingud_table
//            sql = "INSERT INTO tehingud_table (account_number, balance, selgitus) VALUES (:databaseAccountNr, :dbBalance, :dbSelgitus)"; // dbSelgitus tähendab, et kust kontolt -> accountNr1
//            selgitus = "Ülekanne kontolt " + accountNr1; // uus muutuja
//            paramMap.put("dbBalance", balance);
//            paramMap.put("dbSelgitus", selgitus);
//            jdbcTemplate.update(sql, paramMap);
//            // andmed tabelisse tehingud_table
//
//            return "Transfer amount of " + balance + " EUR is done to account " + accountNr2;
//        } else {
//            return "You do not have enough money to transfer.";
//        }
//    }
//
//    //http://localhost:8080/Bank/transfer/
//    @PutMapping("Bank/transfer/{accountNr1}/{balance}/{accountNr2}") // Ülekanne
//    public String transferMoney2(@PathVariable("accountNr1") String accountNr1, @PathVariable("accountNr2") String accountNr2, @PathVariable("balance") double balance) {
//        if (accountBalanceMap.get(accountNr1) < 0 || accountBalanceMap.get(accountNr2) < 0) {
//            return "ERROR. Your account is locked.";
//        } else {
//            accountBalanceMap.replace(accountNr1, accountBalanceMap.get(accountNr1) - balance);
//            accountBalanceMap.replace(accountNr2, accountBalanceMap.get(accountNr2) + balance);
//            return "Ülekanne summas " + (int) balance + "€ tehtud.";
//        }
//    }
//
//    // http://localhost:8080/Bank/accountClose/EE12345678910 // Konto lukku
//    @PutMapping("Bank/accountClose/{accountNr}")
//    public String accountClose(@PathVariable("accountNr") String accountNr) {
//        String sql = "UPDATE bank_table SET active = false WHERE account_number = :databaseAccountNr";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("databaseAccountNr", accountNr);
//        jdbcTemplate.update(sql, paramMap);
//        return "Account is closed!";
//    }
//
//    // http://localhost:8080/Bank/accountActive/EE12345678910 // Konto avatud
//    @PutMapping("Bank/accountActive/{accountNr}")
//    public String accountActive(@PathVariable("accountNr") String accountNr) {
//        String sql = "UPDATE bank_table SET active = true WHERE account_number = :databaseAccountNr";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("databaseAccountNr", accountNr);
//        jdbcTemplate.update(sql, paramMap);
//        return "Account is active!";
//    }
//
//
//}
//
