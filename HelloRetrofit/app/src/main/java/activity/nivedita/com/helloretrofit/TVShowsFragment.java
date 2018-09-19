package activity.nivedita.com.helloretrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activity.nivedita.com.base.BaseFragment;

/**
 * New Fragment to be attached when attaching the second tab
 */

public class TVShowsFragment extends BaseFragment{

    public static TVShowsFragment newInstance() {

        Bundle args = new Bundle();
        TVShowsFragment tvShowsFragment = new TVShowsFragment();
        tvShowsFragment.setArguments(args);
        return tvShowsFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.tvshows_fragment, container, false);
    }


}
