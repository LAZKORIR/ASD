package com.bank.cams.service;

import com.bank.cams.entity.Account;
import com.bank.cams.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public List<Account> getAllAccountsSorted() {
        return repo.findAllByOrderByBalanceDesc();
    }

    public double getLiquidityPosition() {
        return repo.findAll().stream().mapToDouble(Account::getBalance).sum();
    }

    public List<Account> getPrimeAccounts() {
        return repo.findByBalanceGreaterThan(10000.0);
    }

    public Account save(Account acc) {
        return repo.save(acc);
    }
}
