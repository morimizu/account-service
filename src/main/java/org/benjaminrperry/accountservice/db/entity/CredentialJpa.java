package org.benjaminrperry.accountservice.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.benjaminrperry.accountservice.db.model.Account;
import org.benjaminrperry.accountservice.db.model.Credential;
import org.benjaminrperry.accountservice.dictionary.CredentialType;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "credentials")
@Getter
@Setter
public class CredentialJpa implements Credential, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = AccountJpa.class)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CredentialType type;

    @Column(name = "password")
    private String password;

    @Column(name = "locked")
    private boolean locked;

    @Column(name = "locked_date", nullable = false)
    private Instant lockedDate;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Override
    public void lock() {
        this.locked = true;
        this.lockedDate = Instant.now();
    }

    @Override
    public void unlock() {
        this.locked = false;
        this.lockedDate = null;
    }
}
