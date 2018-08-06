package venkov.vladimir.myapplication.views;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.uiutils.Navigator;

public class SuperheroesListActivity extends Activity implements Navigator {


    private SuperheroesListFragment mSuperheroesListFragmen;
    private boolean mIsPhone;
    private SuperheroDetailFragment mSuperheroDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superheroes_list);
        mSuperheroesListFragmen = SuperheroesListFragment.newInstance();
        mSuperheroesListFragmen.setNavigator(this);

        mIsPhone = findViewById(R.id.content_details) == null;

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.content, mSuperheroesListFragmen);
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
}
