package org.benjaminrperry.accountservice.controller;

import lombok.RequiredArgsConstructor;
import org.benjaminrperry.accountservice.api.AccountDTO;
import org.benjaminrperry.accountservice.api.RegistrationDTO;
import org.benjaminrperry.accountservice.configuration.EnableRestCallLogging;
import org.benjaminrperry.accountservice.db.model.Account;
import org.benjaminrperry.accountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/account/registration")
@RequiredArgsConstructor
@CrossOrigin({"http://localhost:4200","chrome-extension://eipdnjedkpcnlmmdfdkgfpljanehloah"})
public class RegistrationController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(CREATED)
    @EnableRestCallLogging
    public AccountDTO register(@RequestBody RegistrationDTO requestBody) {
       return convert(this.accountService.register(requestBody));
    }

    private AccountDTO convert(Account account) {
        return AccountDTO.builder()
                .username(account.getUsername())
                .email(account.getEmail())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .joinDate(LocalDate.ofInstant(account.getJoinDate(), ZoneId.systemDefault()))
                .build();
    }
}
