package lupricht.development.de.pongaping.Net;

import java.io.Serializable;


public class DataPack implements Serializable {

    private int connectionType = 0;
    private boolean send = false;
    private int ballX = 0, ballY = 0, rightX = 0, rightY = 0, leftX = 0, leftY = 0, leftpoints = 0,
            rightpoints = 0;


    public DataPack() {
    }


    public DataPack(int ballX, int ballY, int rightX, int rightY, int leftX, int leftY) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.rightX = rightX;
        this.rightY = rightY;
        this.leftX = leftX;
        this.leftY = leftY;
        this.send = true;
    }

    public DataPack(int ballX, int ballY, int rightX, int rightY, int leftX, int leftY, int
            rightpoints, int leftpoints) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.rightX = rightX;
        this.rightY = rightY;
        this.leftX = leftX;
        this.leftY = leftY;
        this.send = true;
        this.leftpoints = leftpoints;
        this.rightpoints = rightpoints;
    }


    public int getBallX() {
        return ballX;
    }


    public void setBallX(int ballX) {
        this.ballX = ballX;
    }


    public int getBallY() {
        return ballY;
    }


    public void setBallY(int ballY) {
        this.ballY = ballY;
    }


    public int getRightX() {
        return rightX;
    }


    public void setRightX(int rightX) {
        this.rightX = rightX;
    }


    public int getRightY() {
        return rightY;
    }


    public void setRightY(int rightY) {
        this.rightY = rightY;
    }


    public int getLeftX() {
        return leftX;
    }


    public void setLeftX(int leftX) {
        this.leftX = leftX;
    }


    public int getLeftY() {
        return leftY;
    }


    public void setLeftY(int leftY) {
        this.leftY = leftY;
    }


    public void setSend(boolean send) {
        this.send = send;
    }


    public void sended() {
        this.send = false;
    }


    public String getString() {
        return (ballX + "," + ballY + "," + rightX + "," + rightY + "," + leftX + "," + leftY + ","
                + rightpoints + "," + leftpoints);
    }


    public byte[] getByteArray() {
        return (connectionType + getString()).getBytes();
    }


    public int getByteArrayLengh() {
        return (ballX + "," + ballY + "," + rightX + "," + rightY + "," + leftX + "," + leftY).getBytes().length;
    }


    public boolean send() {
        return send;
    }


    public void reset() {
        send = false;
    }
}
