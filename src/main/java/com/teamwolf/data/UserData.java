package com.teamwolf.data;

import com.teamwolf.beans.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface UserData extends JpaRepository<User, Integer>
{
}
