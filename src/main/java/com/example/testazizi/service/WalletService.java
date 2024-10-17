package com.example.testazizi.service;

import com.example.testazizi.entity.User;
import com.example.testazizi.entity.Wallet;
import com.example.testazizi.repository.UserRepository;
import com.example.testazizi.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
public class WalletService {
    private WalletRepository walletRepository;
    private UserRepository userRepository;

    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    public Wallet getWalletByUserName(String userName) throws IllegalArgumentException, RuntimeException {
        if (Objects.isNull(userName) || userName.isEmpty())
            throw new IllegalArgumentException("userName can not be empty.");
        Optional<User> user = userRepository.findByEmail(userName);

        if (!user.isPresent())
            throw new RuntimeException("User not found.");

        Optional<Wallet> wallet = walletRepository.findByUser(user.get());

        if (!wallet.isPresent())
            throw new RuntimeException("Wallet not found.");

        return wallet.get();
    }

    public Wallet updateUserWallet(String userName, BigDecimal amount) {
        Wallet wallet;
        Optional<User> user = userRepository.findByEmail(userName);
        if (!user.isPresent()) {
            throw new RuntimeException("User not found");
        }
        try {
            wallet = getWalletByUserName(userName);
        } catch (Exception e) {
            wallet = new Wallet();
            wallet.setUser(user.get());
        }
        wallet.setAmount(amount);
        walletRepository.save(wallet);
        return wallet;
    }
}
