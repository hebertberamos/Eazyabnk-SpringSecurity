package com.sec.authenticator.config;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EazyBankUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final EazyBankUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPwd = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if(passwordEncoder.matches(rawPwd, userDetails.getPassword())) {
            // here is possible to add othe type of validations like the age and whatever.
            return new UsernamePasswordAuthenticationToken(username, rawPwd, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid credentials...");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
