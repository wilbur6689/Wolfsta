package com.teamwolf.config;


import com.teamwolf.beans.*;
import com.teamwolf.dataAccess.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = ("com.teamwolf"))
public class Config
{
    @Bean(name = "UserTAO")
    TAO<User> makeUserTAO()
    {
        return new TAOClass<User>(new User());
    }
    @Bean(name = "UserSessionTAO")
    TAO<UserSession> makeUserSessionTAO()
    {
        return new TAOClass<UserSession>(new UserSession());
    }
}
