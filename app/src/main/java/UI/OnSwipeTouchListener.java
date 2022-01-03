package UI;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import API.Views.SwipeGestureListener;

/**
 * @author Mirek Rusin - https://stackoverflow.com/a/12938787
 */
public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector gestureDetector;
    private SwipeGestureListener listener;

    public OnSwipeTouchListener (Context ctx, SwipeGestureListener listener){
        gestureDetector = new GestureDetector(ctx, new GestureListener());
        this.listener = listener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public void onSwipeRight() {
        listener.onSwipeRight();
        Log.e("Gesture", "Swipe right");
    }

    public void onSwipeLeft() {
        listener.onSwipeLeft();
        Log.e("Gesture", "Swipe left");
    }

    public void onSwipeTop() {
        listener.onSwipeTop();
        Log.e("Gesture", "Swipe top");
    }

    public void onSwipeBottom() {
        listener.onSwipeBottom();
        Log.e("Gesture", "Swipe down");
    }
}
