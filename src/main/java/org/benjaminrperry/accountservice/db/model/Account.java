package org.benjaminrperry.accountservice.db.model;

import org.benjaminrperry.accountservice.dictionary.AccountStatus;
import org.benjaminrperry.accountservice.dictionary.CredentialType;

import java.time.Instant;
import java.util.List;

public interface Account {
    Long getId();
    String getUsername();
    String getFirstName();
    String getLastName();
    AccountStatus getStatus();
    String getEmail();
    Instant getJoinDate();
    List<Credential> getCredentials();

    Credential getCredentialByType(CredentialType type);

    boolean hasPermission(String permission);
}
