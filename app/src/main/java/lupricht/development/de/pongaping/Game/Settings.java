package lupricht.development.de.pongaping.Game;

import android.graphics.Color;
import android.graphics.Paint;

import lupricht.development.de.pongaping.Game.gamemodes.Multiplayer;
import lupricht.development.de.pongaping.Net.Client;
import lupricht.development.de.pongaping.Net.Server;


public class Settings {


    //Game
    public static int wallpaper = Color.BLACK;
    public static Paint ballpaint = new Paint(Color.WHITE);
    public static Server server;
    public static boolean loadGame = false;


    //Multiplayer&&GameModes
    public static int gameMode = 0;
    public static boolean serverbool = false;
    public static String ipAdress = "localhost";
    public static int port = 1331;
    public static Client client;
    public static String username = "Player 1";
    public static boolean startGame = false;
    public static Multiplayer multiplayer;


    public Settings() {
    }
}
