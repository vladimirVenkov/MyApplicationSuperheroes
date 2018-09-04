package venkov.vladimir.myapplication.views.SuperheroDetails;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.models.Superhero;

public class SuperheroDetailsFragment
        extends Fragment
        implements SuperheroDetailsContracts.View {

    private SuperheroDetailsContracts.Presenter mPresenter;

    @BindView(R.id.tv_name)
    TextView mNameTextView;

    @BindView(R.id.tv_secret_identity)
    TextView mSecretIdentityTextView;

    @Inject
    public SuperheroDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_superhero_details, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadSuperhero();
    }

    @Override
    public void showSuperhero(Superhero superhero) {
        mNameTextView.setText(superhero.getName());
        mSecretIdentityTextView.setText(superhero.getSecretIdentity());
    }

    @Override
    public void setPresenter(SuperheroDetailsContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
