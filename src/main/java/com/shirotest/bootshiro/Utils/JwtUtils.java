package com.shirotest.bootshiro.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    //设置过期时间
    private static final long EXPIRE_TIME = 5 * 60 * 1000;
    //秘钥
    public static final String secret_key = "SECRET_VALUE";
    //请求头
    public static final String AUTH_HEADER = "X-Authorization-With";

    /**
     * 验证token是否正确
     * @param token
     * @param userName
     * @param secret
     * @return
     */
    public static boolean verify(String token,String userName,String secret){
        try {
            Algorithm algorithm  = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim("username",userName).build();
            jwtVerifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获得token的自定义信息，无需secret解密也能获得
     * @param token
     * @param filed
     * @return
     */
    public static String getClaimFiled(String token,String filed){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(filed).asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成签名
     * @param username
     * @param secret
     * @return
     */
    public static String sign(String username,String secret){
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            //附带username，nickname等信息
            return JWT.create().withClaim("username",username)
                    .withExpiresAt(date).sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取token的签发时间
     * @param token
     * @return
     */
    public static Date getIssueAt(String token){

        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getExpiresAt();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证token是否过期
     * @return
     */
    public static boolean isTokenExpired(String token){

        Date now = Calendar.getInstance().getTime();
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getExpiresAt().before(now);
    }

    /**
     * 刷新token的过期时间
     * @param token
     * @param secret
     * @return
     */
    public static String refreshTokenExpired(String token,String secret){
        DecodedJWT jwt = JWT.decode(token);
        Map<String, Claim> claims = jwt.getClaims();
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Builder builer = JWT.create().withExpiresAt(date);
            for (Map.Entry<String, Claim> entry : claims.entrySet()) {
                builer.withClaim(entry.getKey(), entry.getValue().asString());
            }
            return builer.sign(algorithm);
        } catch (JWTCreationException | UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 生成16随机盐
     * @return
     */
    public static String generateSalt(){
        SecureRandomNumberGenerator secureNum = new SecureRandomNumberGenerator();
        String hex = secureNum.nextBytes(16).toHex();
        return hex;
    }


}
