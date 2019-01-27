package lupricht.development.de.pongaping.Entity;

import java.util.Random;

import lupricht.development.de.pongaping.Game.Tools.Tool;


public class AI {
    private Random random = new Random();
    private int randommove = 0;
    int offset = 0;

    public AI() {
    }


    public int predict(Ball ball) {
        int ballx, bally, ballySpeed, ballxSpeed;
        ballx = (int) ball.getX();
        ballxSpeed = (int) ball.getXSpeed();
        bally = (int) ball.getY();
        ballySpeed = (int) ball.getYSpeed();
        int varx = ((Tool.Screensize.x - offset) - ballx) / ballxSpeed;
        int vary = (varx * ballySpeed) + bally;
        if (vary > 0 && vary < Tool.Screensize.y) {
            return vary;
        }
        if (vary < 0) {
            return vary * -1;
        }
        if (vary > Tool.Screensize.y) {
            vary = (Tool.Screensize.y * 2) - vary;
            vary *= -1;
            return vary;
        }
        return Tool.Screensize.y / 2;
    }

    public int predicttwo(Ball ball, int width) {
        int ballx = (int) ball.getX();
        int bally = (int) ball.getY();
        int ballXSpeed = (int) ball.getXSpeed();
        int ballYSpeed = (int) ball.getYSpeed();


        if (ballXSpeed < 0) {
            return bally;
        }

        int vx = ballx - width;
        int vy = ((vx / ballXSpeed) * ballYSpeed) + bally;

        if (bally < 0) {
            return Math.abs(bally);
        }
        if (bally > Tool.Screensize.y) {
            return (Tool.Screensize.y * 2) - bally;
        }


        return vy;
    }


    public int random() {
        return randommove;
    }

    public void newRandom() {
        randommove = random.nextInt(Tool.Screensize.y);
    }

    public void setOffset(int offset) {
        this.offset = Tool.Screensize.x - offset;
    }

}
