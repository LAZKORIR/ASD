package com.bank.cams.controller;

import com.bank.cams.entity.Customer;
import com.bank.cams.repository.CustomerRepository;
import com.bank.cams.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final AccountService accountService;
    private final CustomerRepository customerRepo;

    public HomeController(AccountService accountService, CustomerRepository customerRepo) {
        this.accountService = accountService;
        this.customerRepo = customerRepo;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/accounts")
    public String listAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccountsSorted());
        model.addAttribute("liquidity", accountService.getLiquidityPosition());
        return "accounts";
    }

    @GetMapping("/accounts/new")
    public String newAccountForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "new-account";
    }

    @PostMapping("/accounts")
    public String saveAccount(@ModelAttribute Customer customer) {
        if (customer.getAccount() != null) {
            customer.getAccount().setCustomer(customer);
        }
        customerRepo.save(customer);
        return "redirect:/accounts";
    }
}
