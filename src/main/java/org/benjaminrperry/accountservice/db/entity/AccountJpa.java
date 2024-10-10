package org.benjaminrperry.accountservice.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.benjaminrperry.accountservice.db.model.Account;
import org.benjaminrperry.accountservice.db.model.Credential;
import org.benjaminrperry.accountservice.dictionary.AccountStatus;
import org.benjaminrperry.accountservice.dictionary.CredentialType;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
public class AccountJpa implements Account, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccountStatus status = AccountStatus.ACTIVE;

    @Column(name = "email")
    private String email;

    @Column(name = "join_date")
    private Instant joinDate;

    @OneToMany(targetEntity = CredentialJpa.class, mappedBy = "account")
    private List<Credential> credentials;

    @Override
    public Credential getCredentialByType(CredentialType type) {
        return credentials.stream()
                .filter(c -> c.getType().equals(type))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean hasPermission(String permission) {
        return true;
    }
}
