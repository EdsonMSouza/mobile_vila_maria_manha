package com.ems.aula_1504;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    // declarar variáveis para associar com os objetos da View (Layout)
    EditText nome, sobrenome;
    Button btOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ligando as variáveis que criamos com os objetos da View
        nome = findViewById(R.id.editTextNome);
        sobrenome = findViewById(R.id.editTextSobrenome);
        btOk = findViewById(R.id.buttonOk);

        // acionando o botão
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // capturar o valor informado pelo usuário
                String txtNome = nome.getText().toString();
                String txtSobrenome = sobrenome.getText().toString();

                // começando a preparar para enviar para a segunda tela
                // criar um "ambiente" para enviar os dados
                // Intent(classe atual, classe destino)
                Intent correio = new Intent(
                        getApplicationContext(),
                        Resultado.class);

                // Criar um "envelope" para colocar os dados
                Bundle envelope = new Bundle();

                // colocar os dados dentro do envelope
                envelope.putString("nome", txtNome);
                envelope.putString("sobrenome", txtSobrenome);

                // colocar o envelope no correio
                correio.putExtras(envelope);

                // por fim, vamos transportar a correspondência
                startActivity(correio);

                // testar se tudo está OK
                Toast.makeText(MainActivity.this,
                        txtNome + txtSobrenome,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
