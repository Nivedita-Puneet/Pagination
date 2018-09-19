package activity.nivedita.com.helloretrofit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Define the pager adapter required in order to swipe the view pager.
 */

public class MoviePagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount = 0;

    public MoviePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MoviesFragment.newInstance();
            case 1:
                return TVShowsFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count){
        mTabCount = count;
    }
}
