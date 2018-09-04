package venkov.vladimir.myapplication.async;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import venkov.vladimir.myapplication.async.base.SchedulerProvider;

public class SyncSchedulerProvider implements SchedulerProvider {
    @Override
    public Scheduler background() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}
