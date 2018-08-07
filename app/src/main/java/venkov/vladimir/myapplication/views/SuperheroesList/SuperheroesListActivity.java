package venkov.vladimir.myapplication.views.SuperheroesList;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import com.mikepenz.materialdrawer.Drawer;

import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.uiutils.Navigator;
import venkov.vladimir.myapplication.views.BaseDrawerActivity;
import venkov.vladimir.myapplication.views.SuperheroDetails.SuperheroDetailFragment;
import venkov.vladimir.myapplication.views.SuperheroDetails.SuperheroDetailsActivity;

public class SuperheroesListActivity extends BaseDrawerActivity implements Navigator {

    public static final long IDENTIFIER = 33;
    private SuperheroesListFragment mSuperheroesListFragment;
    private boolean mIsPhone;
    private SuperheroDetailFragment mSuperheroDetailsFragment;
    private Toolbar mToolbar;
    private Drawer mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superheroes_list);

        mToolbar = findViewById(R.id.drawer_toolbar);
        setSupportActionBar(mToolbar);

        mSuperheroesListFragment = SuperheroesListFragment.newInstance();
        mSuperheroesListFragment.setNavigator(this);

        mIsPhone = findViewById(R.id.content_details) == null;

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.content, mSuperheroesListFragment);
        if (!mIsPhone) {
            mSuperheroDetailsFragment = SuperheroDetailFragment.newInstance();
            mSuperheroDetailsFragment.setSuperhero("");//TODO doesn't work empty as in the video 1:09:...
            transaction.replace(R.id.content_details, mSuperheroDetailsFragment);
        }

        transaction.commit(); // 37:53 video until here



    }



    @Override
    public void navigateWith(String superhero) {
        if (mIsPhone) {
            Intent intent = new Intent(
                    this,
                    SuperheroDetailsActivity.class
            );
            intent.putExtra("SUPERHERO_NAME", superhero);
            startActivity(intent);
        } else {
            mSuperheroDetailsFragment.setSuperhero(superhero);
        }
    }

    @Override
    protected long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    protected Toolbar getDrawerToolbar() {
        return mToolbar;
    }
}
