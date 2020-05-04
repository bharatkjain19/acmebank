package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.exception.AccountException;
import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.TransferFundsRequest;
import com.acmebank.accountmanager.services.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountManagementController {

    @Autowired
    private AccountManagerService accountManager;

    //GET
    @RequestMapping(value = "/accounts/all", method = RequestMethod.GET)  //localhost:8080/All_accounts
    public List<Account> getBalance() {
        return accountManager.getAllTheAccounts();
    }

    @RequestMapping(value = "/accounts/{accountNumber}", method = RequestMethod.GET)
    public Account getBalanceByAccountNumber(@PathVariable int accountNumber) throws AccountException {
        return accountManager.getAccount(accountNumber);
    }

    //PUT
    @RequestMapping(value = "/accounts/transferFunds", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Transfer Successful")
    public List<Account> transferFunds(@RequestBody TransferFundsRequest transferFundsRequest) throws AccountException {
        return accountManager.transferFunds(transferFundsRequest);

    }

}