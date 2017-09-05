import com.teamwolf.enums.*;
import org.junit.*;

public class CardTest
{
    @Test
    public void getCardByIDTest()
    {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
        {
            for(Card c : Card.values())
            {
                Card t = Card.getCardbyCardId(c.getId());
                assert (t == c);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }
}
