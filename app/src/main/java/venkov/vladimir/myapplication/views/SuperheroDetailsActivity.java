package venkov.vladimir.myapplication.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import venkov.vladimir.myapplication.R;

public class SuperheroDetailsActivity extends Activity {

    private SuperheroDetailFragment mSuperheroDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_details);

        Intent intent = getIntent();
        String superhero = intent.getStringExtra("SUPERHERO_NAME");
        mSuperheroDetailsFragment = SuperheroDetailFragment.newInstance();
        mSuperheroDetailsFragment.setSuperhero(superhero);

        getFragmentManager().beginTransaction()
                .replace(R.id.content, mSuperheroDetailsFragment)
                .commit();
    }
}
