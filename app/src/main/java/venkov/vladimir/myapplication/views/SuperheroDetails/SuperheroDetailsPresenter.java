package venkov.vladimir.myapplication.views.SuperheroDetails;

import java.io.IOException;

import venkov.vladimir.myapplication.async.AsyncRunner;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.services.base.SuperheroesService;

public class SuperheroDetailsPresenter implements SuperheroDetailsContracts.Presenter {
    private final SuperheroesService mSuperheroesService;
    private SuperheroDetailsContracts.View mView;
    private int mSuperheroId;

    public SuperheroDetailsPresenter(SuperheroesService superheroesService) {
        mSuperheroesService = superheroesService;
    }

    @Override
    public void subscribe(SuperheroDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void loadSuperhero() {
        mView.showLoading();
        AsyncRunner.runInBackground(() -> {
            try {
                Superhero superhero = mSuperheroesService.getDetailsById(mSuperheroId);
                mView.showSuperhero(superhero);
            } catch (IOException e) {
                e.printStackTrace();
                mView.showError(e);
            }

            mView.hideLoading();
        });
    }

    public void setSuperheroId(int superheroId) {
        mSuperheroId = superheroId;
    }
}
