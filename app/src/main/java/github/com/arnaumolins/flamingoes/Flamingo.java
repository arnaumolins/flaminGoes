package github.com.arnaumolins.flamingoes;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Flamingo {

    private boolean move_right, move_left, move_up, move_bottom;
    private Bitmap bm;
    private int x, y;

    public Flamingo(Bitmap bm, int x, int y) {
        this.bm = bm;
        this.x = x;
        this.y = y;
        this.move_right = false;
        this.move_left = false;
        this.move_up = false;
        this.move_bottom = false;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bm, x, y, null);
    }

    public boolean isMove_right() {
        return move_right;
    }

    public void setMove_right(boolean move_right) {
        this.move_right = move_right;
    }

    public boolean isMove_left() {
        return move_left;
    }

    public void setMove_left(boolean move_left) {
        this.move_left = move_left;
    }

    public boolean isMove_up() {
        return move_up;
    }

    public void setMove_up(boolean move_up) {
        this.move_up = move_up;
    }

    public boolean isMove_bottom() {
        return move_bottom;
    }

    public void setMove_bottom(boolean move_bottom) {
        this.move_bottom = move_bottom;
    }

}
