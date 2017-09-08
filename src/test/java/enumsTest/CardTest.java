package enumsTest;


import com.teamwolf.enums.*;
import org.junit.*;
import org.apache.log4j.Logger;

public class CardTest {


    @Test
    public void getIdTest(){
        Card c = Card.FIVEOFDIAMONDS2;
        assert (c.getId() == 38); //basic functionality check
        assert (c.getId() == c.getId()); //consistency
        assert (c.getId() != 37); // checks converse false
    }

    @Test
    public void getRankTest(){
        Card c1 = Card.FIVEOFDIAMONDS2;
        Card c2 = Card.JACKOFCLUBS2;
        Card c3 = Card.JOKER3;
        Card c4 = Card.FIVEOFCLUBS1;
        assert (c1.getRank() == 5);
        assert (c2.getRank() != c1.getRank());
        assert (c3.getRank() == 14);
        assert (c1.getRank() == c4.getRank());

    }

    @Test
    public void getValueTest(){
        Card c1 = Card.FIVEOFDIAMONDS2;
        Card c2 = Card.JACKOFCLUBS2;
        Card c3 = Card.JOKER3;
        Card c4 = Card.ACEOFSPADES1;
        Card c5 = Card.TWOOFSPADES1;
        Card c6 = Card.THREEOFCLUBS1;
        Card c7 = Card.THREEOFDIAMONDS1;
        assert (c1.getValue() == 5);
        assert (c2.getValue() == 10);
        assert (c3.getValue() == 50);
        assert (c4.getValue() == 20);
        assert (c4.getValue() == c5.getValue());
        assert (c6.getValue() == 5);
        assert (c7.getValue() == 100);
    }

    @Test
    public void getCardByCardIdTest(){
        assert (Card.getCardbyCardId(5) == Card.ACEOFDIAMONDS1);
        assert (Card.getCardbyCardId(109) == null);
    }

    @Test
    public void isRedThreeTest(){
        assert (!Card.THREEOFSPADES1.isRedThree());
        assert (Card.THREEOFDIAMONDS2.isRedThree());
    }
}
