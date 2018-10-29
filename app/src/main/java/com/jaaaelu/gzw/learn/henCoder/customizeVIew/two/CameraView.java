package com.jaaaelu.gzw.learn.henCoder.customizeVIew.two;

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

public class CameraView extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(160);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Camera mCamera = new Camera();
    private Bitmap mAvatar;

    {
        mAvatar = getAvatar((int) IMAGE_WIDTH);

        mCamera.rotateX(45);
//        //  mCamera 默认 Location (0, 0, -8)
//        //  8 的单位是英寸，一英寸 = 72 像素，也就是 -8 = -8 * 72
//        //  对 Camera Location 做适配
        mCamera.setLocation(0, 0, -2f * getResources().getDisplayMetrics().density);
    }

    public CameraView(Context context) {
        super(context);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CameraView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        //  一半斜着一半正常
//        //  下面翻折的
//        canvas.save();
//        canvas.translate(IMAGE_WIDTH / 2 + 200, IMAGE_WIDTH / 2 + 0);
//        mCamera.applyToCanvas(canvas);
//        canvas.translate(-(IMAGE_WIDTH / 2 + 200), -(IMAGE_WIDTH / 2 + 0));
//        canvas.clipRect(200, IMAGE_WIDTH /2, 200 + IMAGE_WIDTH, IMAGE_WIDTH);
//        canvas.drawBitmap(mAvatar, 200, 0, mPaint);
//        canvas.restore();
//
//        //  上面不变的
//        canvas.save();
//        canvas.clipRect(200, 0, 200 + IMAGE_WIDTH, IMAGE_WIDTH / 2);
//        canvas.drawBitmap(mAvatar, 200, 0, mPaint);
//        canvas.restore();


        //  上面不变的
        canvas.save();
        canvas.translate(IMAGE_WIDTH / 2 + 200, IMAGE_WIDTH / 2 + 0);
        canvas.rotate(-20);
        canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(20);
        canvas.translate(-(IMAGE_WIDTH / 2 + 200), -(IMAGE_WIDTH / 2 + 0));
        canvas.drawBitmap(mAvatar, 200, 0, mPaint);
        canvas.restore();

        //  下面翻折的
        canvas.save();
        canvas.translate(IMAGE_WIDTH / 2 + 200, IMAGE_WIDTH / 2 + 0);
        canvas.rotate(-20);
        mCamera.applyToCanvas(canvas);
        canvas.clipRect(-IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(20);
        canvas.translate(-(IMAGE_WIDTH / 2 + 200), -(IMAGE_WIDTH / 2 + 0));
        canvas.drawBitmap(mAvatar, 200, 0, mPaint);
        canvas.restore();


//        // 绘制上半部分
//        canvas.save();
//        canvas.translate(100 + 600 / 2, 100 + 600 / 2);
//        canvas.rotate(-20);
//        canvas.clipRect(- 600, - 600, 600, 0);
//        canvas.rotate(20);
//        canvas.translate(- (100 + 600 / 2), - (100 + 600 / 2));
//        canvas.drawBitmap(mAvatar, 100, 100, mPaint);
//        canvas.restore();
//
//        // 绘制下半部分
//        canvas.save();
//        canvas.translate(100 + 600 / 2, 100 + 600 / 2);
//        canvas.rotate(-20);
//        mCamera.applyToCanvas(canvas);
//        canvas.clipRect(- 600, 0, 600, 600);
//        canvas.rotate(20);
//        canvas.translate(- (100 + 600 / 2), - (100 + 600 / 2));
//        canvas.drawBitmap(mAvatar, 100, 100, mPaint);
//        canvas.restore();
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
