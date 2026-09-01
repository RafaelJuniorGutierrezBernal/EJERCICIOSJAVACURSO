package com.prestaya.model.loan;

import java.math.BigDecimal;

public class EntrepeneuerLoan extends Loan {

    public EntrepeneuerLoan(String loanId, BigDecimal amount, int termMonths) {
        super(loanId, amount, termMonths);
    }

    @Override
    public BigDecimal calculateMonthlyInstallment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateMonthlyInstallment'");
    }

}
