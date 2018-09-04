package venkov.vladimir.myapplication.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.myapplication.views.SuperheroesList.SuperheroesListContracts;
import venkov.vladimir.myapplication.views.SuperheroesList.SuperheroesListFragment;
import venkov.vladimir.myapplication.views.SuperheroesList.SuperheroesListPresenter;

@Module
public abstract class SuperheroesListModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract SuperheroesListFragment superheroesListFragment();

    @ActivityScoped
    @Binds
    abstract SuperheroesListContracts.Presenter superheroesListPresenter(SuperheroesListPresenter presenter);
}

