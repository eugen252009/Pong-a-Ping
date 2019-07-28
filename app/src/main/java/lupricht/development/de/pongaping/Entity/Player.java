package lupricht.development.de.pongaping.Entity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.net.InetAddress;

import lupricht.development.de.pongaping.Game.Tools.Tool;


/**
 * Created by eugen on 13.09.2017.
 */

public class Player {


    private final float DEFAULT_SPEED = 5;
    private float PauseSpeedX, PauseSpeedY;
    private Rect rect;
    private Paint paint;
    int width, lengh, x, y;
    private boolean isMoving = true;
    private int points = 0;
    private float SPEED;
    private final int DEFAULT_WIDTH = 50;
    private final int DEFAULT_LENGH = 150;
    private final Paint DEFAULT_PAINT = new Paint();
    private int id;
    float xvel = 0;
    float yvel = 0;
    float xacc = 0;
    float yacc = 0;
    private String username = "NOUSER";
    private InetAddress IP;
    private int port;
    private Point touch = new Point(0, 0);



    public Player(int width, int lengh, Paint paint, float SPEED) {
        this.width = width;
        this.lengh = lengh;
        this.rect = new Rect(0, 0, width, lengh);
        this.paint = paint;
        this.SPEED = SPEED;
    }


    public Player(int id, InetAddress Ip, int port, String username) {
        this.id = id;
        this.IP = Ip;
        this.port = port;
        this.username = username;
        this.width = 50;
        this.lengh = 150;
        this.rect = new Rect(0, 0, width, lengh);
        this.paint = new Paint();
        this.SPEED = 5;
        this.init(50, 50, new Paint(), 5);
    }


    public Player init(int width, int lengh, Paint paint, float SPEED) {
        this.width = width;
        this.lengh = lengh;
        this.rect = new Rect(0, 0, width, lengh);
        this.paint = paint;
        this.SPEED = SPEED;
        return this;
    }


    public void draw(Canvas canvas) {
        canvas.drawRect(x - width / 2, y - lengh / 2, x + width / 2, y + lengh / 2, paint);
    }


    public void render(float pos) {
        if (pos==getY()){
            return;
        }
        if (isMoving) {
            if (getY() < pos) {
                setY((int) (getY() + SPEED));
            }
            if (getY() > pos) {
                setY((int) (getY() - SPEED));
            }
            if (getY() < getLengh() / 2) {
                setY(getLengh() / 2);
            }
            if (getY() > Tool.Screensize.y - getLengh() / 2) {
                setY(Tool.Screensize.y - getLengh() / 2);
            }
        }

    }

    public void render() {
        yvel += yacc;
        if (yvel < -40) {
            yvel = -40;
        }
        if (yvel > 40) {
            yvel = 40;
        }
        setY(getY() + (int) yvel);
        if (getY() < getLengh() / 2) {
            setY(getLengh() / 2);
            yvel = 0;
            yacc = 0;
        }
        if (getY() > Tool.Screensize.y - getLengh() / 2) {
            setY(Tool.Screensize.y - getLengh() / 2);
            yvel = 0;
            yacc = 0;
        }


    }


    public void bouncyrender(float pos) {
        if (getY()==pos){
            return;
        }
        yacc = (pos-getY());
        yvel += yacc;
        if (yvel < -40) {
            yvel = -40;
        }
        if (yvel > 40) {
            yvel = 40;
        }
        setY(getY() + (int) yvel);
        if (getY() < getLengh() / 2) {
            setY(getLengh() / 2);
            yvel = 0;
            yacc = 0;
        }
        if (getY() > Tool.Screensize.y - getLengh() / 2) {
            setY(Tool.Screensize.y - getLengh() / 2);
            yvel = 0;
            yacc = 0;
        }
    }


    public void bouncyrender(float pos, int i) {
        yacc = (pos > getY() ? 0.1f : -0.1f);
        yvel += yacc;
        setY(getY() + (int) yvel);
        if (getY() < getLengh() / 2) {
            setY(getLengh() / 2);
        }
        if (getY() > Tool.Screensize.y - getLengh() / 2) {
            setY(Tool.Screensize.y - getLengh() / 2);
        }
    }

    //GETTER AND SETTER


    public Point getTouch() {
        return touch;
    }


    public void setTouch(Point touch) {
        this.touch = touch;
    }

    public void setTouch(int x, int y) {
        this.touch.set(x, y);
    }


    public InetAddress getIP() {
        return IP;
    }


    public void setIP(InetAddress IP) {
        this.IP = IP;
    }


    public int getPort() {
        return port;
    }


    public void setPort(int port) {
        this.port = port;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setPos(Point point) {
        this.y = point.y;
        this.x = point.x;
    }

    public void setPlayer(Player player) {
        this.username = player.username;
        this.id = player.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public void setxSpeed(float xvel) {
        setX((int) (getX() + xvel));
    }


    public void setySpeed(float xvel) {
        setY((int) (getY() + yvel));
    }


    public void pause() {
        PauseSpeedX = SPEED;
        PauseSpeedY = SPEED;
        SPEED = 0;
        SPEED = 0;
    }


    public void resume() {
        SPEED = DEFAULT_SPEED;
    }


    public void addPoint() {
        this.points++;
    }


    public int getLengh() {
        return lengh;
    }


    public int getWidth() {
        return rect.right - rect.left;
    }


    public int getX() {
        return x;
    }


    public void setX(int x) {
        this.x = x;
    }


    public Rect getRect() {
        return rect;
    }


    public void setRect(Rect rect) {
        this.rect = rect;
    }


    public void setRect(int x, int y, int xoff, int yoff) {
        this.rect.set(x, y, xoff, yoff);
    }


    public void setRectX(int x) {
        this.rect.set(x - width / 2, rect.top, x + width / 2, rect.bottom);
        this.x = x;
    }


    public void setRectY(int y) {
        this.rect.set(rect.left, y - width / 2, rect.right, y + width / 2);
        this.y = y;
    }


    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }


    public float getSPEED() {
        return SPEED;
    }


    public void setSPEED(float SPEED) {
        this.SPEED = SPEED;
    }


    public Paint getPaint() {
        return paint;
    }


    public void setPaint(Paint paint) {
        this.paint = paint;
    }


    public boolean isMoving() {
        return isMoving;
    }


    public void setMoving(boolean moving) {
        isMoving = moving;
    }


    public int getPoints() {
        return points;
    }


    public void setPoints(int points) {
        this.points = points;
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public void setLengh(int lengh) {
        this.lengh = lengh;
    }


    public Point getPoint() {
        return new Point(x, y);
    }


    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


}