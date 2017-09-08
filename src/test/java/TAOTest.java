import com.google.gson.*;
import com.teamwolf.beans.*;
import com.teamwolf.dataAccess.*;
import org.junit.*;

public class TAOTest
{

    //@Test
    public void Users()
    {
        Gson geeson = new Gson();
        TAO<User> UserTao = new TAOClass<>(new User());


        User user1 = UserTao.getById(1);
        System.out.println( geeson.toJson(user1) );

        User user2 = new User();
        user2.setUsername("cJohnson");
        user2.setPassword("lemons");
        user2.setGamesPlayed(1);
        user2.setGamesWon(5);
        user2.setUserid(null);

        User user3 = UserTao.add(user2);

        System.out.println( geeson.toJson(user2) );
        System.out.println( geeson.toJson(user3) );
        user3.setGamesWon(1);

        UserTao.update(user3);

        user3 = UserTao.getById(user3.getUsername());
        System.out.println( geeson.toJson(user3) );

        UserTao.delete(user3);

        user3 = UserTao.getById(user3.getUserid());
        System.out.println( geeson.toJson(user3) );


    }
    //@Test
    public void Teams()
    {
        Gson geeson = new Gson();
        TAO<Team> TeamTao = new TAOClass<>(new Team());


        Team Team1 = TeamTao.getById(1);
        System.out.println( geeson.toJson(Team1) );

    }
    //@Test
    public void Rewards()
    {
        Gson geeson = new Gson();
        TAO<Reward> RewardTao = new TAOClass<>(new Reward());


        Reward Reward1 = RewardTao.getById(1);
        System.out.println( geeson.toJson(Reward1) );

    }
    //@Test
    public void Players()
    {
        Gson geeson = new Gson();
        TAO<Player> PlayerTao = new TAOClass<>(new Player());


        Player Player1 = PlayerTao.getById(1);
        System.out.println( geeson.toJson(Player1) );

    }
    //@Test
    public void Games()
    {
        Gson geeson = new Gson();
        TAO<Game> GameTao = new TAOClass<>(new Game());


        Game Game1 = GameTao.getById(1);
        System.out.println( geeson.toJson(Game1) );

    }
    //@Test
    public void FriendLookUps()
    {
        Gson geeson = new Gson();
        TAO<FriendLookUp> FriendLookUpTao = new TAOClass<>(new FriendLookUp());


        FriendLookUp FriendLookUp1 = FriendLookUpTao.getById(1);
        System.out.println( geeson.toJson(FriendLookUp1) );

    }
}
