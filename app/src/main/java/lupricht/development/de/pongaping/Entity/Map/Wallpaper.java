package lupricht.development.de.pongaping.Entity.Map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by eugen on 15.09.2017.
 */

public class Wallpaper {

    private Bitmap bitmap;
    private Paint[] paint;
    private int num = 1;

    public Wallpaper(Paint[] paint) {
        this.paint = paint;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setPaint(Paint[] paint) {
        this.paint = paint;
    }

    public void draw(Canvas canvas) {
        if (paint != null) canvas.drawColor(paint[num].getColor());
        else canvas.drawColor(Color.BLACK);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
