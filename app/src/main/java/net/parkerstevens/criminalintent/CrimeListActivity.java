package net.parkerstevens.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by pstev on 2/10/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
