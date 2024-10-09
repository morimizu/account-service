package org.benjaminrperry.accountservice.db.repository.account;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.benjaminrperry.accountservice.db.entity.AccountJpa;
import org.benjaminrperry.accountservice.db.entity.CredentialJpa;
import org.benjaminrperry.accountservice.db.model.Account;
import org.benjaminrperry.accountservice.dictionary.CredentialType;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Account save(Account account) {
        return accountJpaRepository.save((AccountJpa) account);
    }

    @Override
    public void deleteById(Long accountId) {
        accountJpaRepository.deleteById(accountId);
    }

    @Override
    public Account findById(Long accountId) {
        return accountJpaRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account with id " + accountId + " not found"));
    }

    @Override
    public Account findByUsername(String username) {
        return accountJpaRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Account with username " + username + " not found"));
    }

    @Override
    public Account findByEmail(String email) {
        return accountJpaRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Account with email " + email + " not found"));
    }

    @Override
    public Account buildNewAccount(String username, CredentialType credentialType, String password, String firstName, String lastNam, String email) {
        var now = Instant.now();
        var newAccount = new AccountJpa();
        newAccount.setUsername(username);
        newAccount.setEmail(email);
        newAccount.setFirstName(firstName);
        newAccount.setLastName(lastNam);
        var newCredential = new CredentialJpa();
        newCredential.setType(credentialType);
        newCredential.setPassword(password);
        newCredential.setAccount(newAccount);
        newCredential.setCreationDate(now);
        newAccount.setCredentials(List.of(newCredential));
        newAccount.setJoinDate(now);
        return newAccount;
    }
}
