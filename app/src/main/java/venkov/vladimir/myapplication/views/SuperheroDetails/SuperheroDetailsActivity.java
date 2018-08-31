package venkov.vladimir.myapplication.views.SuperheroDetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import venkov.vladimir.myapplication.AndroidApplication;
import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.services.base.SuperheroesService;

public class SuperheroDetailsActivity extends Activity {

    public static final String EXTRA_KEY = "SUPERHERO_EXTRA_KEY";

    private SuperheroDetailsFragment mSuperheroDetailsFragment;
    private SuperheroDetailsPresenter mSuperheroDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_details);
        SuperheroesService superheroesService = AndroidApplication.getSuperheroesService();

        mSuperheroDetailsPresenter =
                new SuperheroDetailsPresenter(superheroesService);

        Intent intent = getIntent();
        Superhero superhero = (Superhero) intent.getSerializableExtra(SuperheroDetailsActivity.EXTRA_KEY);
        mSuperheroDetailsPresenter.setSuperheroId(superhero.getId());

        mSuperheroDetailsFragment = SuperheroDetailsFragment.newInstance();
        mSuperheroDetailsFragment.setPresenter(mSuperheroDetailsPresenter);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mSuperheroDetailsFragment)
                .commit();
    }

}
