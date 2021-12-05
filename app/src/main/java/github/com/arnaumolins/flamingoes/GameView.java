package github.com.arnaumolins.flamingoes;

import static java.util.Arrays.sort;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    private static Context mContext;
    private Bitmap bmFloor1, bmLava, bmFlamingo, bmReward;
    public static int sizeOfMap = 75*Constants.SCREEN_WIDTH/1000;
    private int h = 21, w = 12;
    private int count = 0;
    private ArrayList<Floor> arrFloor = new ArrayList<>();
    private Flamingo flamingo;
    private Reward reward;
    private boolean move = false;
    private float mx, my;
    private int[] arrLava = new int[40];
    private Random rd;
    private Handler handler;
    private Runnable r;
    private TextView counterReward;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        counterReward = (TextView) findViewById(R.id.counterReward);
        bmFloor1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.good_cell);
        bmFloor1 = Bitmap.createScaledBitmap(bmFloor1, sizeOfMap, sizeOfMap, true);
        bmLava = BitmapFactory.decodeResource(this.getResources(), R.drawable.bad_cell_png);
        bmLava = Bitmap.createScaledBitmap(bmLava, sizeOfMap, sizeOfMap, true);
        bmFlamingo = BitmapFactory.decodeResource(this.getResources(), R.drawable.flamingo);
        bmFlamingo = Bitmap.createScaledBitmap(bmFlamingo, sizeOfMap, sizeOfMap, true);
        bmReward = BitmapFactory.decodeResource(this.getResources(), R.drawable.reward);
        bmReward = Bitmap.createScaledBitmap(bmReward, sizeOfMap, sizeOfMap, true);
        generateMap(); // Generate the map with the lava positions and create a reward.
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int swipe = event.getActionMasked();
        switch (swipe){
            case MotionEvent.ACTION_MOVE:{
                if (move == false){
                    mx = event.getX();
                    my = event.getY();
                    move = true;
                }else{
                    if(mx - event.getX() > (100*Constants.SCREEN_WIDTH)/1080 && !flamingo.isMove_right()){
                        mx = event.getX();
                        my = event.getY();
                        flamingo.setMove_left(true);
                    }else if(event.getX() - mx > (100*Constants.SCREEN_WIDTH)/1080 && !flamingo.isMove_left()){
                        mx = event.getX();
                        my = event.getY();
                        flamingo.setMove_right(true);
                    }else if(my - event.getY() > (100*Constants.SCREEN_HEIGHT)/1080 && !flamingo.isMove_bottom()){
                        mx = event.getX();
                        my = event.getY();
                        flamingo.setMove_bottom(true);
                    }else if(event.getY() - my > (100*Constants.SCREEN_HEIGHT)/1080 && !flamingo.isMove_up()){
                        mx = event.getX();
                        my = event.getY();
                        flamingo.setMove_up(true);
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:{
                mx = 0;
                my = 0;
                move = false;
                break;
            }
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xFFFA9CE5);
        for (int i = 0; i < arrFloor.size(); i++){
            canvas.drawBitmap(arrFloor.get(i).getBm(), arrFloor.get(i).getX(), arrFloor.get(i).getY(), null);
        }
        flamingo.update();
        flamingo.draw(canvas);
        reward.draw(canvas);
        if(flamingo.getR().intersect(reward.getR())){
            generateMap();
            count++;
            //editing textView
        }
        for(int i = 0; i < arrLava.length; i++) {
            if (flamingo.getR().intersect(arrFloor.get(arrLava[i]).getR())) {
                gameOverView();
            }
        }
        handler.postDelayed(r, 400);

    }


    public void generateMap(){
        generateLavaArray(); // Generate array of Lava positions.
        int k = 0;
        for (int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                if (k < 40 && (w * i) + j == arrLava[k]) {
                    arrFloor.add(new Floor(bmLava, j * sizeOfMap + Constants.SCREEN_WIDTH / 2 - (w / 2) * sizeOfMap, i * sizeOfMap + 100 * Constants.SCREEN_HEIGHT / 1920, sizeOfMap, sizeOfMap));
                    arrFloor.get((w*i)+j).setR(new Rect(arrFloor.get(j).getX(), arrFloor.get(i).getY(), arrFloor.get(j).getX()+sizeOfMap, arrFloor.get(i).getY()+sizeOfMap));
                    k = k + 1;
                } else {
                    arrFloor.add(new Floor(bmFloor1, j * sizeOfMap + Constants.SCREEN_WIDTH / 2 - (w / 2) * sizeOfMap, i * sizeOfMap + 100 * Constants.SCREEN_HEIGHT / 1920, sizeOfMap, sizeOfMap));
                }
            }
        }
        generateFlamingo(); // Create the flamingo.
        generateReward(); // Create a reward.
    }

    public void generateLavaArray(){
        rd = new Random();
        for (int i = 0; i < arrLava.length; i++) {
            arrLava[i] = rd.nextInt(252);
        }
        checkRepeatedLava(arrLava);
        sort(arrLava);
    }

    public void checkRepeatedLava(int[] arrLava){
        rd = new Random();
        for (int i = 0; i < arrLava.length; i++){
            for (int j = 0; j < arrLava.length; j++){
                if (i!=j && arrLava[i]==arrLava[j]){
                    arrLava[j] = rd.nextInt(252);
                    checkRepeatedLava(arrLava);
                }
            }
        }
    }

    public void generateReward(){
        rd = new Random();
        int posX, posY;
        posX = rd.nextInt(12);
        posY = rd.nextInt(21);
        for (int i = 0; i < arrLava.length; i++){
            if((w*posY)+posX == arrLava[i]){
                posX = rd.nextInt(12);
                posY = rd.nextInt(21);
            }
        }
        Rect rect = new Rect(arrFloor.get(posX).getX(), arrFloor.get(posY).getY(), arrFloor.get(posX).getX()+sizeOfMap, arrFloor.get(posY).getY()+sizeOfMap);
        reward = new Reward(arrFloor.get((w*posY)+posX).getX(), arrFloor.get((w*posY)+posX).getY(), bmReward);
    }

    public void generateFlamingo(){
        rd = new Random();
        int posX, posY;
        posX = rd.nextInt(12);
        posY = rd.nextInt(21);
        for (int i = 0; i < arrLava.length; i++){
            if((w*posY)+posX == arrLava[i]){
                posX = rd.nextInt(12);
                posY = rd.nextInt(21);
            }
        }
        flamingo = new Flamingo(bmFlamingo, arrFloor.get((w*posY)+posX).getX(), arrFloor.get((w*posY)+posX).getY());
    }

    public static void gameOverView(){
        GameActivity.GameOver(true, mContext);
    }
}
