package com.ems.aula_1504;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Resultado extends AppCompatActivity {
    TextView nome, sobrenome;
    Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        // ligar os objetos
        nome = findViewById(R.id.textViewNome);
        sobrenome = findViewById(R.id.textViewSobrenome);
        btVoltar = findViewById(R.id.buttonVoltar);

        // recuperar os dados enviados
        // inicializar o correio lá da outra tela e recuperar o conteúdo
        Intent correio = getIntent();

        // criando o pacote para poder ser aberto
        Bundle envelope = correio.getExtras();

        nome.setText(envelope.getString("nome"));
        sobrenome.setText(envelope.getString("sobrenome"));

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fecha a View(tela) atual
                finish();
            }
        });
    }
}
