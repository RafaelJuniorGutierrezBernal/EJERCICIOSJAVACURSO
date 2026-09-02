package com.prestaya.model.loan;

import java.math.BigDecimal;

public class EntrepreneurLoan extends Loan {

    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.012");

    public EntrepreneurLoan(String loanId, BigDecimal amount, int termMonths) {
        super(loanId, amount, termMonths);
    }

    @Override
    public BigDecimal calculateMonthlyInstallment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateMonthlyInstallment'");
    }

}
