package lupricht.development.de.pongaping.Game.gamemodes;

import android.graphics.Canvas;
import android.graphics.Point;


public class Bouncy {
    private Classic classic;

    public Bouncy(Classic classic) {
        this.classic = classic;
    }

    public void render(Point touch) {
        classic.bouncyrender(touch);
    }

    public void draw(Canvas canvas) {
        classic.draw(canvas);
    }

    public void screenchange() {
        classic.screenchange();
    }

    public void save(int gamemode) {
        classic.save(gamemode);
    }

}
