package io.juniorlima.jwtauth.security.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
  @Value("${jwt_secret}")
  private String secret;

  public String generateToken(final String email)
      throws IllegalArgumentException, JWTCreationException {
    return JWT.create()
        .withSubject("UserDetails")
        .withClaim("email", email)
        .withIssuedAt(new Date())
        .withIssuer("JWT-AUTH-TEST")
        .sign(Algorithm.HMAC256(this.secret));
  }

  public String validateTokenAndRetrieveSubject(final String token)
      throws JWTVerificationException {
    final JWTVerifier verifier = JWT.require(Algorithm.HMAC256(this.secret))
        .withSubject("User Details")
        .withIssuer("JWT-AUTH-TEST")
        .build();
    final DecodedJWT jwt = verifier.verify(token);
    return jwt.getClaim("email").asString();
  }
}
