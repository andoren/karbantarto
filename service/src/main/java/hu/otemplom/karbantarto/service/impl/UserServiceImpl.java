package hu.otemplom.karbantarto.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import hu.otemplom.karbantarto.dao.UserDao;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidFullNameException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidIdException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidRoleException;
import hu.otemplom.karbantarto.model.Exceptions.User.InvalidUsernameException;
import hu.otemplom.karbantarto.model.Role;
import hu.otemplom.karbantarto.model.User;
import hu.otemplom.karbantarto.service.Exceptions.UserService.DuplicateUserException;
import hu.otemplom.karbantarto.service.Exceptions.UserService.UserDoesNotExistsException;
import hu.otemplom.karbantarto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@ComponentScan(basePackages = {"hu.otemplom.karbantarto.dao"})
public class UserServiceImpl implements UserService {

    static Algorithm algorithmHS = Algorithm.HMAC256("k35Vl1o1L5");
    @Autowired
    public UserServiceImpl(@Qualifier("fakeUserDao") UserDao dao) {
        this.dao = dao;
    }

    private UserDao dao;

    @Override
    public int addUser(User user) throws DuplicateUserException, InvalidIdException {
        return dao.addUser(user);
    }

    @Override
    public boolean modifyUser(User user) throws UserDoesNotExistsException {
        return dao.modifyUser(user);
    }

    @Override
    public boolean deleteUserByUserId(int userId) throws UserDoesNotExistsException {
        return dao.deleteUserByUserId(userId);
    }

    @Override
    public Collection<User> getAllUser() {
        return dao.getAllUser();
    }

    @Override
    public User getUserByUserId(int userId) throws UserDoesNotExistsException {
        return dao.getUserByUserId(userId);
    }

    @Override
    public String login(String username, String password) {
        User user = dao.login(username,password);
        if( user != null){
            return generateTokenFromUser(user);
        }
        return "";
    }

    @Override
    public User getUserFromToken(String token) throws InvalidIdException, InvalidFullNameException, InvalidRoleException, InvalidUsernameException {

        token = token.split(":")[1] ;
        token = token.trim();
        JWTVerifier verifier = JWT.require(algorithmHS)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        System.out.println(jwt.getClaim("realname").asString());
        User user = new User();
        user.setId(jwt.getClaim("userId").asInt());
        user.setEmail(jwt.getClaim("email").asString());
        user.setFullName(jwt.getClaim("realname").asString());
        user.setRole(Role.valueOf(jwt.getClaim("role").asString()));
        user.setUsername(jwt.getClaim("username").asString());
        return user;
    }

    @Override
    public String generateTokenFromUser(User user) {
        try {

            String token = JWT.create()
                    .withClaim("userId",user.getId())
                    .withClaim("username",user.getUsername())
                    .withClaim("realname",user.getFullName())
                    .withClaim("email",user.getEmail())
                    .withClaim("role",user.getRole().name())
                    .sign(algorithmHS);
            return token;
        } catch (JWTCreationException exception){
            System.out.println("JWT Generálási hiba");
            return "";
        }
    }

}
