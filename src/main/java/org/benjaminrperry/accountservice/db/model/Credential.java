package org.benjaminrperry.accountservice.db.model;

import org.benjaminrperry.accountservice.dictionary.CredentialType;

import java.time.Instant;

public interface Credential {
    Account getAccount();
    Long getId();
    CredentialType getType();
    String getPassword();
    boolean isLocked();
    Instant getLockedDate();
    Instant getCreationDate();

    void setPassword(String password);
    void lock();
    void unlock();
}
