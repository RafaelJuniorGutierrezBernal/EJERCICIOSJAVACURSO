package com.prestaya.model.status;

public record RejectedStatus(
        String rejectionReason)
        implements ApplicationStatus {

}
