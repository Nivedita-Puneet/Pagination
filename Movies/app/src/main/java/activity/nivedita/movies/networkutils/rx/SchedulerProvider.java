package activity.nivedita.movies.networkutils.rx;

import io.reactivex.Scheduler;

/**
 * An interface which defines scheduler provider.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
