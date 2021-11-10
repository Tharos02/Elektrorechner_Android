package com.example.elektrorechner_schule_awe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.elektrorechner_schule_awe.math.MathHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int anzahlFelder;
    double parsedWiderstand, parsedStrom, parsedSpannung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***
         * Button und Textfelder getten
         */
        Button buttonBerechnen = (Button) findViewById(R.id.button_berechnen);
        EditText editTextSpannung = (EditText) findViewById(R.id.editTextSpannung);
        EditText editTextWiderstand = (EditText) findViewById(R.id.editTextWiderstand);
        EditText editTextStrom = (EditText) findViewById(R.id.editTextStrom);


        buttonBerechnen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /***
                 * Spinner Items selecten für Umrechnung
                 */
                Spinner spinnerSpannung = (Spinner) findViewById(R.id.spinnerSpannung);
                String spinnerSpannungValue = spinnerSpannung.getSelectedItem().toString();

                Spinner spinnerWiderstand = (Spinner) findViewById(R.id.spinnerWiderstand);
                String spinnerWiderstandValue = spinnerWiderstand.getSelectedItem().toString();

                Spinner spinnerStrom = (Spinner) findViewById(R.id.spinnerStrom);
                String spinnerStromValue = spinnerStrom.getSelectedItem().toString();

                ArrayList<String> spinnerValues = new ArrayList<>(Arrays.asList(spinnerSpannungValue, spinnerWiderstandValue, spinnerStromValue));

                /***
                 * MathHelper Klasse initialisieren für Berehnungen
                 */
                MathHelper mathHelper = new MathHelper(spinnerValues);


                /***
                 * Anzahl der gefüllten Felder ziehen
                 */
                if (!editTextWiderstand.getText().toString().isEmpty() && !editTextWiderstand.getText().toString().equals(".")) {
                    parsedWiderstand = Double.parseDouble(String.valueOf(editTextWiderstand.getText()));
                    anzahlFelder = anzahlFelder + 1;
                }
                if (!editTextSpannung.getText().toString().isEmpty() && !editTextSpannung.getText().toString().equals(".")) {
                    parsedSpannung = Double.parseDouble(String.valueOf(editTextSpannung.getText()));
                    anzahlFelder = anzahlFelder + 1;
                }
                if (!editTextStrom.getText().toString().isEmpty()  && !editTextStrom.getText().toString().equals(".")) {
                    parsedStrom = Double.parseDouble(String.valueOf(editTextStrom.getText()));
                    anzahlFelder = anzahlFelder + 1;
                }

                if (anzahlFelder == 2){
                    if (!editTextWiderstand.getText().toString().isEmpty() && !editTextStrom.getText().toString().isEmpty()) {
                        double res =  mathHelper.berechneSpannung(parsedWiderstand, parsedStrom);
                        if(res != 0) {
                            showAlert(res, 0);
                        } else {
                            Toast popUp = Toast.makeText(getApplicationContext(), "Bitte geben Sie richtige Werte an", Toast.LENGTH_LONG);
                            popUp.show();
                        }
                        anzahlFelder = 0;
                    } else if (!editTextWiderstand.getText().toString().isEmpty() && !editTextSpannung.getText().toString().isEmpty()) {
                        double res =  mathHelper.berechneStrom(parsedWiderstand, parsedSpannung);
                        if(res != 0) {
                            showAlert(res, 1);
                        } else {
                            Toast popUp = Toast.makeText(getApplicationContext(), "Bitte geben Sie richtige Werte an", Toast.LENGTH_LONG);
                            popUp.show();
                        }
                        anzahlFelder = 0;
                    }else if (!editTextSpannung.getText().toString().isEmpty() && !editTextStrom.getText().toString().isEmpty()) {
                        double res = mathHelper.berechneWiderstand(parsedSpannung, parsedStrom);
                        if(res != 0) {
                            showAlert(res, 2);
                        } else {
                            Toast popUp = Toast.makeText(getApplicationContext(), "Bitte geben Sie richtige Werte an", Toast.LENGTH_LONG);
                            popUp.show();
                        }
                        anzahlFelder = 0;
                    }
                } else if (anzahlFelder == 1 || anzahlFelder == 0) {
                    Toast popUp = Toast.makeText(getApplicationContext(), "Bitte geben Sie mindestens zwei Werte an", Toast.LENGTH_LONG);
                    popUp.show();
                    anzahlFelder = 0;
                } else if (anzahlFelder == 3) {
                    Toast popUp = Toast.makeText(getApplicationContext(), "Bitte geben Sie maximal zwei Werte an", Toast.LENGTH_LONG);
                    popUp.show();
                    anzahlFelder = 0;
                }
            }
        });
    }
    /***
     * Diese Methode generiert einen Alert mit dem Ergebnis und zeigt diesen an
     * @return nichts
     */
    public void showAlert(double res, int typ) {

        AlertDialog.Builder alert =  new AlertDialog.Builder(MainActivity.this)
                .setTitle("Ergebnis")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        if(typ == 0){
            alert.setMessage("Die Spannung beträgt: " + String.format("%.6f",res) + " V");
            alert.show();
        }
        else if (typ == 1) {
            alert.setMessage("Die Stromstärke beträgt: " + String.format("%.6f",res) + " A");
            alert.show();
        } else if (typ == 2) {
            alert.setMessage("Der Widerstand beträgt: " + String.format("%.6f",res) + " Ohm");
            alert.show();
        }

    }
}