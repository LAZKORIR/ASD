package com.bank.cams.dto;

import com.bank.cams.entity.Account;

import java.time.LocalDate;

public record AccountDto(
        Long accountId,
        String accountNumber,
        String accountType,
        LocalDate dateOpened,
        Double balance,
        CustomerLite owner
) {
    public static AccountDto of(Account a) {
        var c = a.getCustomer();
        return new AccountDto(
            a.getAccountId(),
            a.getAccountNumber(),
            a.getAccountType(),
            a.getDateOpened(),
            a.getBalance(),
            new CustomerLite(c.getCustomerId(), c.getFirstName(), c.getLastName())
        );
    }
}
