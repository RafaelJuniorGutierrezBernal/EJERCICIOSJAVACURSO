package com.prestaya.model.loan;

import java.math.BigDecimal;

public class SocialHousingLoan extends Loan {

    private final BigDecimal governmentSubsidieAmount;

    public SocialHousingLoan(
            String loanId,
            BigDecimal amount,
            int termMonths,
            BigDecimal governmentSubsidieAmount) {
        super(loanId, amount, termMonths);

        if (governmentSubsidieAmount == null || governmentSubsidieAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del subsidio gubernamental debe ser mayor que cero.");
        }
        if (governmentSubsidieAmount.compareTo(getAmount()) > 0) {
            throw new IllegalArgumentException("El monto del subsidio gubernamental no puede ser mayor que el monto del préstamo.");
        }
        if (governmentSubsidieAmount.compareTo(getAmount()) {
            
        }

        this.governmentSubsidieAmount = governmentSubsidieAmount;
    }

    public BigDecimal getGovernmentSubsidieAmount() {
        return governmentSubsidieAmount;
    }

    @Override
    public BigDecimal calculateMonthlyInstallment() {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateMonthlyInstallment'");
    }

}
