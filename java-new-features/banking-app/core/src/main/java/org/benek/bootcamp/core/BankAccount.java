package org.benek.bootcamp.core;
import org.apache.commons.lang3.RandomStringUtils;
import org.benek.bootcamp.logger.Logger;

import java.util.ServiceLoader;

public class BankAccount {
    private static final Logger logger = ServiceLoader
            .load(Logger.class)
            .findFirst()
            .orElseThrow();

    private final String accountNumber = RandomStringUtils.randomNumeric(10);

    public void deposit(double amount) {
        logger.log("Depositing: [" + amount + "] to account number: [" + accountNumber + "]");
    }
}
