package com.ems.aula_2904;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // (Aluno) isso é conhecido como Cast ou Conversão
                Aluno aluno = (Aluno) lvAlunos.getItemAtPosition(position);

                Toast.makeText(
                        MainActivity.this,
                        aluno.getDados(),
                        Toast.LENGTH_LONG).show();
            }
        });

        lvAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno) lvAlunos.getItemAtPosition(position);
                // criar um ArrayList<Aluno> para passa via Intent
                ArrayList<Aluno> al = new ArrayList<>();
                // adiciona o objeto aluno ao ArrayList<Aluno>
                al.add(aluno);
                // criar a Intent
                Intent it = new Intent(MainActivity.this, ResultadoActivity.class);
                // passando o objeto via Intent
                it.putExtra("objAluno", al);
                // iniciar a outra View
                startActivity(it);

                return false;
            }
        });
    }
}
