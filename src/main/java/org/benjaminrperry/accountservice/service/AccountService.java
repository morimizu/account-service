package org.benjaminrperry.accountservice.service;

import org.benjaminrperry.accountservice.api.RegistrationDTO;
import org.benjaminrperry.accountservice.db.model.Account;

public interface AccountService {
    Account register(RegistrationDTO registrationDTO);

    Account getAccountByUsername(String username);

    Account getAccountByEmail(String email);
}
