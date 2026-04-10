package taskmanager.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkey12"; // 32+ chars
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    // FIX: Added 'role' parameter - was using undefined variable before
    public static String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)  // FIX: was .claim("role", role) with undefined 'role'
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    // FIX: Was returning role instead of subject (username)
    public static String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // FIX: was .get("role", String.class) — wrong, that returns the role not username
    }

    public static String extractRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}