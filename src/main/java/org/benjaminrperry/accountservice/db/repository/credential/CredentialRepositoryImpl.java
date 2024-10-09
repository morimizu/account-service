package org.benjaminrperry.accountservice.db.repository.credential;

import lombok.RequiredArgsConstructor;
import org.benjaminrperry.accountservice.db.entity.CredentialJpa;
import org.benjaminrperry.accountservice.db.model.Account;
import org.benjaminrperry.accountservice.db.model.Credential;
import org.benjaminrperry.accountservice.dictionary.CredentialType;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
@RequiredArgsConstructor
public class CredentialRepositoryImpl implements CredentialRepository {

    private final CredentialJpaRepository credentialJpaRepository;

    @Override
    public Credential save(Credential credential) {
        return credentialJpaRepository.save((CredentialJpa) credential);
    }

    @Override
    public void deleteById(Long credentialId) {
        credentialJpaRepository.deleteById(credentialId);
    }

    @Override
    public Credential buildNewCredential(Account account, CredentialType type, String password) {
        var newCredential = new CredentialJpa();
        newCredential.setType(type);
        newCredential.setPassword(password);
        newCredential.setAccount(account);
        newCredential.setCreationDate(Instant.now());
        return newCredential;
    }
}
