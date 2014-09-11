package com.sec.android.calculator;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.sec.android.calculator.interfaces.InputFieldManager;
import com.sec.android.calculator.interfaces.OnCalculationListener;
import com.sec.android.calculator.utils.ListValidationHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        editText = (EditText) findViewById(R.id.input_string);
        showResultTextView = (TextView) findViewById(R.id.show_result);

        editText.setInputType(InputType.TYPE_NULL);

        Button btnClear = (Button) findViewById(R.id.btn_delete);
        btnClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editText.getText().toString())) {
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
        editText.addTextChangedListener(new TextWatcherImpl(editText));
        showResultTextView.setMovementMethod(new ScrollingMovementMethod());
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

    @Override
    public void setInputtedSymbol(String str) {
        int cursorPosition = editText.getSelectionStart();
        editText.getText().insert(cursorPosition, str);
    }


    @Override
    public void onEqualClicked() {
        if (!TextUtils.isEmpty(editText.getText().toString()) && ListValidationHelper.isListValid(parseMathString())) {
            CalculateResults.reversePolishNotation(parseMathString(), new OnCalculationListener() {
                @Override
                public void onResult(BigDecimal result) {
                    setResult(result);
                }
                @Override
                public void onCalculationError(int errorType) {
                    String errorDescription = "Invalid operation.";
                    if (errorType == OnCalculationListener.ERROR_TYPE_DIVIDE_BY_ZERO) {
                        errorDescription += "\n" + "Divide by zero";
                    }
                    Toast.makeText(getBaseContext(), errorDescription, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getBaseContext(), "Invalid input string", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public ViewPager getViewPager() {
        return mPager;
    }

    private void setCursorToTheEnd() {
        editText.setSelection(editText.length());
    }

    private List<String> parseMathString() {
        List<String> myList = new ArrayList<>();
        Pattern numberPattern = Pattern.compile("(?:\\d*\\.)?\\d+|[sin]+|[cos]+|\\D");
        Matcher matcher = numberPattern.matcher(editText.getText().toString());

        while (matcher.find()) {
            myList.add(matcher.group());
        }

        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i).equals("-") && (i == 0 || myList.get(i - 1).equals("("))) {
                myList.remove(i);
                myList.set(i, "-" + myList.get(i));
            }
        }
        return myList;
    }


    private void setResult(BigDecimal result) {
        showResultTextView.setText("" + editText.getText().toString() + "=" + result);
        editText.setText("" + result);
        setCursorToTheEnd();
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
