package com.sunzn.mark.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MarkTextView extends AppCompatTextView {

    public static final int STA = 0;
    public static final int END = 1;

    public MarkTextView(Context context) {
        super(context);
    }

    public MarkTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarkTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setStaText(CharSequence text, CharSequence label, int res) {
        setText(text, STA, label, res);
    }

    public void setEndText(CharSequence text, CharSequence label, int res) {
        setText(text, END, label, res);
    }

    private void setText(CharSequence text, int gravity, CharSequence label, int res) {
        if (label == null || label.length() == 0) {
            setText(text);
            return;
        }
        SpannableStringBuilder builder = new SpannableStringBuilder();
        switch (gravity) {
            case STA:
                SpannableString s = new SpannableString(label);
                s.setSpan(getImageSpan(label, res), 0, label.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.append(s).append(text);
                setText(builder);
                break;
            case END:
                SpannableString e = new SpannableString(label);
                e.setSpan(getImageSpan(label, res), 0, label.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.append(text).append(e);
                setText(builder);
                break;
            default:
                throw new IllegalArgumentException("gravity must be MarkTextView.STA or MarkTextView.END");
        }
    }

    public MarkImageSpan getImageSpan(CharSequence text, Integer res) {
        View view = getView(text, res);
        Bitmap bitmap = getBitmap(view);
        return new MarkImageSpan(getContext(), bitmap);
    }

    public View getView(CharSequence text, Integer res) {
        View view = LayoutInflater.from(getContext()).inflate(res, null);
        TextView tv = view.findViewById(R.id.mark);
        tv.setText(text);
        return view;
    }

    public static Bitmap getBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

}
