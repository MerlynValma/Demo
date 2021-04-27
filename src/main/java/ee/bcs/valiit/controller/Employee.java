package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class Employee { // Loon uue klassi, defineerin uue andmetüübi Employee ja muutujad ning seejärel Alt+Insert
    private String name;
    private String aadress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadress() {
        return aadress;
    }

    public void setAadress(String aadress) {
        this.aadress = aadress;
    }
} // http://localhost:8080/Employee; v: {"name":"Mari","aadress":"Tallinn"}
