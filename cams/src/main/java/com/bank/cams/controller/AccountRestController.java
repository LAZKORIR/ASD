package com.bank.cams.controller;

import com.bank.cams.entity.Account;
import com.bank.cams.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {

    private final AccountService service;

    public AccountRestController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return service.getAllAccountsSorted();
    }

    @GetMapping("/prime")
    public List<Account> getPrimeAccounts() {
        return service.getPrimeAccounts();
    }
}
