package com.jaaaelu.gzw.learn.henCoder.customizeVIew.three;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.jaaaelu.gzw.learn.henCoder.R;
import com.jaaaelu.gzw.learn.henCoder.util.Utils;

public class NewCameraView extends View {
    private static final float PADDING = Utils.dp2px(100);
    private static final float IMAGE_WIDTH = Utils.dp2px(200);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Camera mCamera = new Camera();
    float topFlip = 0;
    float bottomFlip = 0;
    float flipRotation = 0;

    {
        mCamera.setLocation(0, 0, -4f * getResources().getDisplayMetrics().density);
    }

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    public NewCameraView(Context context) {
        super(context);
    }

    public NewCameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewCameraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NewCameraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        // 绘制上半部分
//        canvas.save();
//        canvas.translate(100 + 600 / 2, 100 + 600 / 2);
//        canvas.rotate(-20);
//        canvas.clipRect(-600, -600, 600, 0);
//        canvas.rotate(20);
//        canvas.translate(-(100 + 600 / 2), -(100 + 600 / 2));
//        canvas.drawBitmap(mAvatar, 100, 100, mPaint);
//        canvas.restore();
//
//        // 绘制下半部分
//        canvas.save();
//        canvas.translate(100 + 600 / 2, 100 + 600 / 2);
//        canvas.rotate(-20);
//        mCamera.applyToCanvas(canvas);
//        canvas.clipRect(-600, 0, 600, 600);
//        canvas.rotate(20);
//        canvas.translate(-(100 + 600 / 2), -(100 + 600 / 2));
//        canvas.drawBitmap(mAvatar, 100, 100, mPaint);
//        canvas.restore();

        // 绘制上半部分
        canvas.save();
        canvas.translate(PADDING + IMAGE_WIDTH / 2, PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-flipRotation);
        mCamera.save();
        mCamera.rotateX(topFlip);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.clipRect(- IMAGE_WIDTH, - IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(flipRotation);
        canvas.translate(- (PADDING + IMAGE_WIDTH / 2), - (PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(getAvatar((int) IMAGE_WIDTH), PADDING, PADDING, mPaint);
        canvas.restore();

        // 绘制下半部分
        canvas.save();
        canvas.translate(PADDING + IMAGE_WIDTH / 2, PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-flipRotation);
        mCamera.save();
        mCamera.rotateX(bottomFlip);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.clipRect(- IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(flipRotation);
        canvas.translate(- (PADDING + IMAGE_WIDTH / 2), - (PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(getAvatar((int) IMAGE_WIDTH), PADDING, PADDING, mPaint);
        canvas.restore();

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
