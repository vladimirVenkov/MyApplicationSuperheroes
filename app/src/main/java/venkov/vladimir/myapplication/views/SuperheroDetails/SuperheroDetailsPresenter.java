package venkov.vladimir.myapplication.views.SuperheroDetails;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import venkov.vladimir.myapplication.async.base.SchedulerProvider;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.services.base.SuperheroesService;

public class SuperheroDetailsPresenter
        implements SuperheroDetailsContracts.Presenter {
    private final SuperheroesService mSuperheroesService;
    private final SchedulerProvider mSchedulerProvider;

    private SuperheroDetailsContracts.View mView;
    private int mSuperheroId;

    @Inject
    public SuperheroDetailsPresenter(
            SuperheroesService superheroesService,
            SchedulerProvider schedulerProvider
    ) {
        mSuperheroesService = superheroesService;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void subscribe(SuperheroDetailsContracts.View view) {
        mView = view;
    }

    @Override
    public void loadSuperhero() {
        mView.showLoading();
        Disposable observable = Observable
                .create((ObservableOnSubscribe<Superhero>) emitter -> {
                    Superhero superhero = mSuperheroesService.getDetailsById(mSuperheroId);
                    emitter.onNext(superhero);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnError(mView::showError)
                .subscribe(mView::showSuperhero);
    }

    @Override
    public void setSuperheroId(int superheroId) {
        mSuperheroId = superheroId;
    }
}
