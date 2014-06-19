package com.sec.android.calculator;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.sec.android.calculator.mathrules.OperationsLimit;
import com.sec.android.calculator.mathrules.ProhibitInputOperationAtStart;
import com.sec.android.calculator.mathrules.ReplaceSecondOperation;
import com.sec.android.calculator.mathrules.bracketsrules.*;
import com.sec.android.calculator.mathrules.pointrules.AddZeroBeforePoint;
import com.sec.android.calculator.mathrules.pointrules.ValidatePointInNumber;
import com.sec.android.calculator.mathrules.zerorules.ReplaceZeroAtStartOfNumber;
import com.sec.android.calculator.utils.MathRule;

import java.util.ArrayList;
import java.util.List;

public class TextWatcherImpl implements TextWatcher {
    private final EditText mEditText;

    public TextWatcherImpl(EditText editText) {
        mEditText = editText;
    }

    private List<MathRule> getListOfRules() {
        List<MathRule> listRules = new ArrayList<MathRule>();

        listRules.add(new OperationsLimit());
        listRules.add(new ReplaceSecondOperation());
        listRules.add(new ReplaceZeroAtStartOfNumber());
        listRules.add(new ProhibitInputOperationAtStart());
        //Brackets
        listRules.add(new InsertMultiplyBeforeOpenBracket());
        listRules.add(new CheckCountOfBrackets());
        listRules.add(new BracketsLimit());
        listRules.add(new AddCloseBracket());
        listRules.add(new InputAfterOpenBracket());
        listRules.add(new InputAfterCloseBracket());
        //Point
        listRules.add(new ValidatePointInNumber());
        listRules.add(new AddZeroBeforePoint());

        return listRules;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = s.toString();

        if (!text.isEmpty()) {
            for (MathRule rule : getListOfRules()) {
                rule.applyRule(s, getCursorPosition());
            }
        }
    }

    private int getCursorPosition() {
        return mEditText.getSelectionStart();
    }
}


