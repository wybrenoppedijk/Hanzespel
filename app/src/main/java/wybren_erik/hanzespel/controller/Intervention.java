package wybren_erik.hanzespel.controller;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import wybren_erik.hanzespel.interfaces.InterventionListener;

public class Intervention {

    private static final String TAG = "Intervention";
    private static Set<InterventionListener> handler = new HashSet<>();

    @SuppressWarnings("TryWithIdenticalCatches")
    public Intervention() {
        Random rand = new Random();
        int randomNum = rand.nextInt(110);
        Log.d(TAG, "Generated random number " + randomNum + " for intervention chances.");

        if (randomNum <= 0 || randomNum > 75) {
            if (randomNum > 75 && randomNum <= 90) {
                randomNum = rand.nextInt(4);
                try {
                    Method[] negativeInterventions = NegativeInterventions.class.getDeclaredMethods();
                    negativeInterventions[randomNum].invoke(new NegativeInterventions());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                randomNum = rand.nextInt(3);
                try {
                    Method[] positiveInterventions = PositiveInterventions.class.getDeclaredMethods();
                    positiveInterventions[randomNum].invoke(new PositiveInterventions());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addListener(InterventionListener listener) {
        handler.add(listener);
    }

    @SuppressWarnings("unused")
    private class NegativeInterventions {
        void pirateship() {
            for (InterventionListener l : handler
                    ) {
                l.pirateship();
            }
        }

        void boatLeak() {
            for (InterventionListener l : handler
                    ) {
                l.boatLeak();
            }
        }

        void crewOverboard() {
            for (InterventionListener l : handler
                    ) {
                l.crewOverboard();
            }
        }

        void badWeaather() {
            for (InterventionListener l : handler
                    ) {
                l.badWeather();
            }
        }

        void bandit() {
            for (InterventionListener l : handler
                    ) {
                l.bandit();
            }
        }
    }

    @SuppressWarnings("unused")
    private class PositiveInterventions {

        void goodWeather() {
            for (InterventionListener l : handler
                    ) {
                l.goodWeather();
            }
        }

        void increaseOfValue() {
            for (InterventionListener l : handler
                    ) {
                l.foundHiddenChest();
            }
        }

        void defeatPirateShip() {
            for (InterventionListener l : handler
                    ) {
                l.defeatPirateShip();
            }
        }

        void shortCut() {
            for (InterventionListener l : handler
                    ) {
                l.shortCut();
            }
        }
    }
}


