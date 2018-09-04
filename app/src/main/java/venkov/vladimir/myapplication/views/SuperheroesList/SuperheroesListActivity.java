package venkov.vladimir.myapplication.views.SuperheroesList;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.ButterKnife;
import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.views.BaseDrawerActivity;
import venkov.vladimir.myapplication.views.SuperheroDetails.SuperheroDetailsActivity;
import venkov.vladimir.myapplication.views.SuperheroDetails.SuperheroDetailsFragment;
import venkov.vladimir.myapplication.views.SuperheroDetails.SuperheroDetailsPresenter;

public class SuperheroesListActivity
        extends BaseDrawerActivity
        implements SuperheroesListContracts.Navigator {
    public static final long IDENTIFIER = 49;

    @Inject
    SuperheroesListFragment mSuperheroesListFragment;

    @Inject
    SuperheroesListContracts.Presenter mSuperheroesListPresenter;

    @Inject
    SuperheroDetailsFragment mSuperheroDetailsFragment;

    @Inject
    SuperheroDetailsPresenter mSuperheroDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superheroes_list);

        ButterKnife.bind(this);

        setSupportActionBar(getToolbar());

        mSuperheroesListFragment.setNavigator(this);
        mSuperheroesListFragment.setPresenter(mSuperheroesListPresenter);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mSuperheroesListFragment);

        if (!isPhone()) {
            mSuperheroDetailsFragment.setPresenter(mSuperheroDetailsPresenter);
            transaction.replace(R.id.content_details, mSuperheroDetailsFragment);
        }

        transaction.commit();
    }

    private boolean isPhone() {
        return findViewById(R.id.content_details) == null;
    }

    @Override
    protected long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void navigateWith(Superhero superhero) {
        if (isPhone()) {
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
