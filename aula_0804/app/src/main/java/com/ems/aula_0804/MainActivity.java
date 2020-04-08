package com.ems.aula_0804;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    // declarar variáveis locais (em java) para ligar com
    // os objetos que criamos na View (layout)
    EditText nome, sobrenome;
    Button btConcatenar;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inicia uma View (Layout)
        setContentView(R.layout.activity_main);

        // ligando os objetos da View com as variáveis
        nome = findViewById(R.id.editTextNome);
        sobrenome = findViewById(R.id.editTextSobrenome);
        btConcatenar = findViewById(R.id.buttonConcatenar);
        resultado = findViewById(R.id.textViewResultado);

        // executando uma ação ao clicar no botão
        btConcatenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // aqui a magia acontece (Harry Potter)

                resultado.setText(nome.getText() + " " + sobrenome.getText());
            }
        });

    }
}
