package org.benjaminrperry.accountservice.controller;

import lombok.RequiredArgsConstructor;
import org.benjaminrperry.accountservice.api.LoginRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    public String authenticate(@RequestBody LoginRequestDTO loginRequest) {

    }
}
