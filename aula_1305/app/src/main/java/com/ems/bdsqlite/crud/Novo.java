package com.ems.bdsqlite.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlite.R;
import com.ems.bdsqlite.utils.Banco;

public class Novo extends AppCompatActivity {
    EditText ra, nome, curso;
    ImageButton btConfirma, btVoltar;

    // variável para receber o banco de dados
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("Incluir Aluno");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ra = findViewById(R.id.editTextRa);
        nome = findViewById(R.id.editTextNome);
        curso = findViewById(R.id.editTextCurso);
        btConfirma = findViewById(R.id.btConfirma);
        btVoltar = findViewById(R.id.btVoltar);

        // abertura ou criação do banco de dados
        db = openOrCreateDatabase(
                Banco.banco(),
                Context.MODE_PRIVATE,
                null);

        // Cria a tabela se ela não existir
        db.execSQL(Banco.criaTabela());

        btConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cria um objeto para receber os dados que serão gravados
                ContentValues dados = new ContentValues();
                dados.put("ra", ra.getText().toString());
                dados.put("nome", nome.getText().toString());
                dados.put("curso", curso.getText().toString());

                // Inserir no banco de dados (Persistir)
                db.insert(Banco.tabela(), null, dados);

                // Mostrar uma mensagem para o usuário
                Toast.makeText(Novo.this, "Registro incluído com sucesso!", Toast.LENGTH_SHORT).show();

                // fechar a Activity
                finish();
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Configura o botão (seta) na ActionBar (Barra Superior)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}