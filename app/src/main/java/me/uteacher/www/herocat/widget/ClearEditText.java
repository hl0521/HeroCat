package me.uteacher.www.herocat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import me.uteacher.www.herocat.R;

/**
 * Created by HL0521 on 2016/1/19.
 */
public class ClearEditText extends EditText implements View.OnFocusChangeListener {

    private Drawable mClearDrawable;

    private boolean hasFocus;

    private boolean mEmpty;

    private ClearEditText.onClearEditTextChanged mOnClearEditTextChangedListener;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ico_delete);
        }

        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        //默认设置隐藏图标
        setClearIconVisible(false);
        // 设置焦点改变的监听
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听
        /**
         * TextView 自带了一个 onTextChanged （TextView有一个内部类，实现了TextWatcher，因此此处只需要
         * 覆写 TextView 中的 onTextChanged 即可）
         */
//        addTextChangedListener(this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {

                // getWidth():得到控件的宽度
                // getTotalPaddingRight():clear的图标左边缘至控件右边缘的距离
                // getPaddingRight():clear的图标右边缘至控件右边缘的距离
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (text.length() > 0) {
            setClearIconVisible(true);
            mEmpty = false;
        } else {
            setClearIconVisible(false);
            mEmpty = true;
        }

        if (mOnClearEditTextChangedListener != null) {
            mOnClearEditTextChangedListener.onClearEditTextChanged(text, start, lengthBefore, lengthAfter);
        }
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], null, right, null);
    }

    public boolean isEmpty() {
        return mEmpty;
    }

    public void setOnClearEditTextChangedListener(ClearEditText.onClearEditTextChanged listener) {
        if (mOnClearEditTextChangedListener == null) {
            mOnClearEditTextChangedListener = listener;
        }
    }

    public interface onClearEditTextChanged {

        void onClearEditTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter);

    }
}
