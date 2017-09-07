package com.teamwolf.controller.sessionlogic;

import com.teamwolf.beans.*;
import com.teamwolf.controller.*;
import com.teamwolf.dataAccess.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.security.*;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;

@Component
@Scope(value = "prototype")
public class Token
{

    private UserSession session;
    private HttpServletResponse resp;
    private User user;

    private TAO<User> UserTAO;
    private TAO<UserSession> SessionTAO;

    @Autowired
    public Token(@CookieValue(value = "WolfstaToken", defaultValue = "") String tokenString,
                 HttpServletResponse resp, TAO<User> userTAO, TAO<UserSession> sessionTAO)
    {
        this.resp = resp;
        UserTAO = userTAO;
        SessionTAO = sessionTAO;
        this.setToken(tokenString);

    }

    public void check()
    {
        if (!this.isValid())
            throw new AuthorizationException("You must be logged in to access this resource");
    }

    public boolean isValid()
    {
        return (user != null);
    }

    public String authenticate(String username, String password)
    {
        Collection<User> l =  UserTAO.getBy("username",username);
        if(l.size() == 1)
        {
            this.user = (User) l.toArray()[0];
            if((this.user.getPassword() != null) && (this.user.getPassword().equals(password)))
            {
                byte[] data = new byte[15];
                new SecureRandom().nextBytes(data);
                String token = Base64.getEncoder().encodeToString(data);
                Timestamp t = Timestamp.from(Instant.now());

                session = new UserSession();
                session.setToken(token);
                session.setStartTime(t);
                session.setUserId(this.user.getUserid());

                this.SessionTAO.add(session);
                resp.addCookie(new Cookie("WolfstaToken",token));
                return token;
            }
        }
        throw new AuthorizationException("invalid username or password");
    }


    public String getTokenString()
    {
        if (session != null)
            return session.getToken();
        else
            return "";
    }

    public void setToken(String tokenString)
    {
        if("".equals(tokenString))
        {
            user = null;
            session = null;
        }
        else
        {
            session = this.SessionTAO.getById(tokenString);
            if(session != null)
                user = this.UserTAO.getById(session.getUserId());
        }
    }

    public HttpServletResponse getResp()
    {
        return resp;
    }

    public void setResp(HttpServletResponse resp)
    {
        this.resp = resp;
    }

    public TAO<User> getUserTAO()
    {
        return UserTAO;
    }

    public void setUserTAO(TAO<User> userTAO)
    {
        UserTAO = userTAO;
    }

    public TAO<UserSession> getSessionTAO()
    {
        return SessionTAO;
    }

    public void setSessionTAO(TAO<UserSession> sessionTAO)
    {
        SessionTAO = sessionTAO;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
