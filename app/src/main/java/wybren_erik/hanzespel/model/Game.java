package wybren_erik.hanzespel.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import wybren_erik.hanzespel.interfaces.GameListener;

public class Game {

    private static ScheduledFuture<?> gameEndFuture;
    private static Set<GameListener> listeners = new HashSet<>();
    private static ScheduledExecutorService executor;

    private static long totalTime;

    private static Runnable updateTimeTask = new Runnable() {
        @Override
        public void run() {
            try {
                for (GameListener l : listeners) {
                    l.onGameTimeChanged(gameEndFuture.getDelay(TimeUnit.MILLISECONDS));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private static Runnable endGameTask = new Runnable() {
        @Override
        public void run() {
            for (GameListener l : listeners) {
                l.onGameEnd();
                executor.shutdownNow();
            }
        }
    };

    private static Runnable warnGameEndTask = new Runnable() {
        @Override
        public void run() {
            for(GameListener l : listeners) {
                l.onWarnGameEnd();
            }
        }
    };

    public static void start(long time) {
        totalTime = time;
        executor = Executors.newScheduledThreadPool(2);
        gameEndFuture = executor.schedule(endGameTask, time, TimeUnit.MILLISECONDS);
        executor.schedule(warnGameEndTask, time - 0x927c0, TimeUnit.MILLISECONDS); // Time minus ten minutes
        executor.scheduleAtFixedRate(updateTimeTask, 1000, 1000, TimeUnit.MILLISECONDS);
    }

    public static long getTotalGameTime() {
        return totalTime;
    }

    public static void addListener(GameListener l) {
        if (!listeners.contains(l))
            listeners.add(l);
    }

    public static long getRemainingGameTime() {
        return gameEndFuture.getDelay(TimeUnit.MILLISECONDS);
    }

}
