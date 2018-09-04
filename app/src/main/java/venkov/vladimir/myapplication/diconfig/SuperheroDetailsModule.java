package venkov.vladimir.myapplication.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.myapplication.views.SuperheroDetails.SuperheroDetailsContracts;
import venkov.vladimir.myapplication.views.SuperheroDetails.SuperheroDetailsFragment;
import venkov.vladimir.myapplication.views.SuperheroDetails.SuperheroDetailsPresenter;


@Module
public abstract class SuperheroDetailsModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract SuperheroDetailsFragment superheroDetailsFragment();

    @ActivityScoped
    @Binds
    abstract SuperheroDetailsContracts.Presenter superheroesListPresenter(SuperheroDetailsPresenter presenter);
}
