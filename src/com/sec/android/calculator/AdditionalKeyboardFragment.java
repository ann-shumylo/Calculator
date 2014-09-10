package com.sec.android.calculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sec.android.calculator.utils.ActionCodesLinks;
import com.sec.android.calculator.utils.InputFieldManager;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class AdditionalKeyboardFragment extends Fragment implements View.OnClickListener {

    public AdditionalKeyboardFragment() {
    }

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static AdditionalKeyboardFragment newInstance(int position) {
        AdditionalKeyboardFragment fragment = new AdditionalKeyboardFragment();
        Bundle args = new Bundle();
        args.putInt("", position);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additional_keyboard, container, false);

        view.findViewById(R.id.btn_power).setOnClickListener(this);
        view.findViewById(R.id.btn_sin).setOnClickListener(this);
        view.findViewById(R.id.btn_cos).setOnClickListener(this);
        view.findViewById(R.id.btn_percent).setOnClickListener(this);
        view.findViewById(R.id.btn_open_parentheses).setOnClickListener(this);
        view.findViewById(R.id.btn_close_parentheses).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (ActionCodesLinks.BUTTON_ID_TO_ADDITIONAL_OPERATION_CODE_LINK.get(v.getId()) != null) {
            getParentActivity().setInputtedSymbol(ActionCodesLinks.BUTTON_ID_TO_ADDITIONAL_OPERATION_CODE_LINK.get(v.getId()));
        } else if (ActionCodesLinks.BUTTON_ID_TO_BRACKET_CODE_LINK.get(v.getId()) != null) {
            getParentActivity().setInputtedSymbol(ActionCodesLinks.BUTTON_ID_TO_BRACKET_CODE_LINK.get(v.getId()));
        } else if (ActionCodesLinks.BUTTON_ID_TO_LITERAL_OPERATION_CODE_LINK.get(v.getId()) != null) {
            getParentActivity().setInputtedSymbol(ActionCodesLinks.BUTTON_ID_TO_LITERAL_OPERATION_CODE_LINK.get(v.getId()));
        }
        getParentActivity().getViewPager().setCurrentItem(0, true);
    }

    private InputFieldManager getParentActivity() {
        return (InputFieldManager) getActivity();
    }

}
