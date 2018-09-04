package venkov.vladimir.myapplication.views.SuperheroDetails;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.views.BaseDrawerActivity;

public class SuperheroDetailsActivity extends BaseDrawerActivity {
    public static final String EXTRA_KEY = "SUPERHERO_EXTRA_KEY";

    @Inject
    SuperheroDetailsFragment mSuperheroDetailsFragment;

    @Inject
    SuperheroDetailsContracts.Presenter mSuperheroDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_details);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Superhero superhero = (Superhero) intent.getSerializableExtra(SuperheroDetailsActivity.EXTRA_KEY);

        mSuperheroDetailsPresenter.setSuperheroId(superhero.getId());
        mSuperheroDetailsFragment.setPresenter(mSuperheroDetailsPresenter);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mSuperheroDetailsFragment)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return 0;
    }
}
