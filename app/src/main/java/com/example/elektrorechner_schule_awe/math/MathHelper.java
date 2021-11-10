package com.example.elektrorechner_schule_awe.math;

import java.util.ArrayList;

public class MathHelper {
    /***
     * Notwendige Formel: U = R*I   R= widerstand   U= spannung   I= Strom
     */

    ArrayList<String> spinnerValues;

    /***
     * Dies ist der Konstruktor der die Spinnervalues benötigt
     * @param spinnerValues, dieser Parameter enthält die einheiten der Spinner
     */
    public MathHelper(ArrayList<String> spinnerValues) {
        this.spinnerValues = spinnerValues;
    }

    /***
     * Diese Methode berechnet die Spannung und führt nötige Umrechnungen durch
     * @return spannung in Volt oder 0 wenn mal 0 geteilt werden soll
     */
    public double berechneSpannung(double widerstand, double strom) {
        if(strom == 0) {
           return 0;
        }

        if(this.spinnerValues.get(1).equals("mOhm")) {
            widerstand = widerstand / 1000;
        }
        if(this.spinnerValues.get(2).equals("mA")) {
            strom = strom / 1000;
        }
        return (widerstand * strom);
    }

    /***
     * Diese Methode berechnet den Widerstand und führt nötige Umrechnungen durch
     * @return widerstand in Ohm oder 0 wenn durch 0 geteilt werden soll
     */
    public double berechneWiderstand(double spannung, double strom) {
        if(strom == 0) {
            return 0;
        }

        if(this.spinnerValues.get(0).equals("mV")) {
            spannung = spannung / 1000;
        }
        if(this.spinnerValues.get(2).equals("mA")) {
            strom = strom / 1000;
        }
        return (spannung / strom);
    }

    /***
     * Diese Methode berechnet die Stromstärke und führt nötige Umrechnungen durch
     * @return stromstärke in Ampere oder 0 wenn durch 0 geteilt werden soll
     */
    public double berechneStrom(double widerstand, double spannung) {
        if(widerstand == 0) {
            return 0;
        }

        if(this.spinnerValues.get(1).equals("mOhm")) {
            widerstand = widerstand / 1000;
        }
        if(this.spinnerValues.get(0).equals("mV")) {
            spannung = spannung / 1000;
        }
        return (spannung / widerstand);
    }
}
