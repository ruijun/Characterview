package com.ruijun.android.characterview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CharacterView extends View {

    private static final int DEFAULT_TITLE_COLOR = Color.WHITE;
    private static final int DEFAULT_BACKGROUND_COLOR = Color.CYAN;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = Color.BLACK;
    private static final int DEFAULT_VIEW_SIZE = 96;
    private static final int DEFAULT_LETTER_COUNT= 1;
    private static final float DEFAULT_TITLE_SIZE = 25f;
    private static final String DEFAULT_TITLE = "A";
    private static final int LETTER_MAX_COUNT = 3;

    private int mTitleColor = DEFAULT_TITLE_COLOR;
    private int mBackgroundColor = DEFAULT_BACKGROUND_COLOR;
    private int mLetterCount = DEFAULT_LETTER_COUNT;
    private String mTitleText = DEFAULT_TITLE;
    private float mTitleSize = DEFAULT_TITLE_SIZE;

    private int mBorderColor = DEFAULT_BORDER_COLOR;
    private int mBorderWidth = DEFAULT_BORDER_WIDTH;

    // View type
    private int mType;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_RECT = 1;

    private TextPaint mTitleTextPaint;
    private Paint mBackgroundPaint;
    private Paint mBorderPaint;
    private RectF mInnerRectF;
    private RectF mBorderRect;
    private float mBorderRadius;
    private int mViewSize;

    private Typeface mFont = Typeface.defaultFromStyle(Typeface.NORMAL);

    public CharacterView(Context context) {
        super(context);
        init(null, 0);
    }

    public CharacterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CharacterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CharacterView, defStyle, 0);

        if(a.hasValue(R.styleable.CharacterView_titleText)){
            mTitleText = a.getString(R.styleable.CharacterView_titleText);
        }

        mTitleColor = a.getColor(R.styleable.CharacterView_titleColor, DEFAULT_TITLE_COLOR);
        mBackgroundColor = a.getColor(R.styleable.CharacterView_backgroundColorValue, DEFAULT_BACKGROUND_COLOR);

        mBorderWidth = a.getDimensionPixelSize(R.styleable.CharacterView_borderWidth, DEFAULT_BORDER_WIDTH);
        mBorderColor = a.getColor(R.styleable.CharacterView_borderColor, DEFAULT_BORDER_COLOR);

        mTitleSize = a.getDimension(R.styleable.CharacterView_titleSize, DEFAULT_TITLE_SIZE);
        mLetterCount = a.getInteger(R.styleable.CharacterView_letterCount, DEFAULT_LETTER_COUNT);

        mType = a.getInt(R.styleable.CharacterView_type, TYPE_CIRCLE);

        a.recycle();

        //Title TextPaint
        mTitleTextPaint = new TextPaint();
        mTitleTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTitleTextPaint.setTypeface(mFont);
        mTitleTextPaint.setTextAlign(Paint.Align.CENTER);
        mTitleTextPaint.setAntiAlias(true);
        mTitleTextPaint.setLinearText(true);
        mTitleTextPaint.setColor(mTitleColor);
        mTitleTextPaint.setTextSize(mTitleSize);

        //Background Paint
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(mBackgroundColor);

        //Border Paint
        mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);

        mInnerRectF = new RectF();
        mBorderRect = new RectF();
    }

    private void invalidateTextPaints(){
        mTitleTextPaint.setTypeface(mFont);
        mTitleTextPaint.setTextSize(mTitleSize);
    }

    private void invalidatePaints(){
        mBackgroundPaint.setColor(mBackgroundColor);
        mBorderPaint.setColor(mBorderColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = resolveSize(DEFAULT_VIEW_SIZE, widthMeasureSpec);
        int height = resolveSize(DEFAULT_VIEW_SIZE, heightMeasureSpec);
        mViewSize = Math.min(width, height);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mInnerRectF.set(0, 0, mViewSize, mViewSize);
        mInnerRectF.offset((getWidth() - mViewSize) / 2, (getHeight() - mViewSize) / 2);

        Log.d("Debug", "mViewSize " + mViewSize + " Width " + getWidth() + " Height " + getHeight());
        Log.d("Debug", "left " + mInnerRectF.left + " top " + mInnerRectF.top + " right " + mInnerRectF.right + " bottom " +  mInnerRectF.bottom);

        float centerX = mInnerRectF.centerX();
        float centerY = mInnerRectF.centerY();

        int xPos = (int) centerX;
        int yPos = (int) (centerY - (mTitleTextPaint.descent() + mTitleTextPaint.ascent()) / 2);

        Log.d("Debug", "centerX " + centerX+ " centerY " + centerY + " Height " + (mTitleTextPaint.descent() + mTitleTextPaint.ascent()) / 2);


        if (mType == TYPE_CIRCLE){
            canvas.drawOval(mInnerRectF, mBackgroundPaint);
            if (mBorderWidth != 0){
                mBorderRect.set(0, 0, mViewSize, mViewSize);
                mBorderRect.offset((getWidth() - mViewSize) / 2, (getHeight() - mViewSize) / 2);
                mBorderRadius = Math.min((mBorderRect.height() - mBorderWidth) / 2, (mBorderRect.width() - mBorderWidth) / 2);
                canvas.drawCircle(mViewSize / 2, mViewSize / 2, mBorderRadius, mBorderPaint);
            }
//            canvas.drawCircle(mViewSize / 2, mViewSize / 2, mViewSize / 2, mBackgroundPaint);
        } if (mType == TYPE_RECT) {
            canvas.drawRect(mInnerRectF, mBackgroundPaint);

            if (mBorderWidth != 0){
                mBorderRect.set(mBorderWidth, mBorderWidth, mViewSize, mViewSize);
                mBorderRect.offset((getWidth() - mViewSize - mBorderWidth) / 2, (getHeight() - mViewSize - mBorderWidth) / 2);
                canvas.drawRect(mBorderRect, mBorderPaint);
            }
        }

//        Shader shader = new LinearGradient(0, 0, 0, 100, Color.WHITE, Color.BLUE, Shader.TileMode.CLAMP);
//        mTitleTextPaint.setShader(shader);
        canvas.drawText(mTitleText, xPos, yPos, mTitleTextPaint);
    }

    /**
     * Gets the title string attribute value.
     * @return The title string attribute value.
     */
    public String getTitleText() {
        return mTitleText;
    }

    /**
     * Sets the view's title string attribute value.
     * @param title The example string attribute value to use.
     */
    public void setTitleText(String title) {
//        mTitleText = title;

        if (title.length() < mLetterCount){
            mTitleText = title.substring(0, title.length() - 1);
        } else if (mLetterCount > LETTER_MAX_COUNT){
            mTitleText = title.substring(0, LETTER_MAX_COUNT);
        } else {
            mTitleText = title.substring(0, mLetterCount);
        }

        Log.d("Debug", "mTitleText " + mTitleText + " " + mLetterCount);
        invalidate();
    }

    /**
     * Gets the background color attribute value.
     * @return The background color attribute value.
     */
    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     * Sets the view's background color attribute value.
     * @param backgroundColor The background color attribute value to use.
     */
    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
        invalidatePaints();
    }

    /**
     * Gets the title size dimension attribute value.
     * @return The title size dimension attribute value.
     */
    public float getTitleSize() {
        return mTitleSize;
    }

    /**
     * Sets the view's title size dimension attribute value.
     * @param titleSize The title size dimension attribute value to use.
     */
    public void setTitleSize(float titleSize) {
        mTitleSize = titleSize;
        invalidateTextPaints();
    }

    /**
     * Sets the view's title typeface.
     * @param font The typeface to be used for the text.
     */
    public void setTextTypeface(Typeface font){
        this.mFont = font;
        invalidateTextPaints();
    }

    /**
     * Sets the view's type.
     * @param type The typeface to be used for the text.
     */
    public void setViewType(int type){
        this.mType = type;
        invalidate();
    }

    /**
     * Sets the view's borderColor color attribute value.
     * @param borderColor The background color attribute value to use.
     */
    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
        invalidatePaints();
    }
}
