package lupricht.development.de.pongaping.Entity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import lupricht.development.de.pongaping.Game.Tools.Tool;


/**
 * Created by eugen on 13.09.2017.
 */

public class Ball {

    private float DEFAULT_X_BALLSPEED = 5;
    private float DEFAULT_Y_BALLSPEED = 5;

    private Paint paint;
    private boolean left = false,
            right = false,
            up = false,
            down = false;
    private float XSpeed, YSpeed;
    private float PauseSpeedX, PauseSpeedY;
    private Point point;
    private int size;


    public Ball(Point point, int size, Paint paint, float SPEED) {
        this.point = new Point(0, 0);
        this.paint = paint;
        this.XSpeed = SPEED;
        this.YSpeed = SPEED;
        this.point = point;
        this.size = size;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(point.x, point.y, size, paint);
    }

    public void render() {
        point.set((int) (point.x + XSpeed), (int) (point.y + YSpeed));
    }


    //GETTER & SETTER


    public float getXSpeed() {
        return XSpeed;
    }


    public void setXSpeed(float XSpeed) {
        this.XSpeed = XSpeed;
        this.DEFAULT_X_BALLSPEED = XSpeed;
    }


    public void setPos(int x, int y) {
        point.x = x;
        point.y = y;
    }


    public Point getPoint() {
        return point;
    }


    public void setPoint(Point point) {
        this.point = point;
    }


    public int getSize() {
        return size;
    }


    public void turnX() {
        this.XSpeed *= -1;
    }


    public void turnY() {
        this.YSpeed *= -1;
    }


    public void pause() {
        PauseSpeedX = XSpeed;
        PauseSpeedY = YSpeed;
        XSpeed = 0;
        YSpeed = 0;
    }


    public float getX() {
        return point.x;
    }

    public float getY() {
        return point.y;
    }


    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
        this.right = !left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
        this.left = !right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
        this.down = !up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
        this.up = !down;
    }


    public void setSize(int size) {
        this.size = size;
    }


    public void reset() {
        point.set(Tool.Screensize.x / 2, Tool.Screensize.y / 2);
        setYSpeed(DEFAULT_Y_BALLSPEED);
        setXSpeed(DEFAULT_X_BALLSPEED);
    }


    public float getYSpeed() {
        return YSpeed;
    }


    public void setYSpeed(float YSpeed) {
        this.YSpeed = YSpeed;
        this.DEFAULT_Y_BALLSPEED = YSpeed;
    }
}