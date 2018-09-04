package venkov.vladimir.myapplication.async;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import venkov.vladimir.myapplication.async.base.SchedulerProvider;

public class AsyncSchedulerProvider implements SchedulerProvider {
    private static SchedulerProvider instance;

    private AsyncSchedulerProvider() {
        // Empty private constructor for singleton
    }

    public static SchedulerProvider getInstance() {
        if (instance == null) {
            instance = new AsyncSchedulerProvider();
        }
        return instance;
    }

    @Override
    public Scheduler background() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
