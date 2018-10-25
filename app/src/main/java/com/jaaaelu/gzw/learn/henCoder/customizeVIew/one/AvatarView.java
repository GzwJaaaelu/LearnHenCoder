package com.jaaaelu.gzw.learn.henCoder.customizeVIew.one;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.jaaaelu.gzw.learn.henCoder.R;
import com.jaaaelu.gzw.learn.henCoder.util.Utils;

public class AvatarView extends View {
    //  图片显式宽度
    private static final float WIDTH = Utils.dp2px(200);
    private static final float PADDING = (int) Utils.dp2px(20);
    private static final float EDGE_WIDTH = Utils.dp2px(5);

    private Bitmap mBitmap;
    //  ANTI_ALIAS_FLAG 用来防锯齿
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private RectF mBounds = new RectF();

    {
        mBitmap = getAvatar((int) WIDTH, R.drawable.ic_god);
    }

    public AvatarView(Context context) {
        super(context);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBounds.set(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //  为了画一个黑边
        canvas.drawOval(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH, mPaint);
        //  借用离屏缓冲来做剪裁，因为我们以为底层图像是 drawOval 的圆，其实是整个屏幕已经绘制好的区域
        //  绘制之前先把对应区域取下来
        int saved = canvas.saveLayer(mBounds, mPaint);
        //  底层图像
        //  顺便往里面挪一下
        canvas.drawOval(PADDING + EDGE_WIDTH, PADDING + EDGE_WIDTH,
                PADDING + WIDTH - EDGE_WIDTH, PADDING + WIDTH - EDGE_WIDTH, mPaint);
        //  设置图形叠加时候叠加效果
        //  SRC_IN 表示叠加时候，要露出上层图像与底层图像叠加时重合部分上层图像的内容
        mPaint.setXfermode(mXfermode);
        //  mBitmap 为上层图像，然后两者叠加，取重复部分就是圆形的部分，然后 SRC_IN 来保证显示的内容为上
        //  层的图像
        canvas.drawBitmap(mBitmap, PADDING, PADDING, mPaint);
        //  清理操作
        mPaint.setXfermode(null);
        canvas.restoreToCount(saved);
    }

    Bitmap getAvatar(int width, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resId, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), resId, options);
    }
}
