package net.mobctrl.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by wangzhiyuan on 2015/9/18.
 */
public class CircleImageView extends ImageView {

    private Paint mPaint;

    private Xfermode mXfermode;

    private boolean onceLoad = false;

    private int mWidth;

    private int mHeight;

    private Bitmap mBitmap;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed && !onceLoad) {
            mPaint = new Paint();
            mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
            mPaint.setFilterBitmap(true);
            mPaint.setXfermode(mXfermode);
            mPaint.setAntiAlias(true);
            mWidth = getWidth();
            mHeight = getHeight();
            mBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(mBitmap);
            RectF oval = new RectF(0, 0, mWidth, mHeight);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            canvas.drawOval(oval, paint);
            onceLoad = true;
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {

        Drawable image = getDrawable();
        int level = canvas.saveLayer(0, 0, mWidth, mHeight, null, Canvas.ALL_SAVE_FLAG);
        image.setBounds(0, 0, mWidth, mHeight);
        image.draw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.restoreToCount(level);
        //super.onDraw(canvas);

    }


}

