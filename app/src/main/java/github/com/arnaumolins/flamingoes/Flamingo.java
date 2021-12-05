package github.com.arnaumolins.flamingoes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Flamingo {

    public int actualFloor;
    private boolean move_right, move_left, move_up, move_bottom;
    private Bitmap bm;
    private int x, y;
    private Rect r;

    public Flamingo(Bitmap bm, int x, int y) {
        this.bm = bm;
        this.x = x;
        this.y = y;
        this.actualFloor = (12*y)+x;
    }

    public void update(){
        if(move_right){
            actualFloor = actualFloor+1;
            setX(x+GameView.sizeOfMap);
        }else if(move_left){
            actualFloor = actualFloor-1;
            setX(x-GameView.sizeOfMap);
        }else if(move_up){
            actualFloor = actualFloor-12;
            setY(y+GameView.sizeOfMap);
        }else if (move_bottom){
            actualFloor = actualFloor+12;
            setY(y-GameView.sizeOfMap);
        }
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
        s();
        this.move_right = move_right;
    }

    public boolean isMove_left() {
        return move_left;
    }

    public void setMove_left(boolean move_left) {
        s();
        this.move_left = move_left;
    }

    public boolean isMove_up() {
        return move_up;
    }

    public void setMove_up(boolean move_up) {
        s();
        this.move_up = move_up;
    }

    public boolean isMove_bottom() {
        return move_bottom;
    }

    public void setMove_bottom(boolean move_bottom) {
        s();
        this.move_bottom = move_bottom;
    }

    public void s(){
        this.move_right = false;
        this.move_left = false;
        this.move_up = false;
        this.move_bottom = false;
    }

    public void setR(Rect r){
        this.r=r;
    }

    public Rect getR(){
        return new Rect(this.x, this.y, this.x+GameView.sizeOfMap, this.y+GameView.sizeOfMap);
    }

}
