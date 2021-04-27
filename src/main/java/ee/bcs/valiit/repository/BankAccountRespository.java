package ee.bcs.valiit.repository;

import ee.bcs.valiit.controller.BankAccountController;
import ee.bcs.valiit.controller.Bank_Data_SQL;
import ee.bcs.valiit.controller.Transactions;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankAccountRespository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Bank_Data_SQL> bankList() {
        String sql = "SELECT account_number, balance, id, active FROM bank_table";
        return jdbcTemplate.query(sql, new ObjectRowMapper());
    }

    private class ObjectRowMapper implements RowMapper<Bank_Data_SQL> {
        @Override
        public Bank_Data_SQL mapRow(ResultSet resultSet, int i) throws SQLException {
            Bank_Data_SQL bankAccounts = new Bank_Data_SQL();
            bankAccounts.setAccountNr(resultSet.getString("account_number"));
            bankAccounts.setBalance(resultSet.getDouble("balance"));
            bankAccounts.setId(resultSet.getInt("id"));
            bankAccounts.setActive(resultSet.getBoolean("active"));
            return bankAccounts;
        }
    }

    public double depositMoney(String accountNr) { //kontojäägi küsimine
        String sql = "SELECT balance FROM bank_table WHERE account_number = :databaseAccountNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("databaseAccountNr", accountNr);
        return jdbcTemplate.queryForObject(sql, paramMap, double.class);
    }

    public boolean active(String accountNr) { //konto aktiivne või suletud
        String sql = "SELECT active FROM bank_table WHERE account_number = :databaseAccountNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("databaseAccountNr", accountNr);
        return jdbcTemplate.queryForObject(sql, paramMap, boolean.class);
    }

    public void depositMoney(double balance, String accountNr) { //kontojäägi uuendamine
        String sql = "UPDATE bank_table SET balance = :newBalance WHERE account_number = :databaseAccountNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("databaseAccountNr", accountNr);
        paramMap.put("newBalance", balance);
        jdbcTemplate.update(sql, paramMap);
    }

    public void depositMoney(double balance, String accountNr, String selgitus) { //rea lisamine tabelisse tehingud_table
        String sql = "INSERT INTO tehingud_table (account_number, balance, selgitus) VALUES (:databaseAccountNr, :dbBalance, :selgitus)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbBalance", balance);
        paramMap.put("databaseAccountNr", accountNr);
        paramMap.put("selgitus", selgitus);
        jdbcTemplate.update(sql, paramMap);
    }

    public void accountClose(String accountNr) {
        String sql = "UPDATE bank_table SET active = false WHERE account_number = :databaseAccountNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("databaseAccountNr", accountNr);
        jdbcTemplate.update(sql, paramMap);
    }

    public void accountActive(String accountNr) {
        String sql = "UPDATE bank_table SET active = true WHERE account_number = :databaseAccountNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("databaseAccountNr", accountNr);
        jdbcTemplate.update(sql, paramMap);
    }

    public List<Transactions> transactionList(String accountNr) {
        String sql = "SELECT account_number, balance, selgitus, id FROM tehingud_table WHERE account_number = :databaseAccountNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("databaseAccountNr", accountNr);
        return jdbcTemplate.query(sql, paramMap, new TransactionRowMatter());
    }

    private class TransactionRowMatter implements RowMapper<Transactions> {
        @Override
        public Transactions mapRow(ResultSet resultSet, int i) throws SQLException {
            Transactions transactions = new Transactions();
            transactions.setId(resultSet.getInt("id"));
            transactions.setAccountNr(resultSet.getString("account_number"));
            transactions.setBalance(resultSet.getDouble("balance"));
            transactions.setSelgitus(resultSet.getString("selgitus"));
            return transactions;
        }
    }
}