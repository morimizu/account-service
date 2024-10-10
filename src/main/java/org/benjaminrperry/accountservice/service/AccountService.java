package org.benjaminrperry.accountservice.service;

import org.benjaminrperry.accountservice.api.LoginRequestDTO;
import org.benjaminrperry.accountservice.api.RegistrationDTO;
import org.benjaminrperry.accountservice.db.model.Account;

import java.util.List;

public interface AccountService {
    Account register(RegistrationDTO registrationDTO);

    Account getAccountByUsername(String username);

    Account getAccountByEmail(String email);

    String authenticate(LoginRequestDTO loginRequest);

    void authorize(String token, List<String> permissions);
}
