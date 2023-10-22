package com.module_security.jwt;

import com.module_security.service.UserPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//Generation and validation token
public class JwtProvider {

    @Value("$(jwt.secret)")
    private String secret;
    @Value("$(jwt.expiration)")
    private int expiration;

    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        //Builder -> patron de diseño apra cuando una clase tiene muchos atributos y la mayor parte son opcionales
        //Ya nos e crena muchos contructos
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, "secret")
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .claim("roles", getRoles(userPrincipal))
                .claim("cara","fea")
                .compact();

    }
    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(){

    }

    private List<String> getRoles(UserPrincipal principal){
        return principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
