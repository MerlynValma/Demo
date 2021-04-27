package ee.bcs.valiit.controller;

public class Transactions {
    int id;
    String accountNr;
    Double balance;
    String selgitus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(String accountNr) {
        this.accountNr = accountNr;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getSelgitus() {
        return selgitus;
    }

    public void setSelgitus(String selgitus) {
        this.selgitus = selgitus;
    }
}
