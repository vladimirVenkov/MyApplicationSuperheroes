package venkov.vladimir.myapplication.views.SuperheroesList;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.models.Superhero;

public class SuperheroesListFragment
        extends Fragment
        implements SuperheroesListContracts.View, SuperheroesAdapter.OnSuperheroClickListener {
    private SuperheroesListContracts.Navigator mNavigator;

    @BindView(R.id.lv_superheroes)
    RecyclerView mSuperheroesView;

    @BindView(R.id.loading)
    ProgressBar mLoadingView;

    @BindView(R.id.et_filter)
    EditText mFilterEditText;

    @Inject
    SuperheroesAdapter mSuperheroesAdapter;

    private SuperheroesListContracts.Presenter mPresenter;
    private GridLayoutManager mSuperheroesViewLayoutManager;

    @Inject
    public SuperheroesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_superheroes_list, container, false);

        // ButterKnife is applied
        ButterKnife.bind(this, view);

        mSuperheroesAdapter.setOnSuperheroClickListener(this);

        mSuperheroesView.setAdapter(mSuperheroesAdapter);
        mSuperheroesViewLayoutManager = new GridLayoutManager(getContext(), 2);
        mSuperheroesView.setLayoutManager(mSuperheroesViewLayoutManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadSuperheroes();
    }

    @Override
    public void setPresenter(SuperheroesListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showSuperheroes(List<Superhero> superheroes) {
        mSuperheroesAdapter.clear();
        mSuperheroesAdapter.addAll(superheroes);
        mSuperheroesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptySuperheroesList() {
        Toast.makeText(getContext(),
                "No superheroes",
                Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showLoading() {
        mSuperheroesView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mSuperheroesView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showSuperheroDetails(Superhero superhero) {
        mNavigator.navigateWith(superhero);
    }

    void setNavigator(SuperheroesListContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    @OnTextChanged(R.id.et_filter)
    public void onTextChanged() {
        String pattern = mFilterEditText.getText().toString();
        mPresenter.filterSuperheroes(pattern);
    }

    @Override
    public void onClick(Superhero superhero) {
        mPresenter.selectSuperhero(superhero);
    }
}