package com.example.CrossTheRoad;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
//help from: https://stackoverflow.com/questions/2381560/how-to-group-a-3x3-grid-of-radio-buttons
public class RadioGroupGridLayout extends TableLayout implements View.OnClickListener {
    public static final String TAG = "RadioGroupGridLayout";
    private RadioButton activeRadioButton;

    public RadioGroupGridLayout(Context context) {
        super(context);
    }

    public RadioGroupGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onClick(View view) {
        final RadioButton curr = (RadioButton) view;
        if (activeRadioButton != null) {
            activeRadioButton.setChecked(false);
        }
        curr.setChecked(true);
        activeRadioButton = curr;
    }

    @Override
    public void addView(View child, int index,
                        android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        addChildrenToClickListener((TableRow) child);
    }

    private void addChildrenToClickListener(TableRow tr) {
        final int count = tr.getChildCount();
        for (int i = 0; i < count; i++) {
            final View v = tr.getChildAt(i);
            if (v instanceof RadioButton) {
                v.setOnClickListener(this);
            }
        }
    }

    public int getCheckedRadioButtonId() {
        int id = -1;
        if (activeRadioButton != null) {
            if (activeRadioButton.getText().equals("Jester")) {
                id = 0;
            } else if (activeRadioButton.getText().equals("Knight")) {
                id = 1;
            } else if (activeRadioButton.getText().equals("King")) {
                id = 2;
            } else if (activeRadioButton.getText().equals("Princess")) {
                id = 3;
            } else if (activeRadioButton.getText().equals("Wizard")) {
                id = 4;
            } else if (activeRadioButton.getText().equals("Executioner")) {
                id = 5;
            }
            return id;
        }
        return -1;
    }
}
