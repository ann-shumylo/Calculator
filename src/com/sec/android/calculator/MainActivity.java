package com.sec.android.calculator;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.sec.android.calculator.utils.InputFieldManager;
import com.sec.android.calculator.utils.ListValidationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class MainActivity extends FragmentActivity implements InputFieldManager {
    private EditText editText;
    private TextView showResultTextView;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        editText = (EditText) findViewById(R.id.input_string);
        showResultTextView = (TextView) findViewById(R.id.show_result);

        Button btnClear = (Button) findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isEditTextEmpty()) {
                    setBackspace();
                }
            }
        });
        btnClear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setEmptyView();
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private String formatStringResult(double doubleNum) {
        if (doubleNum % 1 == 0) {
            return String.format("%d", (int) doubleNum);
        } else {
            return String.format("%s", doubleNum);
        }
    }

    private void setCursorToTheEnd(EditText editText) {
        editText.setSelection(editText.length());
    }

    @Override
    public void setInputtedSymbol(String str) {
        int cursorPosition = editText.getSelectionStart();
        editText.getText().insert(cursorPosition, str);
    }

    @Override
    public void onEqualClicked() {
        if (!isEditTextEmpty() &&
                ListValidationHelper.isListValid(getListOfNumbersAndSignsFromString())) {
            setResult();
        } else {
            String text = "Invalid input string";
            Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isEditTextEmpty() {
        return TextUtils.isEmpty(editText.getText().toString());
    }

    public List<String> getListOfNumbersAndSignsFromString() {
        List<String> myList = new ArrayList<>();
        String tempStr = editText.getText().toString().replace("sin", "s");
        StringTokenizer check = new StringTokenizer(tempStr.replace("cos", "c"), "+/-*)(^%sc", true);
        while (check.hasMoreTokens()) {
            myList.add(check.nextToken());
        }
        return myList;
    }

    public void setResult() {
        showResultTextView.setText("" + editText.getText().toString() + "=" +
                formatStringResult(CalculateResults.reversePolishNotation(getListOfNumbersAndSignsFromString())));
        editText.setText(formatStringResult(CalculateResults.reversePolishNotation(getListOfNumbersAndSignsFromString())));
        setCursorToTheEnd(editText);
    }

    private void setBackspace() {
        int cursorPosition = editText.getSelectionStart();
        if (cursorPosition != 0) {
            editText.getText().delete(cursorPosition - 1, cursorPosition);
        }
    }

    private void setEmptyView() {
        showResultTextView.setText(R.string.no_results);
        editText.setText("");
    }
}
