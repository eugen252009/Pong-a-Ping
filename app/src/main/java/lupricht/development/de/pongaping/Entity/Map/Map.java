package lupricht.development.de.pongaping.Entity.Map;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by eugen on 13.09.2017.
 */

public class Map {

    private Rect rect;
    private Paint paint = new Paint();

    public Map(Rect rect, Paint paint) {
        this.rect = rect;
        this.paint = paint;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    public void render() {
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Rect getRect() {
        return rect;
    }
}