package com.example.testazizi.controller;

import com.example.testazizi.entity.User;
import com.example.testazizi.entity.Wallet;
import com.example.testazizi.service.WalletService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/")
    public Wallet getWallet() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return walletService.getWalletByUserName(currentUser.getEmail());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/")
    public Wallet updateWallet(@RequestParam(value = "userName") String userName, @RequestParam("amount") BigDecimal amount) {
        return walletService.updateUserWallet(userName, amount);
    }
}
