package venkov.vladimir.myapplication.diconfig;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import venkov.vladimir.myapplication.views.SuperheroCreate.SuperheroCreateContracts;
import venkov.vladimir.myapplication.views.SuperheroCreate.SuperheroCreateFragment;
import venkov.vladimir.myapplication.views.SuperheroCreate.SuperheroCreatePresenter;

@Module
public abstract class SuperheroCreateModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract SuperheroCreateFragment superheroCreateFragment();

    @ActivityScoped
    @Binds
    abstract SuperheroCreateContracts.Presenter superheroCreatePresenter(SuperheroCreatePresenter presenter);
}

