package com.prestaya.model.status;

import java.math.BigDecimal;

public record ApprovedStatus(
        BigDecimal approvedAmount,
        BigDecimal calculatedInstallment) implements ApplicationStatus {
}
