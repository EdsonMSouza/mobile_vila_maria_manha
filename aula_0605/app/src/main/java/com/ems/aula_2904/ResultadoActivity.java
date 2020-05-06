package com.ems.aula_2904;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResultadoActivity extends AppCompatActivity {
    TextView ra, nome, curso;
    Button btVoltar;
    ArrayList<Aluno> objAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        ra = findViewById(R.id.textViewRa);
        nome = findViewById(R.id.textViewNome);
        curso = findViewById(R.id.textViewCurso);
        btVoltar = findViewById(R.id.btVoltar);

        // recuperando os dados passados pela Intent
        objAluno = (ArrayList<Aluno>)
                getIntent().getSerializableExtra("objAluno");

        // atribuindo o resultado aos objetos da View
        ra.setText(objAluno.get(0).getRa());
        nome.setText(objAluno.get(0).getNome());
        curso.setText(objAluno.get(0).getCurso());

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
