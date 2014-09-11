package com.sec.android.calculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.sec.android.calculator.utils.ActionCodesLinks;
import com.sec.android.calculator.interfaces.InputFieldManager;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class BasicKeyboardFragment extends Fragment implements View.OnClickListener {

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static BasicKeyboardFragment newInstance(int position) {
        BasicKeyboardFragment fragment = new BasicKeyboardFragment();
        Bundle args = new Bundle();
        args.putInt("", position);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_keyboard, container, false);
        /*Numbers*/
        view.findViewById(R.id.btn_one).setOnClickListener(this);
        view.findViewById(R.id.btn_two).setOnClickListener(this);
        view.findViewById(R.id.btn_three).setOnClickListener(this);
        view.findViewById(R.id.btn_four).setOnClickListener(this);
        view.findViewById(R.id.btn_five).setOnClickListener(this);
        view.findViewById(R.id.btn_six).setOnClickListener(this);
        view.findViewById(R.id.btn_seven).setOnClickListener(this);
        view.findViewById(R.id.btn_eight).setOnClickListener(this);
        view.findViewById(R.id.btn_nine).setOnClickListener(this);
        view.findViewById(R.id.btn_zero).setOnClickListener(this);
        /*Basic operations*/
        view.findViewById(R.id.btn_addition).setOnClickListener(this);
        view.findViewById(R.id.btn_subtraction).setOnClickListener(this);
        view.findViewById(R.id.btn_division).setOnClickListener(this);
        view.findViewById(R.id.btn_multiplication).setOnClickListener(this);
        /*Point*/
        view.findViewById(R.id.btn_point).setOnClickListener(this);

        Button btnEqual = (Button) view.findViewById(R.id.btn_equal);
        btnEqual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getParentActivity().onEqualClicked();
            }
        });
        return view;
    }

    private InputFieldManager getParentActivity() {
        return (InputFieldManager) getActivity();
    }

    @Override
    public void onClick(View v) {
        if (ActionCodesLinks.BUTTON_ID_TO_BASIC_OPERATION_CODE_LINK.get(v.getId()) != null) {
            getParentActivity().setInputtedSymbol(ActionCodesLinks.BUTTON_ID_TO_BASIC_OPERATION_CODE_LINK.get(v.getId()));
        } else if (ActionCodesLinks.BUTTON_ID_TO_POINT_CODE_LINK.get(v.getId()) != null) {
            getParentActivity().setInputtedSymbol(ActionCodesLinks.BUTTON_ID_TO_POINT_CODE_LINK.get(v.getId()));
        } else {
            getParentActivity().setInputtedSymbol(String.valueOf(ActionCodesLinks.BUTTON_ID_TO_DIGIT_CODE_LINK.get(v.getId())));
        }
    }
}
