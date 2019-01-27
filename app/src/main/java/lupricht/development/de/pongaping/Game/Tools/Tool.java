package lupricht.development.de.pongaping.Game.Tools;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Point;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Arrays;

/* <string name="app_name">Pong a Ping BETA</string>
    <string name="balloptions">Ball Optionen</string>
    <string name="wallpaper">Hintergrundbild</string>
    <string name="developing">In Entwicklung…</string>
    <string name="difficulty">Schwierigkeitsgrad</string>
    <string name="musicoptions">Musik Optionen</string>
    <string name="volume">Lautstärke</string>
    <string name="choosesong">Wähle deinen Song:</string>
    <string name="back">Zurück</string>
    <string name="gameresume">Spiel fortsetzen</string>
    <string name="player">Spieler</string>
    <string name="enemy">Gegner</string>
    <string name="easy">Einfach</string>
    <string name="medium">Mittel</string>
    <string name="resetscore">Punkte zurücksetzen?</string>
    <string name="reset">Spielstand zurücksetzen</string>
    <string name="yesdelete">Ja, lösche den Spielstand</string>
    <string name="nodelete">Nein, Lösche den Spielstand nicht</string>
    <string name="ultra">Ultra schwer</string>
    <string name="mute">Stumm</string>
    <string name="choosewallpaper">Wähle dein HintergrundBild</string>
    <string name="pick_a_color">Wähle deine Farbe</string>
    <string name="black">Schwarz</string>
    <string name="blue">Blau</string>
    <string name="red">Rot</string>
    <string name="white">Weiß</string>
    <string name="green">Grün</string>
    <string name="cyan">Türkis</string>
    <string name="chooseBall">Wähle die Ball Farbe</string>
    <string name="options">Optionen</string>
    <string name="now">Aktueller Schwierigkeitsgrad</string>
    <string name="chooseGameMode">Welchen Spielmodus willst du spielen?</string>
    <string name="Classic">Klassisch</string>
    <string name="About">Über</string>
    <string name="choose_gamemode">Wählen Sie Bitte einen Spielmodus aus</string>
    <string name="gamemode_multiplayer">Multiplayer</string>
    <string name="game_creator">Spiel Entwickler</string>
    <string name="Credits">Programmierung und Design:</string>
    <string name="logo">Logo</string>
    <string name="hard">Besoffen</string>*/
public class Tool extends Application {

    private static final String KEY = "Pong_a_Ping";

    public static Point Screensize = new Point(0, 0);
    public static Point GameSize = new Point(0, 0);


    public Tool() {
    }


    private static Context context;


    public static void initScreen(Point ScreenSize) {
        Screensize = ScreenSize;
        GameSize.set((int) (Screensize.x * .75), (int) (Screensize.y * .75));
    }


    public static void initScreen(int x, int y) {
        Screensize.set(x, y);
        GameSize.set((int) (x * .75), (int) (y * .75));
    }


    //CONTEXT
    public static Context getAppContext() {
        return Tool.context;
    }


    //METHODS
    public static Paint getnewPaint(int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setTextSize(100);
        return paint;
    }


    public void onCreate() {
        super.onCreate();
        Tool.context = getApplicationContext();
    }


    public static void save(String object) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                getAppContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY, object);
        try {
            Log.i("Game saved", "" + editor.commit());
            Log.i("Game saved", Arrays.toString(object.split(",")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String load() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                getAppContext());
        return sharedPreferences.getString(KEY, null);
    }
}