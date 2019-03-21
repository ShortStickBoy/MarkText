package com.sunzn.mark.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
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

    public void setStaText(String text, String label, int res) {
        setText(text, STA, label, res);
    }

    public void setEndText(String text, String label, int res) {
        setText(text, END, label, res);
    }

    private void setText(String text, int gravity, String label, int res) {
        if (label == null || label.length() == 0) {
            setText(text);
            return;
        }
        StringBuilder builder = new StringBuilder();
        switch (gravity) {
            case STA:
                builder.append(label).append(text);
                SpannableString s = new SpannableString(builder);
                s.setSpan(getImageSpan(label, res), 0, label.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                setText(s);
                break;
            case END:
                builder.append(text).append(label);
                SpannableString e = new SpannableString(builder);
                e.setSpan(getImageSpan(label, res), text.length(), builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                setText(e);
                break;
            default:
                throw new IllegalArgumentException("gravity must be MarkTextView.STA or MarkTextView.END");
        }
    }

    public ImageSpan getImageSpan(String text, Integer res) {
        View view = getView(text, res);
        Bitmap bitmap = getBitmap(view);
        return new ImageSpan(getContext(), bitmap);
    }

    public View getView(String text, Integer res) {
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
