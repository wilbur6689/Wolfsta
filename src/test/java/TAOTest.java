import com.google.gson.*;
import com.teamwolf.beans.*;
import com.teamwolf.dataAccess.*;
import org.junit.*;

public class TAOTest
{

    @Test
    public void Users()
    {
        Gson geeson = new Gson();
        TAO<User> UserTao = new TAO<>(new User());


        User user1 = UserTao.getByUnique(1);
        System.out.println( geeson.toJson(user1) );

    }
    @Test
    public void Teams()
    {
        Gson geeson = new Gson();
        TAO<Team> TeamTao = new TAO<>(new Team());


        Team Team1 = TeamTao.getByUnique(1);
        System.out.println( geeson.toJson(Team1) );

    }
    @Test
    public void Rewards()
    {
        Gson geeson = new Gson();
        TAO<Reward> RewardTao = new TAO<>(new Reward());


        Reward Reward1 = RewardTao.getByUnique(1);
        System.out.println( geeson.toJson(Reward1) );

    }
    @Test
    public void Players()
    {
        Gson geeson = new Gson();
        TAO<Player> PlayerTao = new TAO<>(new Player());


        Player Player1 = PlayerTao.getByUnique(1);
        System.out.println( geeson.toJson(Player1) );

    }
    @Test
    public void Games()
    {
        Gson geeson = new Gson();
        TAO<Game> GameTao = new TAO<>(new Game());


        Game Game1 = GameTao.getByUnique(1);
        System.out.println( geeson.toJson(Game1) );

    }
    @Test
    public void FriendLookUps()
    {
        Gson geeson = new Gson();
        TAO<FriendLookUp> FriendLookUpTao = new TAO<>(new FriendLookUp());


        FriendLookUp FriendLookUp1 = FriendLookUpTao.getByUnique(1);
        System.out.println( geeson.toJson(FriendLookUp1) );

    }
}
