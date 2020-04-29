package com.ems.aula_2904;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ra, nome, curso;
    Button btInserir;
    ListView lvAlunos;

    // criar uma variável para receber a lista de objetos
    ArrayList<Aluno> alunos = new ArrayList<>();

    // vamos criar um adaptador para receber os "alunos" e
    // colocá-los dentro da ListView
    ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ra = findViewById(R.id.editTextRa);
        nome = findViewById(R.id.editTextNome);
        curso = findViewById(R.id.editTextCurso);
        btInserir = findViewById(R.id.btInserir);
        lvAlunos = findViewById(R.id.listViewAlunos);

        // configurar o adaptador para associar com a ListView
        adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                alunos);

        // ligar o adaptador (adapter) com a ListView (lvAlunos)
        lvAlunos.setAdapter(adapter);

        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alunos.add(
                        new Aluno(
                                ra.getText().toString(),
                                nome.getText().toString(),
                                curso.getText().toString())
                );
                // avisar o adaptador que a lista de alunos foi alterada
                adapter.notifyDataSetChanged();
                ra.setText("");
                nome.setText("");
                curso.setText("");
                // colocar o cursor no campo "ra"
                ra.requestFocus();

                // fecha o teclado virtual
                ((InputMethodManager) MainActivity.this.getSystemService(
                        Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                        getCurrentFocus().getWindowToken(), 0);
            }
        });
    }
}
