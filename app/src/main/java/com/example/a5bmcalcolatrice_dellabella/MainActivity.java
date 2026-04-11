package com.example.a5bmcalcolatrice_dellabella;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // dichiaro gli elementi utilizzati
    TextView textView2;

    Button t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, punto, uguale, reciproco, radice, potenza, log10, diviso, per, piu, meno, canc, ac;

    // Variabili per la logica della calcolatrice
    private double primoNumero = 0;
    private double secondoNumero = 0;
    private String operazione = "";
    private boolean isNuovoNumero = true;
    private boolean isPuntoInserito = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView2 = findViewById(R.id.textView2);
        t0 = findViewById(R.id.t0);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);
        t9 = findViewById(R.id.t9);
        punto = findViewById(R.id.punto);
        uguale = findViewById(R.id.uguale);
        reciproco = findViewById(R.id.reciproco);
        radice = findViewById(R.id.radice);
        potenza = findViewById(R.id.potenza);
        log10 = findViewById(R.id.log10);
        diviso = findViewById(R.id.diviso);
        per = findViewById(R.id.per);
        piu = findViewById(R.id.piu);
        meno = findViewById(R.id.meno);
        canc = findViewById(R.id.canc);
        ac = findViewById(R.id.ac);

        textView2.setText("0");

        // Listener per i numeri
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String numeroPremuto = b.getText().toString();
                String testoAttuale = textView2.getText().toString();

                if (isNuovoNumero || testoAttuale.equals("0")) {
                    textView2.setText(numeroPremuto);
                    isNuovoNumero = false;
                } else {
                    textView2.setText(testoAttuale + numeroPremuto);
                }
                isPuntoInserito = testoAttuale.contains(".");
            }
        };

        // Assegna il listener a tutti i pulsanti
        t0.setOnClickListener(numberListener);
        t1.setOnClickListener(numberListener);
        t2.setOnClickListener(numberListener);
        t3.setOnClickListener(numberListener);
        t4.setOnClickListener(numberListener);
        t5.setOnClickListener(numberListener);
        t6.setOnClickListener(numberListener);
        t7.setOnClickListener(numberListener);
        t8.setOnClickListener(numberListener);
        t9.setOnClickListener(numberListener);

        // Listener per il punto
        punto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String testoAttuale = textView2.getText().toString();

                if (isNuovoNumero) {
                    textView2.setText("0.");
                    isNuovoNumero = false;
                    isPuntoInserito = true;
                } else if (!testoAttuale.contains(".")) {
                    textView2.setText(testoAttuale + ".");
                    isPuntoInserito = true;
                }
            }
        });

        // Listener per la somma
        piu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    primoNumero = Double.parseDouble(textView2.getText().toString());
                    operazione = "+";
                    isNuovoNumero = true;
                    isPuntoInserito = false;
                } catch (NumberFormatException e) {
                    textView2.setText("Errore");
                }
            }
        });

        // Listener per la sottrazione
        meno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    primoNumero = Double.parseDouble(textView2.getText().toString());
                    operazione = "-";
                    isNuovoNumero = true;
                    isPuntoInserito = false;
                } catch (NumberFormatException e) {
                    textView2.setText("Errore");
                }
            }
        });

        // Listener per il pulsante uguale
        uguale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!operazione.isEmpty()) {
                        secondoNumero = Double.parseDouble(textView2.getText().toString());
                        double risultato = 0;

                        switch (operazione) {
                            case "+":
                                risultato = primoNumero + secondoNumero;
                                break;
                            case "-":
                                risultato = primoNumero - secondoNumero;
                                break;
                        }

                        // Formatta il risultato per evitare decimali inutili
                        if (risultato == (long) risultato) {
                            textView2.setText(String.valueOf((long) risultato));
                        } else {
                            textView2.setText(String.valueOf(risultato));
                        }

                        operazione = "";
                        isNuovoNumero = true;
                        isPuntoInserito = textView2.getText().toString().contains(".");
                    }
                } catch (NumberFormatException e) {
                    textView2.setText("Errore");
                }
            }
        });
    }
}