package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {
	private Double balance = new Double(0.0);

    public void add(Double addedAmount) {
    	this.balance += addedAmount;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) 
    		throws IllegalBalanceException {
    	Double withdrawnBalance = this.balance - withdrawnAmount;
    	if(!rule.withdrawPermitted(withdrawnBalance)) {
    		throw new IllegalBalanceException(withdrawnBalance);
    	}
    	setBalance(withdrawnBalance);
        return withdrawnBalance;
    }

}
