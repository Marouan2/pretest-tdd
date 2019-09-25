package com.priceminister.account;


import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.priceminister.account.implementation.*;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {
    
    Account customerAccount;
    AccountRule rule;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
    }
    
    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
        Double balance = customerAccount.getBalance();
        assertEquals(balance, new Double(0.0));
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
        Double expectedAmount = new Double(100.0);
        customerAccount.add(expectedAmount);
        assertEquals(customerAccount.getBalance(), expectedAmount);
    }
    
    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     * @throws IllegalBalanceException 
     */
    @Test
    public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {
    	customerAccount.add(new Double(500.0));
        thrown.expect(IllegalBalanceException.class);
        customerAccount.withdrawAndReportBalance(new Double(700.0), new CustomerAccountRule());
        
    }
    
    // Also implement missing unit tests for the above functionalities.
    /**
     * Adds multiple amounts to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddMultiplePositiveAmounts() {
        customerAccount.add(new Double(100.0));
        customerAccount.add(new Double(200.0));
        assertEquals(customerAccount.getBalance(), new Double(300.0));
    }
    
    /**
     * Tests that a positive withdrawal do not throws exceptions
     * @throws IllegalBalanceException 
     */
    @Test
    public void testWithdrawAndReportBalancePositiveBalance() throws IllegalBalanceException {
    	customerAccount.add(new Double(400.0));
        Double withdraw = customerAccount.withdrawAndReportBalance(new Double(200.0), new CustomerAccountRule());
        assertEquals(new Double(200.0), withdraw);
        assertEquals(customerAccount.getBalance(), withdraw);
    }
    
    /**
     * Tests multiple positive withdrawals do not throws exceptions
     * @throws IllegalBalanceException 
     */
    @Test
    public void testMultipleWithdrawsAndReportBalancePositiveBalance() throws IllegalBalanceException {
    	customerAccount.add(new Double(400.0));
    	customerAccount.withdrawAndReportBalance(new Double(100.0), new CustomerAccountRule());
        Double withdraw = customerAccount.withdrawAndReportBalance(new Double(200.0), new CustomerAccountRule());
        assertEquals(new Double(100.0), withdraw);
        assertEquals(customerAccount.getBalance(), withdraw);
    }
}
