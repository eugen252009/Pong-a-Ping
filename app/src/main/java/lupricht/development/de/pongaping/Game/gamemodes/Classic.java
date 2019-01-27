package lupricht.development.de.pongaping.Game.gamemodes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import java.util.Arrays;
import java.util.Random;

import lupricht.development.de.pongaping.Entity.AI;
import lupricht.development.de.pongaping.Entity.Ball;
import lupricht.development.de.pongaping.Entity.Player;
import lupricht.development.de.pongaping.Game.Settings;
import lupricht.development.de.pongaping.Game.Tools.Tool;
import lupricht.development.de.pongaping.Net.DataPack;


public class Classic {
    private final String TAG = this.getClass().getName();

    float SPEED = 15f;
    private Player right;
    private Player left;
    private Ball ball;
    private Paint rightfont, leftfont;
    private Paint wallpaper = new Paint();
    private int offsetenemy = 0;
    private Random random = new Random();
    private AI ai = new AI();
    private int prediction = Tool.Screensize.y / 2;
//	private DataPack dataPack;


    public Classic() {
        right = new Player(25, 150, new Paint(Tool.getnewPaint(Color.CYAN)), SPEED);//ENEMY
        left = new Player(25, 150, new Paint(Tool.getnewPaint(Color.GREEN)), SPEED);//PLAYER
        left.setUsername(Settings.username);
        ball = new Ball(new Point(500, 500), 50, Tool.getnewPaint(Color.WHITE), 5);
        ball.turnY();
        rightfont = new Paint();
        rightfont.set(Tool.getnewPaint(Color.CYAN));
        rightfont.setTextSize(Tool.Screensize.x / 20);
        leftfont = new Paint();
        leftfont.set(Tool.getnewPaint(Color.GREEN));
        leftfont.setTextSize(Tool.Screensize.x / 20);
        wallpaper.setColor(Settings.wallpaper);
    }


    public void inits() {

    }

    public void initc() {

    }


    public DataPack collectData() {
        return new DataPack((int) ball.getX(), (int) ball.getY(), right.getX(), right.getY(),
                left.getX(), left.getY(), right.getPoints(), left.getPoints());
    }


    public void renderPlayer(Player[] players) {
        //ENEMY
        for (Player player : players) {
            player.render(0);
        }
        //PLAYER
        //PLAYER---LEFT
        if (ball.getX() - ball.getSize() < left.getX() + left.getWidth() / 2 && ball.getY() - ball
                .getSize() < left.getY() + left.getLengh() / 2 && ball.getY() + ball.getSize() > left
                .getY() - left.getLengh() / 2 && ball.getXSpeed() < 0) {
            ball.turnX();
        }
        //ENEMY---RIGHT
        if (ball.getX() + ball.getSize() > right.getX() - right.getWidth() / 2 && ball.getY() - ball
                .getSize() > right.getY() - right.getLengh() / 2 && ball.getY() + ball.getSize() < right
                .getY() + right.getLengh() / 2 && ball.getXSpeed() > 0) {
            ball.turnX();
        }
    }

    public void render(Point touch) {
        //ENEMY
        right.render(ball.getXSpeed() < 0 ? ball.getY() : ai.predict(ball) + offsetenemy);
        //PLAYER
        left.render(touch.y);
        ball.render();
        //PLAYER---LEFT
        if (ball.getX() - ball.getSize() < left.getX() + left.getWidth() / 2 && ball.getY() - ball
                .getSize() < left.getY() + left.getLengh() / 2 && ball.getY() + ball.getSize() > left
                .getY() - left.getLengh() / 2 && ball.getXSpeed() < 0) {
            //			Log.i ("Ball" , "Collision X:" + ball.getX () + " Y: " + ball.getY ());
            ball.turnX();
        }
        //ENEMY---RIGHT
        if (ball.getX() + ball.getSize() > right.getX() - right.getWidth() / 2 && ball.getY() - ball
                .getSize() > right.getY() - right.getLengh() / 2 && ball.getY() + ball.getSize() < right
                .getY() + right.getLengh() / 2 && ball.getXSpeed() > 0) {
            ball.turnX();
            //			Log.i ("Ball" , "Collision X:" + ball.getX () + " Y: " + ball.getY ());
            if (random.nextBoolean()) {
                offsetenemy = 0;
                offsetenemy += random.nextInt(right.getLengh());
            } else {
                offsetenemy = 0;
                offsetenemy -= random.nextInt(right.getLengh() / 2);
            }
        }
        if (ball.getY() + ball.getSize() > Tool.Screensize.y || ball.getY() - ball.getSize() < 0) {
            ball.turnY();
        }
        //		Log.d ("Prediction int" , "" + prediction);
        if (ball.getX() < ball.getSize()) {
            ball.turnX();
            right.addPoint();
            //			Log.d ("Ball" , "Wall X:" + ball.getX () + " Y: " + ball.getY ());
            ball.reset();
            offsetenemy = 0;
            prediction = Tool.Screensize.y / 2;
            return;
        }
        if (ball.getX() > Tool.Screensize.x - ball.getSize()) {
            left.addPoint();
            //			Log.d ("Ball" , "Wall X:" + ball.getX () + " Y: " + ball.getY ());
            ball.reset();
            offsetenemy = 0;
            prediction = Tool.Screensize.y / 2;
            return;
        }
    }

    public void render() {
        ball.render();
        if (ball.getX() > Tool.Screensize.x - ball.getSize()) {
            left.addPoint();
            //			Log.d ("Ball" , "Wall X:" + ball.getX () + " Y: " + ball.getY ());
            ball.reset();
            offsetenemy = 0;
            prediction = Tool.Screensize.y / 2;
            return;
        }
    }


    public void bouncyrender(Point touch) {
        //ENEMY
//		right.bouncyrender (ball.getXSpeed ()<0?ball.getY ():ai.predict (ball)+offsetenemy);
        right.bouncyrender(ball.getXSpeed() < 0 ? ball.getY() : ai.predicttwo(ball, right.getX())
                + offsetenemy);
        //PLAYER
        left.bouncyrender(touch.y);
        ball.render();
        //PLAYER---LEFT
        if (ball.getX() - ball.getSize() < left.getX() + left.getWidth() / 2 && ball.getY() - ball
                .getSize() < left.getY() + left.getLengh() / 2 && ball.getY() + ball.getSize() > left
                .getY() - left.getLengh() / 2 && ball.getXSpeed() < 0) {
            //			Log.i ("Ball" , "Collision X:" + ball.getX () + " Y: " + ball.getY ());
            ball.turnX();
        }
        //ENEMY---RIGHT
        if (ball.getX() + ball.getSize() > right.getX() - right.getWidth() / 2 && ball.getY() - ball
                .getSize() > right.getY() - right.getLengh() / 2 && ball.getY() + ball.getSize() < right
                .getY() + right.getLengh() / 2 && ball.getXSpeed() > 0) {
            ball.turnX();
            //			Log.i ("Ball" , "Collision X:" + ball.getX () + " Y: " + ball.getY ());
            if (random.nextBoolean()) {
                offsetenemy = 0;
                offsetenemy += random.nextInt(right.getLengh());
            } else {
                offsetenemy = 0;
                offsetenemy -= random.nextInt(right.getLengh() / 2);
            }
        }
        if (ball.getY() + ball.getSize() > Tool.Screensize.y || ball.getY() - ball.getSize() < 0) {
            ball.turnY();
        }
        //		Log.d ("Prediction int" , "" + prediction);
        if (ball.getX() < ball.getSize()) {
            ball.turnX();
            right.addPoint();
            //			Log.d ("Ball" , "Wall X:" + ball.getX () + " Y: " + ball.getY ());
            ball.reset();
            offsetenemy = 0;
            prediction = Tool.Screensize.y / 2;
            return;
        }
        if (ball.getX() > Tool.Screensize.x - ball.getSize()) {
            left.addPoint();
            //			Log.d ("Ball" , "Wall X:" + ball.getX () + " Y: " + ball.getY ());
            ball.reset();
            offsetenemy = 0;
            prediction = Tool.Screensize.y / 2;
            return;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(wallpaper.getColor());
        canvas.drawLine(Tool.Screensize.x / 2 - 1,
                0,
                Tool.Screensize.x / 2 + 1,
                Tool.Screensize.y,
                leftfont);
        left.draw(canvas);
        right.draw(canvas);
        ball.draw(canvas);
        canvas.drawText(left.getUsername() + ": " + left.getPoints(), Tool.Screensize.x / 8,
                100,
                leftfont);
        canvas.drawText(right.getUsername() + ": " + right.getPoints(), Tool.Screensize.x * 5 / 8,
                100,
                rightfont);
    }

    public void save(int gamemode) {
        Tool.save((gamemode + "," + collectData().getString()));
    }

    public void load() {
        String loadString = Tool.load();
        String load[] = loadString.split(",");
        Log.i(TAG, "load: " + Arrays.toString(load));
        ball.setPos(Integer.parseInt(load[1]), Integer.parseInt(load[2]));
        right.setX(Integer.parseInt(load[3]));
        right.setY(Integer.parseInt(load[4]));
        left.setX(Integer.parseInt(load[5]));
        left.setY(Integer.parseInt(load[6]));
        right.setPoints(Integer.parseInt(load[7]));
        left.setPoints(Integer.parseInt(load[8]));
    }

    public void screenchange() {
        left.setX(50);
        right.setX(Tool.Screensize.x - 50);
        ball.setPos(Tool.Screensize.x / 2, Tool.Screensize.y / 2);
        left.setLengh(Tool.Screensize.y / 90 * 12);
        left.setWidth(Tool.Screensize.x / 160 * 2);
        right.setLengh(Tool.Screensize.y / 90 * 12);
        right.setWidth(Tool.Screensize.x / 160 * 2);
        rightfont.setTextSize(Tool.Screensize.x / 20);
        leftfont.setTextSize(Tool.Screensize.x / 20);
        ai.setOffset(right.getX());
        ball.setXSpeed(Tool.Screensize.x / 150);
    }

    static public final float map(float value, float istart, float istop, float ostart, float ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }


    public Player getRight() {
        return right;
    }


    public Player getLeft() {
        return left;
    }


    public Ball getBall() {
        return ball;
    }


    public void setRight(Player right) {
        this.right = right;
    }


    public void setLeft(Player left) {
        this.left = left;
    }

    public void setPlayers(Player[] players) {
        right = players[0];
        left = players[1];
    }
}
