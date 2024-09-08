package ecommersite.swiftshopper.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService
{
    Map<String,Object> claims = new HashMap<>();
    private String secretKey = "";

    public String generateToken(String fullName)
    {
        return Jwts.builder()
                .claims().add(claims)
                .subject(fullName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 50))
                .and().signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey()
    {
        try
        {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            secretKey = Base64.getEncoder().encodeToString(keyGenerator.generateKey().getEncoded());
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            return Keys.hmacShaKeyFor(keyBytes);
        }
        catch (NoSuchAlgorithmException e) {throw new RuntimeException(e);}
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String extractUserName(String jwtToken)
    {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    private Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails)
    {
        final String userName = extractUserName(jwtToken);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }
}
