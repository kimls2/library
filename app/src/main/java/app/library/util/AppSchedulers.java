package app.library.util;

import io.reactivex.Scheduler;

public class AppSchedulers {
    private Scheduler io;
    private Scheduler main;

    public AppSchedulers(Scheduler io, Scheduler main) {
        this.io = io;
        this.main = main;
    }

    public Scheduler io() {
        return io;
    }

    public Scheduler main() {
        return main;
    }
}
