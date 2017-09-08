package canastaTest;

import com.teamwolf.beans.Game;
import com.teamwolf.canasta.Canasta;
import org.apache.log4j.Logger;
import org.junit.Test;

public class CanastaTest {

    protected static Logger log = Logger.getRootLogger();

    @Test //FIXME SQL
    public void createGame() {
        Canasta main = new Canasta();
        Game g = new Game("TestGame", null, 4);
        main.addGame(g);
    }

    @Test //FIXME SQL
    public void getGameById(){
        Canasta main = new Canasta();
        Game g = main.getGame(1);
        System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        if(g == null){
            System.out.println("noooooooooooooooooooooooooooooooooooooooo");
        }
        System.out.println(g.getGameId());
        System.out.println(g.getGameName());
        assert (g.getGameName().compareTo("firstGame") == 0);
    }

    @Test //FIXME SQL
    public void startGame(){
        Canasta main = new Canasta();
        Game g = new Game();
        g.setGameId(2);
        main.start(g);
    }
    //TODO I gave up trying to test for now
}
