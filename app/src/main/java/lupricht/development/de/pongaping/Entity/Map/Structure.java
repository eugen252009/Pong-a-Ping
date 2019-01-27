package lupricht.development.de.pongaping.Entity.Map;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by eugen on 13.09.2017.
 */

public class Structure {

    private Rect rect;
    private Paint paint;


    public Structure(Rect rect, Paint paint) {
        this.rect = rect;
        this.paint = paint;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    public Rect getRect() {
        return rect;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
