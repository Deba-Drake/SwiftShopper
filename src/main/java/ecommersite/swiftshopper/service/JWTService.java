package ecommersite.swiftshopper.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    private Key generateKey()
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
}
