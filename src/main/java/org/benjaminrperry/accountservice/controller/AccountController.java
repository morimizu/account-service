package org.benjaminrperry.accountservice.controller;

import lombok.RequiredArgsConstructor;
import org.benjaminrperry.accountservice.api.AccountDTO;
import org.benjaminrperry.accountservice.configuration.EnableRestCallLogging;
import org.benjaminrperry.accountservice.converter.AccountConverter;
import org.benjaminrperry.accountservice.service.AccountService;
import org.benjaminrperry.accountservice.service.JWTGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.benjaminrperry.accountservice.converter.AccountConverter.convert;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final JWTGenerator jwtGenerator;

    @GetMapping("/accountInfo/{username}")
    @EnableRestCallLogging
    @CrossOrigin({"http://localhost:8081","http://localhost:4200","chrome-extension://eipdnjedkpcnlmmdfdkgfpljanehloah"})
    public AccountDTO getAccountInfo(@RequestHeader("Authorization") String token, @PathVariable String username) {
        accountService.authorize(token, List.of("Get Account Info"));
        var claims = jwtGenerator.getClaims(token);
        if (!claims.getSubject().equals(username)) {
            throw new RuntimeException("user token not for this user");
        }
        return convert(accountService.getAccountByUsername(username));
    }
}
