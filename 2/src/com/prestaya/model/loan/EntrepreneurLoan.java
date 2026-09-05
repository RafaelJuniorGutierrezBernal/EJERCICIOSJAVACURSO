package com.prestaya.model.loan;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EntrepreneurLoan extends Loan {

    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.012");

    public EntrepreneurLoan(String loanId, BigDecimal amount, int termMonths) {
        super(loanId, amount, termMonths);
    }

    @Override
    public BigDecimal calculateMonthlyInstallment() {
        BigDecimal interest = getAmount().multiply(INTEREST_RATE);
        BigDecimal totalAmount = getAmount().add(interest);

        return totalAmount.divide(
                BigDecimal.valueOf(getTermMonths()),
                2,
                RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return """
                ----------------------------------------
                Tipo de crédito: Emprendedor
                ID del préstamo: %s
                Monto aprobado: $%s
                Plazo: %d meses
                Tasa mensual: 1.2%%
                Cuota mensual: $%s
                ----------------------------------------
                """.formatted(
                getLoanId(),
                getAmount(),
                getTermMonths(),
                calculateMonthlyInstallment());
    }

}
