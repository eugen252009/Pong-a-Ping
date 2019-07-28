package lupricht.development.de.pongaping.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;
import java.util.TimerTask;

import lupricht.development.de.pongaping.Game.Tools.Tool;
import lupricht.development.de.pongaping.Game.gamemodes.Bouncy;
import lupricht.development.de.pongaping.Game.gamemodes.Classic;
import lupricht.development.de.pongaping.Game.gamemodes.Multiplayer;


/**
 * Created by eugen on 13.09.2017.
 */

public class Surface extends SurfaceView implements SurfaceHolder.Callback {

/*

        V TODO stetig steigender schwierigekeitsgrad
        TODO: LVL System / PunkteSystem Skalierung der lvl
        TODO(Blockaden und so weiter(verschiedene Formen)
        TODO Wurmloch
        V TODO Ball schneller/langsam/
        TODO Powerups (offensiv und Defensiv) Feuer, Wasser, aufTeilen, kurzzeitich unbesiegbar, Wirbel, Blockadenball (wo der Ball auftrifft entsteht eine Blockade)
        TODO Ball schießen und wenn der gegner ihn fängt dann ingame währung für Powerups, wenn er ihn nicht bekommt,bekommt niemand was
       -> TODO Multiplayer 2-4 Spieler (Wlan)
        V TODO Verschiedene Bälle
        V TODO inteligentere KI
        V TODO Spielstand speichern
        TODO BAllarten ändern
        TODO Größe,
        V TODO Farbe
        V TODO HintergrundBild/Farbe ändern
        TODO Blockaden gehn nach 3 Hits kaputt
        TODO Rangliste
        V TODO APP ICON
        TODO Google ACC verbindung zwecks EP und Quests
        V TODO SOUND
        TODO DREIECKIGER BALL
   
    
    * *///

    private MainThread thread;
    private Classic classic = new Classic();
    private Multiplayer multiplayer;
    private Bouncy bouncy;
    private Point touch = new Point(0, 0);
    private int gamemode = 0;
    private Timer timer = new Timer();
    private int Countdown = 4;
    private boolean render = false;
    private Paint paint = new Paint();


    //GAME CONSTRUCTOR
    public Surface(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        init();
    }


    private void init() {
        paint.setColor(Color.WHITE);
        paint.setTextSize(250);
        if (Settings.loadGame) {
            Settings.gameMode = Integer.parseInt(Tool.load().split(",")[0]);
            classic.load();
        } else {
            gamemode = Settings.gameMode;
        }
        switch (gamemode) {
            case 1:
                multiplayer = new Multiplayer();
                break;
            case 2:
                bouncy = new Bouncy(classic);
                break;
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (Countdown == 0) {
                    render = true;
                    timer.cancel();
                } else {
                    --Countdown;
                }
            }
        }, 1000, 1000);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touch.set((int) event.getX(), (int) event.getY());
        return true;
    }


    public void update() {
        if (render) {
            switch (gamemode) {
                case 0:
                    classic.render(touch);
                    break;
                case 1:
                    multiplayer.render(touch);
                    break;
                case 2:
                    bouncy.render(touch);
                    break;
            }
        }
    }


    public void draw(Canvas canvas) {
        super.draw(canvas);
        switch (gamemode) {
            case 0:
                classic.draw(canvas);
                break;
            case 1:
                multiplayer.draw(canvas);
                break;
            case 2:
                bouncy.draw(canvas);
                break;
        }
        if (!render) {
            canvas.drawText("" + (Countdown + 1), Tool.Screensize.x / 2, Tool.Screensize.y / 3,
                    paint);
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Tool.initScreen(width, height);
        thread = new MainThread(holder, this);
        thread.setRunning(true);
        thread.start();
        screenchange();
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.setRunning(false);
        try {
            thread.join();
            save(gamemode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    void save(int gamemode) {
        switch (gamemode) {
            case 0:
                classic.save(gamemode);
                break;
            case 1:
                multiplayer.save(gamemode);
                break;
            case 2:
                bouncy.save(gamemode);
                break;
        }
    }


    void screenchange() {
        switch (gamemode) {
            case 0:
                classic.screenchange();
                break;
            case 1:
                multiplayer.screenchange();
                break;
            case 2:
                bouncy.screenchange();
                break;
        }
    }
}