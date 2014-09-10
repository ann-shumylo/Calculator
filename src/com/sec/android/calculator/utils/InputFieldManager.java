package com.sec.android.calculator.utils;

import android.support.v4.view.ViewPager;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public interface InputFieldManager {
    void setInputtedSymbol(String str);

    void onEqualClicked();

    ViewPager getViewPager();
}
