package com.sec.android.calculator;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.sec.android.calculator.utils.ActionCodesLinks;
import com.sec.android.calculator.utils.ListValidationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CalculatorView implements View.OnClickListener {
    private final View mParentView;
    private final Context mContext;
    private EditText editText;
    private TextView showResult;

    public CalculatorView(Context context, View parentView) {
        mContext = context;
        mParentView = parentView;

        initUIComponents();

        editText.addTextChangedListener(new TextWatcherImpl(editText));
        showResult.setMovementMethod(new ScrollingMovementMethod());

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
                editText.clearFocus();
            }
        });

        editText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideSoftKeyboard();
            }
        });
    }

    void setInputtedSymbol(String str) {
        int cursorPosition = editText.getSelectionStart();
        editText.getText().insert(cursorPosition, str);
    }

    void setEmptyView() {
        showResult.setText(R.string.no_results);
        editText.setText("");
    }

    void setResult() {
        showResult.setText("" + getDisplayedString() + "=" +
                formatStringResult(CalculateResults.reversePolishNotation(getListOfNumbersAndSignsFromString())));
        editText.setText(formatStringResult(CalculateResults.reversePolishNotation(getListOfNumbersAndSignsFromString())));
        setCursorToTheEnd();

    }

    private void showToastPopup(Context context) {
        int duration = Toast.LENGTH_SHORT;
        String text = "Invalid input string";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    void setBackspace() {
        int cursorPosition = editText.getSelectionStart();
        if (cursorPosition != 0) {
            editText.getText().delete(cursorPosition - 1, cursorPosition);
        }
    }

    boolean isEditTextEmpty() {
        return TextUtils.isEmpty(editText.getText().toString());
    }

    private String getDisplayedString() {
        return editText.getText().toString();
    }

    private void setCursorToTheEnd() {
        editText.setSelection(editText.length());
    }

    private String formatStringResult(double doubleNum) {
        if (doubleNum % 1 == 0) {
            return String.format("%d", (int) doubleNum);
        } else {
            return String.format("%s", doubleNum);
        }
    }

    private List<String> getListOfNumbersAndSignsFromString() {
        List<String> myList = new ArrayList<String>();
        String tempStr = getDisplayedString().replace("sin", "s");
        StringTokenizer check = new StringTokenizer(tempStr.replace("cos", "c"), "+/-*)(^%sc", true);
        while (check.hasMoreTokens()) {
            myList.add(check.nextToken());
        }
        return myList;
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private void initUIComponents() {
        editText = (EditText) mParentView.findViewById(R.id.input_string);
        showResult = (TextView) mParentView.findViewById(R.id.show_result);
        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
            }
        });
        editText.setLongClickable(false);
        /*Numbers*/
        mParentView.findViewById(R.id.btn_one).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_two).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_three).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_four).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_five).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_six).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_seven).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_eight).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_nine).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_zero).setOnClickListener(this);
        /*Operations*/
        mParentView.findViewById(R.id.btn_addition).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_subtraction).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_division).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_multiplication).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_power).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_sin).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_cos).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_percent).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_open_bracket).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_close_bracket).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_point).setOnClickListener(this);
        /*Equal, Clear*/
        Button btnClear = (Button) mParentView.findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new OnClickListener() {
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
        Button btnEqual = (Button) mParentView.findViewById(R.id.btn_equal);
        btnEqual.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (!isEditTextEmpty() && ListValidationHelper.isListValid(getListOfNumbersAndSignsFromString())) {
                    setResult();
                } else {
                    showToastPopup(mContext);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (ActionCodesLinks.BUTTON_ID_TO_OPERATION_CODE_LINK.get(v.getId()) != null) {
            setInputtedSymbol(ActionCodesLinks.BUTTON_ID_TO_OPERATION_CODE_LINK.get(v.getId()));
        } else if (ActionCodesLinks.BUTTON_ID_TO_BRACKET_CODE_LINK.get(v.getId()) != null) {
            setInputtedSymbol(ActionCodesLinks.BUTTON_ID_TO_BRACKET_CODE_LINK.get(v.getId()));
        } else if (ActionCodesLinks.BUTTON_ID_TO_POINT_CODE_LINK.get(v.getId()) != null) {
            setInputtedSymbol(ActionCodesLinks.BUTTON_ID_TO_POINT_CODE_LINK.get(v.getId()));
        } else if (ActionCodesLinks.BUTTON_ID_TO_LITERAL_OPERATION_CODE_LINK.get(v.getId()) != null) {
            setInputtedSymbol(ActionCodesLinks.BUTTON_ID_TO_LITERAL_OPERATION_CODE_LINK.get(v.getId()));
        } else {
            setInputtedSymbol(String.valueOf(ActionCodesLinks.BUTTON_ID_TO_DIGIT_CODE_LINK.get(v.getId())));
        }
    }
}
