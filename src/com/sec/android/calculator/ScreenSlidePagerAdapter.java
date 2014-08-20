package com.sec.android.calculator;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class ScreenSlidePagerAdapter extends android.support.v13.app.FragmentStatePagerAdapter {
    private static final int PAGE_NUM = 2;

    public ScreenSlidePagerAdapter(android.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BasicKeyboardFragment.newInstance(position);
            case 1:
                return AdditionalKeyboardFragment.newInstance(position);
            default:
                return BasicKeyboardFragment.newInstance(position);
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }
}
