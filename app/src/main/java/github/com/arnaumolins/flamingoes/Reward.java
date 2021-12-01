package github.com.arnaumolins.flamingoes;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Reward {
    private int x, y;
    private Bitmap bm;
    private int rewardCell = (12*y)+x;

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

    public int getRewardCell() {
        return rewardCell;
    }

    public void setRewardCell(int rewardCell) {
        this.rewardCell = rewardCell;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bm, x, y, null);
    }

}
