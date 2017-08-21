package wybren_erik.hanzespel.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import wybren_erik.hanzespel.interfaces.InterventionListener;

/**
 * Created by wybrenoppedijk on 21/08/2017.
 */

public class Intervention {
    private Method positiveInterventions[] =  NegativeIntervention.class.getDeclaredMethods();
    private Method negativeInterventions[] = PositiveInterventions.class.getDeclaredMethods();
    private static Set<InterventionListener> handler = new HashSet<>();

    public Intervention() {
        int interventionType;
        Random rand = new Random();
        int randomNum = rand.nextInt((100 - 1) + 1) + 1;
        if (randomNum > 0 && randomNum <= 75 ) {
            return;
        } else if (randomNum > 75 && randomNum <= 90){
            randomNum = rand.nextInt((5 - 1) + 1) + 1;
            try {
                negativeInterventions[randomNum].invoke(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return;
        }
        else {
            randomNum = rand.nextInt((4 - 1) + 1) + 1;
            try {
                positiveInterventions[randomNum].invoke(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return;
        }
    }

    public static void addListener(InterventionListener listener){
        handler.add(listener);
    }

    private class NegativeIntervention{
        private void pirateship(){
            for (InterventionListener l: handler
                 ) {
                l.pirateship();
            }
        }

        private void boatLeak(){
            for (InterventionListener l: handler
                    ) {
                l.boatLeak();
            }
        }

        private void crewOverboard() {
            for (InterventionListener l: handler
                    ) {
                l.crewOverboard();
            }
        }

        private void badWeaather(){
            for (InterventionListener l: handler
                    ) {
                l.badWeather();
            }
        }

        private void bandit(){
            for (InterventionListener l: handler
                    ) {
                l.bandit();
            }
        }
    }

    private class PositiveInterventions{

        void goodWeather(){
            for (InterventionListener l: handler
                    ) {
                l.goodWeather();
            }
        }

        void increaseOfValue(){
            for (InterventionListener l: handler
                    ) {
                l.foundHiddenChest();
            }
        }

        void defeatPirateShip(){
            for (InterventionListener l: handler
                    ) {
                l.defeatPirateShip();
            }
        }
        void shortCut(){
            for (InterventionListener l: handler
                    ) {
                l.shortCut();
            }
        }
    }
}


