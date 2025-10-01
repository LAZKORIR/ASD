package com.bank.cams.repository;

import com.bank.cams.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByOrderByBalanceDesc();
    List<Account> findByBalanceGreaterThan(Double amount);
}
