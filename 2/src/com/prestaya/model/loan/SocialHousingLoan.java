package com.prestaya.model.loan;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SocialHousingLoan extends Loan {

    private final BigDecimal governmentSubsidieAmount;

    public SocialHousingLoan(
            String loanId,
            BigDecimal amount,
            int termMonths,
            BigDecimal governmentSubsidieAmount) {
        super(loanId, amount, termMonths);

        if (governmentSubsidieAmount == null
                || governmentSubsidieAmount.compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "El subsidio gubernamental no puede ser nulo ni negativo.");
        }

        if (governmentSubsidieAmount.compareTo(getAmount()) >= 0) {
            throw new IllegalArgumentException(
                    "El subsidio gubernamental debe ser menor que el monto del préstamo.");
        }

        this.governmentSubsidieAmount = governmentSubsidieAmount;
    }

    public BigDecimal getGovernmentSubsidieAmount() {
        return governmentSubsidieAmount;
    }

    @Override
    public BigDecimal calculateMonthlyInstallment() {
        BigDecimal financedAmount = getAmount()
                .subtract(governmentSubsidieAmount);

        return financedAmount.divide(
                BigDecimal.valueOf(getTermMonths()),
                2,
                RoundingMode.HALF_UP);
    }

}
