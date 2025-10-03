package com.bank.cams.controller;

import com.bank.cams.dto.AccountDto;
import com.bank.cams.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {

    private final AccountService service;
    public AccountRestController(AccountService service) { this.service = service; }

    @GetMapping
    public List<AccountDto> getAllAccounts() {
        return service.getAllAccountsSorted().stream().map(AccountDto::of).toList();
    }

    @GetMapping("/prime")
    public List<AccountDto> getPrimeAccounts() {
        return service.getPrimeAccounts().stream().map(AccountDto::of).toList();
    }
}
