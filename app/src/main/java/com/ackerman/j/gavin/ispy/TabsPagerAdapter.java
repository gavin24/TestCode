package com.ackerman.j.gavin.ispy;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ackerman.j.gavin.ispy.Fragments.CreateStoryFragment;
import com.ackerman.j.gavin.ispy.Fragments.NewsfeedFragment;
import com.ackerman.j.gavin.ispy.Fragments.ProfileFragment;
import com.ackerman.j.gavin.ispy.Fragments.SettingFragment;

/**
 * Created by gavin.ackerman on 2017-04-20.
 */
public class TabsPagerAdapter  extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:

                return new NewsfeedFragment();
            case 1:

                return new CreateStoryFragment();
            case 2:
                return new ProfileFragment();
            case 3:
                return  new SettingFragment();


        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 4;
    }

}
