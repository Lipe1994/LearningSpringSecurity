package ferreira.security.ferreira_security.infra.service;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }


    public String generateToken(Authentication authentication)
    {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(3600l);

        var scopes = authentication
            .getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
            .issuer("ferreira_security")
            .issuedAt(now)
            .expiresAt(expiry)
            .subject(authentication.getName())
            .claim("scope", scopes)
            .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    }

}
