package hu.otemplom.karbantarto.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import hu.otemplom.karbantarto.controller.exceptions.InvalidTokenException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.model.Role;
import hu.otemplom.karbantarto.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthenticator implements IUserAuthenticator {
    static Algorithm algorithmHS = Algorithm.HMAC256("k35Vl1o1L5");
    @Override
    public String generateTokenFromUser(User user) {
        try {

            String token = JWT.create()
                    .withClaim("userId",user.getId())
                    .withClaim("username",user.getUsername())
                    .withClaim("realname",user.getFullname())
                    .withClaim("email",user.getEmail())
                    .withClaim("role",user.getRole().name())
                    .sign(algorithmHS);
            return token;
        } catch (JWTCreationException exception){
            System.out.println("JWT Generálási hiba");
            return "";
        }
    }

    @Override
    public User getUserFromRawToken(String rawToken) throws InvalidIdException, InvalidFullNameException, InvalidRoleException, InvalidUsernameException, InvalidTokenException {
        if(rawToken == null || rawToken.length() == 0){
            throw new InvalidTokenException("The token is invalid. The given token is: "+rawToken);
        }
        String token = rawToken.split(":")[1].trim();
        JWTVerifier verifier = JWT.require(algorithmHS)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        User user = new User();
        user.setId(jwt.getClaim("userId").asInt());
        user.setEmail(jwt.getClaim("email").asString());
        user.setFullname(jwt.getClaim("realname").asString());
        user.setRole(Role.valueOf(jwt.getClaim("role").asString()));
        user.setUsername(jwt.getClaim("username").asString());
        return user;
    }

    @Override
    public boolean userIsAdmin(String rawToken) throws InvalidIdException, InvalidUsernameException, InvalidFullNameException, InvalidRoleException, InvalidTokenException {
        return getUserFromRawToken(rawToken).getRole() == Role.Admin;
    }

    @Override
    public boolean userIsJanitor(String rawToken) throws InvalidIdException, InvalidUsernameException, InvalidFullNameException, InvalidRoleException, InvalidTokenException {
        return getUserFromRawToken(rawToken).getRole() == Role.Janitor;
    }

    @Override
    public boolean userIsUserOrAdmin(String rawToken) throws InvalidIdException, InvalidUsernameException, InvalidFullNameException, InvalidRoleException, InvalidTokenException {
        return getUserFromRawToken(rawToken).getRole() == Role.Admin;
    }


}
