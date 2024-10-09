package org.benjaminrperry.accountservice.db.repository.credential;

import org.benjaminrperry.accountservice.db.model.Account;
import org.benjaminrperry.accountservice.db.model.Credential;
import org.benjaminrperry.accountservice.dictionary.CredentialType;

public interface CredentialRepository {
    Credential save(Credential credential);
    void deleteById(Long credentialId);
    Credential buildNewCredential(Account account, CredentialType type, String password);
}
