package github.com.arnaumolins.flamingoes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Reward {
    private int x, y;
    private Bitmap bm;
    private Rect r;

    public Reward(int x, int y, Bitmap bm) {
        this.x = x;
        this.y = y;
        this.bm = bm;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setR(Rect r){
        this.r=r;
    }

    public Rect getR(){
        return new Rect(this.x, this.y, this.x+GameView.sizeOfMap, this.y+GameView.sizeOfMap);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bm, x, y, null);
    }

}
