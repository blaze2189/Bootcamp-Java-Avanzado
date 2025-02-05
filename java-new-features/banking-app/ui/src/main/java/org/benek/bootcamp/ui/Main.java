package org.benek.bootcamp.ui;

import org.benek.bootcamp.core.BankAccount;
//import org.benek.bootcamp.core.security.SecurityCheck;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(10_000);

        //SecurityCheck securityCheck = new SecurityCheck();
    }
}
