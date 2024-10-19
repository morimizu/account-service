package org.benjaminrperry.accountservice.converter;

import lombok.experimental.UtilityClass;
import org.benjaminrperry.accountservice.api.AccountDTO;
import org.benjaminrperry.accountservice.db.model.Account;

import java.time.LocalDate;
import java.time.ZoneId;

@UtilityClass
public class AccountConverter {

    public static AccountDTO convert(Account account) {
        return AccountDTO.builder()
                .username(account.getUsername())
                .email(account.getEmail())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .joinDate(LocalDate.ofInstant(account.getJoinDate(), ZoneId.systemDefault()))
                .build();
    }

}
