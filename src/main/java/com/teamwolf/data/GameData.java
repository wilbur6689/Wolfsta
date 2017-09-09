package com.teamwolf.data;

import com.teamwolf.beans.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Transactional
@Repository
public interface GameData extends JpaRepository<Game,Integer>
{
}