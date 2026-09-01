package com.prestaya.model.loan;

import java.math.BigDecimal;

public abstract class Loan {

    private final String loanId;
    private final BigDecimal amount;
    private final int termMonths;

    protected Loan(String loanId, BigDecimal amount, int termMonths) {
        this.loanId = loanId;
        this.amount = amount;
        this.termMonths = termMonths;
    }

    public String getLoanId() {
        return loanId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public abstract BigDecimal calculateMonthlyInstallment();
}
