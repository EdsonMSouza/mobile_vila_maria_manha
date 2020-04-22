package com.ems.aula_2204;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Resultado extends AppCompatActivity {
    TextView txtResultado;
    Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        txtResultado = findViewById(R.id.textViewResultado);
        btVoltar = findViewById(R.id.btVoltar);

        // recuperar os objetos enviados pela Intent (Serializados)
        ArrayList<Aluno> listaAlunos = (ArrayList<Aluno>)
                getIntent().getSerializableExtra("listaAlunos");

        // concatenar o resultados dos atributos do objeto na TextView
        String tmp = new String();

        // percorrendo a lista de alunos
        for(Aluno aluno : listaAlunos){
            tmp = tmp + aluno.getRa() + ", " +
                    aluno.getNome() + " , " +
                    aluno.getCurso() + "\n";
        }
        txtResultado.setText(tmp);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}