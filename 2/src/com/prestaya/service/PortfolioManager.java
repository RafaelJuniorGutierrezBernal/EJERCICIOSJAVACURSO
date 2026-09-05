package com.prestaya.service;

import java.math.BigDecimal;

import com.prestaya.model.loan.Loan;

public class PortfolioManager {

    private static final int MAX_PORTFOLIO_SIZE = 10;

    private final Loan[] loans;
    private int loanCount;

    public PortfolioManager() {
        loans = new Loan[MAX_PORTFOLIO_SIZE];
        loanCount = 0;
    }

    public boolean registerLoan(Loan loan) {
        if (loan == null) {
            return false;
        }

        if (loanCount >= MAX_PORTFOLIO_SIZE) {
            return false;
        }

        loans[loanCount] = loan;
        loanCount++;

        return true;
    }

    public void showPortfolio() {
        if (loanCount == 0) {
            System.out.println("La cartera de PrestaYa no tiene creditos aprobados registrados.");
            return;
        }
        System.out.println("\n--- CARTERA DE CRÉDITOS APROBADOS ---");
        for (int i = 0; i < loanCount; i++) {
            System.out.println(loans[i]);
        }
    }

    public BigDecimal calculateTotalPlacedAmount() {
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < loanCount; i++) {
            total = total.add(loans[i].getAmount());
        }
        return total;
    }

}