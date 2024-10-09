package org.benjaminrperry.accountservice.db.repository.account;

import org.benjaminrperry.accountservice.db.entity.AccountJpa;
import org.benjaminrperry.accountservice.db.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountJpa, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
}
