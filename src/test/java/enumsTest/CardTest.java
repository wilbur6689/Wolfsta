package enumsTest;


import com.teamwolf.enums.CardEnum;
import org.junit.*;

public class CardTest {

    //DONE
    @Test
    public void getIdTest(){
        CardEnum c = CardEnum.FIVEOFDIAMONDS2;
        assert (c.getId() == 38); //basic functionality check
        assert (c.getId() == c.getId()); //consistency
        assert (c.getId() != 37); // checks converse false
    }

    //TODO
    @Test
    public void getRankTest(){

    }

    //TODO
    @Test
    public void getValueTest(){

    }

    //TODO
    @Test
    public void getCardByCardIdTest(){

    }
}
