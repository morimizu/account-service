package org.benjaminrperry.accountservice.controller;

import lombok.RequiredArgsConstructor;
import org.benjaminrperry.accountservice.api.LoginRequestDTO;
import org.benjaminrperry.accountservice.api.TokenDTO;
import org.benjaminrperry.accountservice.configuration.EnableRestCallLogging;
import org.benjaminrperry.accountservice.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@CrossOrigin({"http://localhost:4200","chrome-extension://eipdnjedkpcnlmmdfdkgfpljanehloah","http://localhost:8081"})
public class AuthenticationController {

    private final AccountService accountService;

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    @EnableRestCallLogging
    public TokenDTO authenticate(@RequestBody LoginRequestDTO loginRequest) {
        return TokenDTO.builder().token(accountService.authenticate(loginRequest)).build();
    }

    @PostMapping("/authorize")
    @ResponseStatus(HttpStatus.OK)
    @EnableRestCallLogging
    public String authorize(WebRequest request, @RequestBody List<String> permissions) {
        accountService.authorize(request.getHeader("Authorization"), permissions);
        return "authorized";
    }
}
