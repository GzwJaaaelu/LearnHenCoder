package com.jaaaelu.gzw.learn.henCoder.customizeVIew.two;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jaaaelu.gzw.learn.henCoder.R;
import com.jaaaelu.gzw.learn.henCoder.util.Utils;

/**
 * Created by Gzw on 2018/10/25 0025.
 */

public class ImageTextView extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(100);
    private static final float IMAGE_OFFSET = Utils.dp2px(80);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private String mContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean justo sem, sollicitudin in maximus a, vulputate id magna. Nulla non quam a massa sollicitudin commodo fermentum et est. Suspendisse potenti. Praesent dolor dui, dignissim quis tellus tincidunt, porttitor vulputate nisl. Aenean tempus lobortis finibus. Quisque nec nisl laoreet, placerat metus sit amet, consectetur est. Donec nec quam tortor. Aenean aliquet dui in enim venenatis, sed luctus ipsum maximus. Nam feugiat nisi rhoncus lacus facilisis pellentesque nec vitae lorem. Donec et risus eu ligula dapibus lobortis vel vulputate turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In porttitor, risus aliquam rutrum finibus, ex mi ultricies arcu, quis ornare lectus tortor nec metus. Donec ultricies metus at magna cursus congue. Nam eu sem eget enim pretium venenatis. Duis nibh ligula, lacinia ac nisi vestibulum, vulputate lacinia tortor.";
    private Bitmap mAvatar;
    float[] mCutWidth = new float[1];

    {
        mAvatar = getAvatar((int) IMAGE_WIDTH);
        mTextPaint.setTextSize(Utils.dp2px(15));
        mPaint.setTextSize(Utils.dp2px(13));
    }

    public ImageTextView(Context context) {
        super(context);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        //  会自动换行的文字显示布局
//        @SuppressLint("DrawAllocation")
//        StaticLayout staticLayout = new StaticLayout(mContent, mTextPaint, getWidth(),
//                Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
//        staticLayout.draw(canvas);

        canvas.drawBitmap(mAvatar, getWidth() - IMAGE_WIDTH, IMAGE_OFFSET, mPaint);

        //  对于换行有自己的特殊需求，就需要自己调用 drawText 了，并且配合 breakText
//        int oldIndex = 0;
//        int index = mPaint.breakText(mContent, true, getWidth(), mCutWidth);
//        canvas.drawText(mContent, oldIndex, oldIndex + index, 0, 25, mPaint);
//        index = mPaint.breakText(mContent, true, getWidth(), mCutWidth);
//        canvas.drawText(mContent, oldIndex, oldIndex + index, 0, 25 + mPaint.getFontSpacing(), mPaint);

        int index = 0;
        int space = 0;
        for (int start = 0; start < mContent.length(); ) {
            float currHeight = 25 + mPaint.getFontSpacing() * space;
            float currWidth = getWidth();
            if (currHeight > IMAGE_OFFSET && currHeight < (IMAGE_WIDTH + IMAGE_OFFSET)) {
                currWidth -= IMAGE_WIDTH;
            }
            index = mPaint.breakText(mContent, start, mContent.length(), true, currWidth, mCutWidth);
            canvas.drawText(mContent, start, start + index, 0, currHeight, mPaint);
            start += index;
            space++;
        }
    }

    Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.avatar_rengwuxian, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.avatar_rengwuxian, options);
    }
}
