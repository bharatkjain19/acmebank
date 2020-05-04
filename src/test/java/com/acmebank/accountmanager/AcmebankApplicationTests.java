package com.acmebank.accountmanager;

import com.acmebank.accountmanager.model.Account;
import com.acmebank.accountmanager.model.TransferFundsRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AcmebankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcmebankApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllAccounts() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/accounts/all", HttpMethod.GET, entity,
                String.class);
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testGetAccountByAccountNumber() {
        Account account = restTemplate.getForObject(getRootUrl() + "/accounts/88888888", Account.class);
        Assert.assertNotNull(account);
    }

    @Test
    public void testTransferAmount() {
        TransferFundsRequest transferFundsRequest = new TransferFundsRequest();
        transferFundsRequest.setFromAccountNumber(88888888);
        transferFundsRequest.setToAccountNumber(12345678);
        transferFundsRequest.setAmount(new Double(20));
        HttpEntity<TransferFundsRequest> entity = new HttpEntity<TransferFundsRequest>(transferFundsRequest);
        ResponseEntity<String> result = restTemplate.exchange(getRootUrl() + "/accounts/transferFunds", HttpMethod.PUT, entity, String.class);
        Assert.assertNotNull(result);
    }
}

