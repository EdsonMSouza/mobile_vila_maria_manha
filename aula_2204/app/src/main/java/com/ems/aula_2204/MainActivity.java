package com.ems.aula_2204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ra, nome, curso;
    Button btInserir, btVisualizar;

    // cria uma lista de objetos do tipo Aluno (RG)
    ArrayList<Aluno> alunos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ra = findViewById(R.id.editTextRa);
        nome = findViewById(R.id.editTextNome);
        curso = findViewById(R.id.editTextCurso);
        btInserir = findViewById(R.id.btInserir);
        btVisualizar = findViewById(R.id.btVisualizar);

        // configurando o botão inserir
        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alunos.add(
                        new Aluno(
                                ra.getText().toString(),
                                nome.getText().toString(),
                                curso.getText().toString()
                        )
                );
                // limpar os campos
                ra.setText("");
                nome.setText("");
                curso.setText("");

                // posicionar o cursor no campo RA
                ra.requestFocus();
            }
        });

        btVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultado = new
                        Intent(MainActivity.this, Resultado.class);

                resultado.putExtra("listaAlunos", alunos);

                startActivity(resultado);
            }
        });
    }
}









