package org.benjaminrperry.accountservice.controller;

import lombok.RequiredArgsConstructor;
import org.benjaminrperry.accountservice.api.LoginRequestDTO;
import org.benjaminrperry.accountservice.configuration.EnableRestCallLogging;
import org.benjaminrperry.accountservice.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AccountService accountService;

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    @EnableRestCallLogging
    public String authenticate(@RequestBody LoginRequestDTO loginRequest) {
        return accountService.authenticate(loginRequest);
    }

    @PostMapping("/authorize")
    @ResponseStatus(HttpStatus.OK)
    @EnableRestCallLogging
    public String authorize(WebRequest request, @RequestBody List<String> permissions) {
        accountService.authorize(request.getHeader("Authorization"), permissions);
        return "authorized";
    }
}
