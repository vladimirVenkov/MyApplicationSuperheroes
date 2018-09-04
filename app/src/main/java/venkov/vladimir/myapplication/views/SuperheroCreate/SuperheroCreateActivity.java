package venkov.vladimir.myapplication.views.SuperheroCreate;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.views.BaseDrawerActivity;
import venkov.vladimir.myapplication.views.SuperheroesList.SuperheroesListActivity;

public class SuperheroCreateActivity extends BaseDrawerActivity implements SuperheroCreateContracts.Navigator {
    public static final long IDENTIFIER = 298;

    @Inject
    SuperheroCreateFragment mView;

    @Inject
    SuperheroCreateContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_create);
        ButterKnife.bind(this);

        mView.setPresenter(mPresenter);
        mView.setNavigator(this);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mView)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, SuperheroesListActivity.class);
        startActivity(intent);
        finish();
    }
}
