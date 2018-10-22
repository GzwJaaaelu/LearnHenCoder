package com.jaaaelu.gzw.learn.henCoder.customizeVIew;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jaaaelu.gzw.learn.henCoder.R;
import com.jaaaelu.gzw.learn.henCoder.util.Utils;

public class AvatarView extends View {
    //  图片显式宽度
    private static final float WIDTH = Utils.dp2px(300);
    //
//    private static final float
    private Bitmap bitmap;
    //  ANTI_ALIAS_FLAG 用来防锯齿
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        bitmap = getAvatar((int) WIDTH, R.drawable.ic_god);
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, Utils.dp2px(50), Utils.dp2px(50), paint);
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
