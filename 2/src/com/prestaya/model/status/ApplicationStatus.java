package com.prestaya.model.status;

public sealed interface ApplicationStatus
        permits
        ApprovedStatus,
        RejectedStatus {

}
