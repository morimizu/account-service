/*
 * Copyright (C) [2022] IGT
 * This software and all information contained therein are confidential and proprietary
 * and shall not be duplicated, used, disclosed, or disseminated in any way except as authorized
 * by the applicable license agreement, without the express written permission of IGT.
 * All authorized reproductions must be marked with this language.
 */

package org.benjaminrperry.accountservice.configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class NoSniffFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var origin  = ((HttpServletRequest) request).getHeader("Origin");
        log.info("NoSniffFilter - Origin: " + origin);
        ((HttpServletResponse) response)
                .setHeader("X-Content-Type-Options", "nosniff");
        chain.doFilter(request, response);
    }
}
