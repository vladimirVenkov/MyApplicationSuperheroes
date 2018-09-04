package venkov.vladimir.myapplication;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import venkov.vladimir.myapplication.diconfig.DaggerAppComponent;

public class AndroidApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
//        return  null;
    }
}
