package lupricht.development.de.pongaping.Game.gamemodes;

import android.graphics.Canvas;

import lupricht.development.de.pongaping.Entity.Player;
import lupricht.development.de.pongaping.Game.Settings;
import lupricht.development.de.pongaping.Net.Client;
import lupricht.development.de.pongaping.Net.Server;


public class Multiplayer_Classic {

    private Player[] players;
    private Classic classic;
    private Server server;
    private Client client;


    public Multiplayer_Classic() {
        classic = new Classic();
        if (Settings.serverbool)
            inits();
        else
            initc();
    }


    public void inits() {
        server = Settings.server;
        classic.setPlayers(server.getPlayers());
    }


    public void initc() {
        client = Settings.client;
        classic.setPlayers(client.getPlayers());
    }


    public void render() {
        classic.render();
    }


    public void draw(Canvas canvas) {
        classic.draw(canvas);
    }

}
