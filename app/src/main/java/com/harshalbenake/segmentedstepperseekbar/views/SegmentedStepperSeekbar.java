package com.harshalbenake.segmentedstepperseekbar.views;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.harshalbenake.segmentedstepperseekbar.R;

/**
 * This class is used for Segmented Stepper Seekbar.
 */
public class SegmentedStepperSeekbar extends LinearLayout implements SeekBar.OnSeekBarChangeListener {
    private Context mContext;
    private SeekBar mSeekBar;
    private LinearLayout mll_containertick, mll_containerticklables;
    private ImageView mImageView;
    private String[] mTagArray;
    private int mProgress;

    public interface OnSelectedListner {
        void onSelected(String selectedText);
    }

    OnSelectedListner mOnSelectedListner;

    public SegmentedStepperSeekbar(Context context) {
        super(context);
        this.mContext = context;
        initlayout();
    }

    public SegmentedStepperSeekbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initlayout();
    }

    public SegmentedStepperSeekbar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initlayout();
    }

    /**
     * initialize Layout
     */
    private void initlayout() {
        View rootView = inflate(mContext, R.layout.segmented_stepper_seekbar, this);
        mSeekBar = (SeekBar) rootView.findViewById(R.id.segmented_stepper_seekbar);
        mll_containertick = (LinearLayout) rootView.findViewById(R.id.ll_containertick);
        mll_containerticklables = (LinearLayout) rootView.findViewById(R.id.ll_containerticklables);

        mSeekBar.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.MULTIPLY));

        mSeekBar.setOnSeekBarChangeListener(this);
    }

    /**
     * sets Up Radio Option Menu
     *
     * @param lableArray
     */
    public void setUpRadioOptionMenu(String[] lableArray, String[] tagArray) {
        mTagArray = tagArray;
        for (int radioButtonIndex = 0; radioButtonIndex < lableArray.length; radioButtonIndex++) {
            mImageView = new ImageView(mContext);
            mImageView.setTag(tagArray[radioButtonIndex]);
            mImageView.setAlpha(0.7f);
            mImageView.setImageResource(R.drawable.tick_unselected);
            LinearLayout.LayoutParams layoutParamsImageView = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 100 / lableArray.length);
            if (radioButtonIndex == 0) {
                mImageView.setScaleType(ImageView.ScaleType.FIT_START);
            } else if (radioButtonIndex == lableArray.length - 1) {
                mImageView.setScaleType(ImageView.ScaleType.FIT_END);
            } else {
                mImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
            mImageView.setLayoutParams(layoutParamsImageView);
            setLables(lableArray, tagArray, radioButtonIndex);
            mll_containertick.addView(mImageView);
        }
    }

    /**
     * sets Lables
     *
     * @param lableArray
     * @param tagArray
     * @param radioButtonIndex
     */
    private void setLables(String[] lableArray, String[] tagArray, int radioButtonIndex) {
        TextView textView = new TextView(mContext);
        LinearLayout.LayoutParams layoutParamsTextView = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 100 / lableArray.length);
        textView.setLayoutParams(layoutParamsTextView);
        textView.setText(lableArray[radioButtonIndex]);
        textView.setTag(radioButtonIndex+"");
        if (radioButtonIndex == 0) {
            textView.setGravity(Gravity.LEFT);
        } else if (radioButtonIndex == lableArray.length - 1) {
            textView.setGravity(Gravity.RIGHT);
        } else {
            textView.setGravity(Gravity.CENTER);
        }
        mll_containerticklables.addView(textView);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        setProgress(seekBar.getProgress());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mProgress = seekBar.getProgress();
        if (mProgress > 0 & mProgress < 26) {
            seekBar.setProgress(0);
        } else if (mTagArray.length > 2 && mProgress > 25 & mProgress < 76) {
            seekBar.setProgress(50);
        } else {
            seekBar.setProgress(100);
        }
    }

    /**
     * sets Progress
     */
    public void setProgress(int progress) {
        mProgress=progress;
    }

    /**
     * gets Progress
     */
    public int getProgress() {
        return mProgress;
    }

    /**
     * sets Background
     */
    public void setBackground(String backgroundColor) {
        mSeekBar.getBackground().setColorFilter(Color.parseColor(backgroundColor), PorterDuff.Mode.SRC_ATOP);
    }

    /**
     * sets Background Image
     */
    public void setBackgroundImage(int backgroundImage) {
        mSeekBar.setBackgroundResource(backgroundImage);
    }

    /**
     * sets On Selected Listner
     *
     * @param onSelectedListner
     */
    public void setOnSelectedListner(OnSelectedListner onSelectedListner) {
        mOnSelectedListner = onSelectedListner;
    }
}