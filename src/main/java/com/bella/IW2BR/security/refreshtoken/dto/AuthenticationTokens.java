package com.bella.IW2BR.security.refreshtoken.dto;

import com.bella.IW2BR.entities.user.dto.GetUserWithJwtToken;

public record AuthenticationTokens(
    GetUserWithJwtToken dto,
String refreshToken) {}
