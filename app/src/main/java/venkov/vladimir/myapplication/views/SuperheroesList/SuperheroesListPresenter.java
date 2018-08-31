package venkov.vladimir.myapplication.views.SuperheroesList;

import java.io.IOException;
import java.util.List;

import venkov.vladimir.myapplication.async.AsyncRunner;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.services.base.SuperheroesService;

public class SuperheroesListPresenter implements SuperheroesListContracts.Presenter {
    private final SuperheroesService mSuperheroesService;
    private SuperheroesListContracts.View mView;

    public SuperheroesListPresenter(SuperheroesService superheroesService) {
        mSuperheroesService = superheroesService;
    }

    @Override
    // same as // setView(SuperheroesListContracts.View view)
    public void subscribe(SuperheroesListContracts.View view) {
        mView = view;
    }

    @Override
    public void loadSuperheroes() {
        mView.showLoading();
        AsyncRunner.runInBackground(() -> {
            try {
                List<Superhero> superheroes =
                        mSuperheroesService.getAllSuperheroes();
                presentSuperheroesToView(superheroes);
            } catch (IOException e) {
                e.printStackTrace();
                mView.showError(e);
            }
            mView.hideLoading();
        });
    }

    @Override
    public void filterSuperheroes(String pattern) {
        mView.showLoading();
        AsyncRunner.runInBackground(() -> {
            try {
                List<Superhero> superheroes =
                        mSuperheroesService.getFilteredSuperheroes(pattern);
                presentSuperheroesToView(superheroes);
            } catch (IOException e) {
                e.printStackTrace();
                mView.showError(e);
            }
            mView.hideLoading();
        });
    }

    @Override
    public void selectSuperhero(Superhero superhero) {
        mView.showSuperheroDetails(superhero);
    }

    private void presentSuperheroesToView(List<Superhero> superheroes) {
        if (superheroes.isEmpty()) {
            mView.showEmptySuperheroesList();
        } else {
            mView.showSuperheroes(superheroes);
        }
    }

}
