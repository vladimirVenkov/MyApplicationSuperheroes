package venkov.vladimir.myapplication.views.SuperheroesList;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import venkov.vladimir.myapplication.AndroidApplication;
import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.services.base.SuperheroesService;
import venkov.vladimir.myapplication.views.BaseDrawerActivity;
import venkov.vladimir.myapplication.views.SuperheroDetails.SuperheroDetailsActivity;
import venkov.vladimir.myapplication.views.SuperheroDetails.SuperheroDetailsFragment;
import venkov.vladimir.myapplication.views.SuperheroDetails.SuperheroDetailsPresenter;

public class SuperheroesListActivity extends BaseDrawerActivity implements SuperheroesListContracts.Navigator {
    public static final long IDENTIFIER = 49;
    private SuperheroesListFragment mSuperheroesListFragment;
    private SuperheroesListContracts.Presenter mSuperheroesListPresenter;

    private boolean mIsPhone;
    private SuperheroDetailsFragment mSuperheroDetailsFragment;
    private SuperheroDetailsPresenter mSuperheroDetailsPresenter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superheroes_list);

        mToolbar = findViewById(R.id.drawer_toolbar);
        setSupportActionBar(mToolbar);

        SuperheroesService superheroesService = AndroidApplication.getSuperheroesService();

        mSuperheroesListPresenter =
                new SuperheroesListPresenter(superheroesService);

        mSuperheroesListFragment = SuperheroesListFragment.newInstance();
        mSuperheroesListFragment.setNavigator(this);
        mSuperheroesListFragment.setPresenter(mSuperheroesListPresenter);

        mIsPhone =
                findViewById(R.id.content_details) == null;

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();

        transaction
                .replace(R.id.content, mSuperheroesListFragment);

        if (!mIsPhone) {
            mSuperheroDetailsPresenter = new SuperheroDetailsPresenter(superheroesService);
            mSuperheroDetailsFragment = SuperheroDetailsFragment.newInstance();
            mSuperheroDetailsFragment.setPresenter(mSuperheroDetailsPresenter);
            transaction.replace(R.id.content_details, mSuperheroDetailsFragment);
        }

        transaction.commit();
    }

    @Override
    protected long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    protected Toolbar getDrawerToolbar() {
        return mToolbar;
    }

    @Override
    public void navigateWith(Superhero superhero) {
        if (mIsPhone) {
            Intent intent = new Intent(
                    this,
                    SuperheroDetailsActivity.class
            );

            intent.putExtra(SuperheroDetailsActivity.EXTRA_KEY, superhero);

            startActivity(intent);
        } else {
            mSuperheroDetailsPresenter.setSuperheroId(superhero.getId());
            mSuperheroDetailsPresenter.loadSuperhero();
        }
    }

}
