package venkov.vladimir.myapplication.views.SuperheroesList;


import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.models.Superhero;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperheroesListFragment extends Fragment implements AdapterView.OnItemClickListener,
        SuperheroesListContracts.View, TextWatcher
{
    private SuperheroesListContracts.Navigator mNavigator;

    private ListView mSuperheroesListView;
    private ArrayAdapter<Superhero> mSuperheroesAdapter;

    private SuperheroesListContracts.Presenter mPresenter;
    private ProgressBar mLoadingView;
    private EditText mFilterEditText;

    public SuperheroesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_superheroes_list, container, false);

        mSuperheroesAdapter = new SuperheroesListAdapter(getContext());
        mSuperheroesListView = view.findViewById(R.id.lv_superheroes);
        mSuperheroesListView.setAdapter(mSuperheroesAdapter);
        mSuperheroesListView.setOnItemClickListener(this);

        mLoadingView = view.findViewById(R.id.loading);

        mFilterEditText = view.findViewById(R.id.et_filter);

        mFilterEditText.addTextChangedListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadSuperheroes();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Superhero superhero = mSuperheroesAdapter.getItem(position);
        mPresenter.selectSuperhero(superhero);

    }

    public static SuperheroesListFragment newInstance() {
        return new SuperheroesListFragment();
    }

    @Override
    public void setPresenter(SuperheroesListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showSuperheroes(List<Superhero> superheroes) {
        runOnUi(() -> {
            mSuperheroesAdapter.clear();
            mSuperheroesAdapter.addAll(superheroes);
        });
    }

    @Override
    public void showEmptySuperheroesList() {
        runOnUi(() -> Toast.makeText(getContext(),
                "No superheroes",
                Toast.LENGTH_LONG)
                .show()
        );
    }

    @Override
    public void showError(Exception e) {
        runOnUi(() ->
                Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG)
                        .show()
        );
    }

    @Override
    public void showLoading() {
        runOnUi(() -> {
            mSuperheroesListView.setVisibility(View.GONE);
            mLoadingView.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void hideLoading() {
        runOnUi(() -> {
            mSuperheroesListView.setVisibility(View.VISIBLE);
            mLoadingView.setVisibility(View.GONE);
        });
    }

    @Override
    public void showSuperheroDetails(Superhero superhero) {
        runOnUi(() -> mNavigator.navigateWith(superhero));
    }

    void setNavigator(SuperheroesListContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    private void runOnUi(Runnable action) {
        getActivity()
                .runOnUiThread(action);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String pattern = mFilterEditText.getText().toString();
        mPresenter.filterSuperheroes(pattern);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}
