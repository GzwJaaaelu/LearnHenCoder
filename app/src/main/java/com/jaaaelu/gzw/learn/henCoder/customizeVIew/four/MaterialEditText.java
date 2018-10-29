package com.jaaaelu.gzw.learn.henCoder.customizeVIew.four;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.jaaaelu.gzw.learn.henCoder.R;
import com.jaaaelu.gzw.learn.henCoder.util.Utils;

public class MaterialEditText extends android.support.v7.widget.AppCompatEditText {
    private static final float TEXT_SIZE = Utils.dp2px(12);
    private static final float TEXT_MARGIN = Utils.dp2px(8);

    private static final int TEXT_VERTICAL_OFFSET = (int) Utils.dp2px(22);
    private static final int TEXT_HORIZONTAL_OFFSET = (int) Utils.dp2px(5);
    private static final int TEXT_ANIMATION_OFFSET = (int) Utils.dp2px(16);

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean useFloatingLabel;
    //  标签是否已经显示过了
    private boolean floatingLabelShown;
    //  标签显示进度
    private float floatingLabelFraction;
    private ObjectAnimator animator;
    private Rect mBounds = new Rect();

    public MaterialEditText(Context context) {
        super(context);

        init();
    }

    public MaterialEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, false);
        typedArray.recycle();

        init();
    }

    public MaterialEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        onUseFloatingLabelChanged();
        mPaint.setTextSize(TEXT_SIZE);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (useFloatingLabel) {
                    if (floatingLabelShown && TextUtils.isEmpty(s)) {
                        //  刚才已经显示过了，但是现在是空的
                        getAnimator().reverse();
                        floatingLabelShown = false;
                    } else if (!floatingLabelShown && !TextUtils.isEmpty(s)) {
                        //  刚才没显示，但是现在内容不是空了
                        getAnimator().start();
                        floatingLabelShown = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private ObjectAnimator getAnimator() {
        if (animator == null) {
            animator = ObjectAnimator.ofFloat(MaterialEditText.this, "floatingLabelFraction", 0, 1);
        }
        return animator;
    }


    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }

    public boolean isUseFloatingLabel() {
        return useFloatingLabel;
    }

    public void setUseFloatingLabel(boolean useFloatingLabel) {
        if (this.useFloatingLabel != useFloatingLabel) {
            this.useFloatingLabel = useFloatingLabel;
            onUseFloatingLabelChanged();
        }
    }

    private void onUseFloatingLabelChanged() {
        getBackground().getPadding(mBounds);
        if (useFloatingLabel) {
            setPadding(mBounds.left, (int) (mBounds.top + TEXT_SIZE + TEXT_MARGIN), mBounds.right, mBounds.bottom);
            invalidate();
        } else {
            setPadding(mBounds.left, mBounds.top, mBounds.right, mBounds.bottom);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (useFloatingLabel) {
            //  做透明度变化和位置偏移变化
            mPaint.setAlpha((int) (0xff * floatingLabelFraction));
//            int extraOffset = (int) (TEXT_ANIMATION_OFFSET * (1 - floatingLabelFraction));
            canvas.drawText(getHint().toString(), TEXT_HORIZONTAL_OFFSET, TEXT_VERTICAL_OFFSET + 0, mPaint);
        }
    }
}
