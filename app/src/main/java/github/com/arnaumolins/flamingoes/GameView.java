package github.com.arnaumolins.flamingoes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GameView extends View {
    private Bitmap bmFloor1, bmFloor2;
    public static int sizeOfMap = 75*Constants.SCREEN_WIDTH/1000;
    private int h = 21, w = 12;
    private ArrayList<Floor> arrFloor = new ArrayList<>();

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bmFloor1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.good_cell);
        bmFloor1 = Bitmap.createScaledBitmap(bmFloor1, sizeOfMap, sizeOfMap, true);
        bmFloor2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.bad_cell_png);
        bmFloor2 = Bitmap.createScaledBitmap(bmFloor2, sizeOfMap, sizeOfMap, true);
        for (int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                arrFloor.add(new Floor(bmFloor1, j*sizeOfMap + Constants.SCREEN_WIDTH/2-(w/2)*sizeOfMap, i*sizeOfMap+100*Constants.SCREEN_HEIGHT/1920, sizeOfMap, sizeOfMap));
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xFFBB86FC);
        for (int i = 0; i < arrFloor.size(); i++){
            canvas.drawBitmap(arrFloor.get(i).getBm(), arrFloor.get(i).getX(), arrFloor.get(i).getY(), null);
        }
    }
}
