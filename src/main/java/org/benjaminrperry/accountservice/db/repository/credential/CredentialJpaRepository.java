package org.benjaminrperry.accountservice.db.repository.credential;

import org.benjaminrperry.accountservice.db.entity.CredentialJpa;
import org.benjaminrperry.accountservice.dictionary.CredentialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialJpaRepository extends JpaRepository<CredentialJpa, Long> {
    Optional<CredentialJpa> findByAccountIdAndType(Long accountId, CredentialType credentialType);
}
