package org.benjaminrperry.accountservice.service;

import lombok.RequiredArgsConstructor;
import org.benjaminrperry.accountservice.api.RegistrationDTO;
import org.benjaminrperry.accountservice.db.model.Account;
import org.benjaminrperry.accountservice.db.repository.account.AccountRepository;
import org.benjaminrperry.accountservice.dictionary.CredentialType;
import org.springframework.stereotype.Service;

import static org.benjaminrperry.accountservice.dictionary.CredentialType.USERNAME;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account register(RegistrationDTO registrationDTO) {
        var newAccount = accountRepository.buildNewAccount(registrationDTO.getUsername(), USERNAME, registrationDTO.getPassword(), registrationDTO.getFirstName(), registrationDTO.getLastName(), registrationDTO.getEmail());
        return accountRepository.save(newAccount);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
