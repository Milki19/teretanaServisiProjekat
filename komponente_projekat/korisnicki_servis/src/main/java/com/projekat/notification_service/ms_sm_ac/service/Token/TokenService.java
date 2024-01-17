package com.projekat.notification_service.ms_sm_ac.service.Token;

import io.jsonwebtoken.Claims;

public interface TokenService {

    String generate(Claims claims);

    Claims parseToken(String jwt);
}
