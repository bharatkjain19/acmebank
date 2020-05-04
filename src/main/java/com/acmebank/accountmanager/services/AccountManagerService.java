package com.acmebank.accountmanager.services;

import com.acmebank.accountmanager.exception.AccountException;
import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.TransferFundsRequest;
import com.acmebank.accountmanager.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AccountManagerService {

    @Autowired
    private AccountRepository accountRepository;

    //Return All the Accounts
    public List<Account> getAllTheAccounts() {
        return accountRepository.findAll();
    }

    //Return account based on accountNumber
    public Account getAccount(Integer accountNumber) throws AccountException {
        Optional<Account> optionalAccount = Optional.ofNullable(accountRepository.findById(accountNumber).orElseThrow(() -> new AccountException("Account", "id", accountNumber)));
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        }
        return null;
    }

    //Return list of two accounts after transfer of amount from one account to another
    public List<Account> transferFunds(TransferFundsRequest transferFundsRequest) throws AccountException {
        List<Account> transferredAccounts = new ArrayList<>();

        Optional<Account> fromAccount = Optional.ofNullable(accountRepository.findById(transferFundsRequest.getFromAccountNumber()).orElseThrow(() -> new AccountException("Account not found: ", "fromAccount", transferFundsRequest.getFromAccountNumber())));
        Optional<Account> toAccount = Optional.ofNullable(accountRepository.findById(transferFundsRequest.getToAccountNumber()).orElseThrow(() -> new AccountException("Account not found: ", "toAccount", transferFundsRequest.getToAccountNumber())));

        if (transferFundsRequest.getAmount() == null) {
            throw new AccountException("Amount not entered: ", "amount", "null or empty");
        } else if (transferFundsRequest.getAmount() <= fromAccount.get().getAccountBalance()) {
            fromAccount.get().setAccountBalance(fromAccount.get().getAccountBalance() - transferFundsRequest.getAmount());
            accountRepository.save(fromAccount.get());
            transferredAccounts.add(fromAccount.get());
            toAccount.get().setAccountBalance(toAccount.get().getAccountBalance() + transferFundsRequest.getAmount());
            accountRepository.save(toAccount.get());
            transferredAccounts.add(toAccount.get());
        } else {
            throw new AccountException("Invalid Input for the field: ", "amount", transferFundsRequest.getAmount());
        }
        return transferredAccounts;
    }
}