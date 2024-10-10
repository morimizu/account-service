package org.benjaminrperry.accountservice.service;

import lombok.RequiredArgsConstructor;
import org.benjaminrperry.accountservice.api.LoginRequestDTO;
import org.benjaminrperry.accountservice.api.RegistrationDTO;
import org.benjaminrperry.accountservice.db.model.Account;
import org.benjaminrperry.accountservice.db.repository.account.AccountJpaRepository;
import org.benjaminrperry.accountservice.db.repository.account.AccountRepository;
import org.benjaminrperry.accountservice.dictionary.CredentialType;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.benjaminrperry.accountservice.dictionary.CredentialType.USERNAME;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final JWTGenerator jwtGenerator;

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

    @Override
    public String authenticate(LoginRequestDTO loginRequest) {
        var account = getAccountByUsername(loginRequest.getUsername());
        return jwtGenerator.generateToken(account);
    }

    @Override
    public void authorize(String token, List<String> permissions) {
        Long id = Long.valueOf(jwtGenerator.getClaims(token).getSubject());
        Account account = accountRepository.findById(id);
        if (!permissions.stream().allMatch(account::hasPermission)) {
            throw new RuntimeException("You don't have access.");
        }
    }
}
