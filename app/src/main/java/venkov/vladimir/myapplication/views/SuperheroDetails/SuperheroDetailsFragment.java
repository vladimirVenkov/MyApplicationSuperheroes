package venkov.vladimir.myapplication.views.SuperheroDetails;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.models.Superhero;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperheroDetailsFragment extends Fragment implements SuperheroDetailsContracts.View {
    private SuperheroDetailsContracts.Presenter mPresenter;
    private TextView mNameTextView;
    private TextView mSecretIdentityTextView;

    public SuperheroDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_superhero_details, container, false);

        mNameTextView = view.findViewById(R.id.tv_name);
        mSecretIdentityTextView = view.findViewById(R.id.tv_secret_identity);
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
        runOnUi(() -> {
            mNameTextView.setText(superhero.getName());
            mSecretIdentityTextView.setText(superhero.getSecretIdentity());
        });
    }

    @Override
    public void setPresenter(SuperheroDetailsContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private void runOnUi(Runnable action) {
        getActivity().runOnUiThread(action);
    }

    public static SuperheroDetailsFragment newInstance() {
        return new SuperheroDetailsFragment();
    }
}
