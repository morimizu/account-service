package org.benjaminrperry.accountservice.db.repository.account;

import org.benjaminrperry.accountservice.db.model.Account;
import org.benjaminrperry.accountservice.dictionary.CredentialType;

public interface AccountRepository {
    Account save(Account account);
    void deleteById(Long accountId);
    Account findById(Long accountId);
    Account findByUsername(String username);
    Account findByEmail(String email);
    Account buildNewAccount(String username, CredentialType credentialType, String password, String firstName, String lastName, String email);
}
