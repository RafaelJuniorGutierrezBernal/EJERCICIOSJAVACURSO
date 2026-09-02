package com.prestaya.model.loan;

import java.math.BigDecimal;

public abstract class Loan {

    private final String loanId;
    private final BigDecimal amount;
    private final int termMonths;

    protected Loan(String loanId, BigDecimal amount, int termMonths) {

        if (loanId == null || loanId.isBlank()) {
            throw new IllegalArgumentException("El ID del préstamo es obligatorio.");

        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor que cero.");
        }
        if (termMonths <= 0) {
            throw new IllegalArgumentException("El plazo del préstamo debe ser mayor que cero meses.");
        }

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
