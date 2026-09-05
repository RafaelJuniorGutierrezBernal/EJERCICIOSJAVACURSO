package com.prestaya.service;

import com.prestaya.model.Customer;
import com.prestaya.model.loan.Loan;
import com.prestaya.model.status.ApplicationStatus;
import com.prestaya.model.status.ApprovedStatus;
import com.prestaya.model.status.RejectedStatus;

public class CreditEvaluationService {

    private static final int MINIMUM_CREDIT_SCORE = 600;

    public ApplicationStatus evaluateApplication(
            Customer customer,
            Loan loan) {

        if (customer == null || loan == null) {
            return new RejectedStatus("Datos de solicitud incompletos");
        }
        if (customer.creditScore() < MINIMUM_CREDIT_SCORE) {
            return new RejectedStatus("Puntaje de crédito insuficiente. Se requiere un puntaje mínimo de 600.");
        }
        return new ApprovedStatus(
                loan.getAmount(),
                loan.calculateMonthlyInstallment());
    }

}