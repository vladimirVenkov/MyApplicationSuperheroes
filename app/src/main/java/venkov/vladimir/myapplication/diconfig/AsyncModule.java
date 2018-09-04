package venkov.vladimir.myapplication.diconfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.myapplication.async.AsyncSchedulerProvider;
import venkov.vladimir.myapplication.async.base.SchedulerProvider;

@Module
public class AsyncModule {
    @Provides
    @Singleton
    public SchedulerProvider schedulerProvider() {
        return AsyncSchedulerProvider.getInstance();
    }
}

