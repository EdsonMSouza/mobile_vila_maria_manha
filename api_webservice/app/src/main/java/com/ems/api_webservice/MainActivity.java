package com.ems.api_webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    // criar variáveis para manipular os dados
    EditText loginUsuario, loginSenha;
    TextView loginResultado;
    Button btLogin;

    // variável para receber o JSON a ser enviado para a API
    String jsonInputString = null;

    // cria uma nova classe dentro da classe MainActivity
    public class UserService extends AsyncTask<Void, Void, List<User>> {
        // construtor da classe
        public UserService(User user) {
            // cria uma instância para o objeto GSON
            Gson gson = new Gson();
            // atribuindo o objeto user para ser convertido em JSON
            jsonInputString = gson.toJson(user);
        }

        // criar o método que é executado em segundo plano
        protected List<User> doInBackground(Void... voids) {
            // variável para receber a resposta do servidor (Pull)
            final StringBuilder response = new StringBuilder();

            try {
                // configurações para se conectar ao servidor externo (API)
                // endereço do serviço (API)
                URL url = new URL("http://emsapi.esy.es/api_android/user.php");
                // cria a conexão com o servidor onde está a API
                HttpURLConnection serv = (HttpURLConnection) url.openConnection();
                // configurar a requisição para o servidor
                serv.setRequestMethod("POST");
                serv.setRequestProperty("Content-Type", "application/json; utf-8");
                serv.setRequestProperty("Accept", "application/json");
                serv.setConnectTimeout(5000); // 5 segundos para se conectar
                serv.setReadTimeout(5000); // 5 segundos para me dar uma resposta
                serv.setDoInput(true); // permite que os dados sejam retornados
                serv.setDoOutput(true); // permite que os dados sejam lidos do servidor

                // enviando os dados para o servidor (API)
                try (OutputStream os = serv.getOutputStream()) {
                    // informar qual é o tipo de dados que vamos enviar
                    byte[] input = jsonInputString.getBytes("utf-8");
                    // vamos enviar agora a requisição completa (request) ou (push)
                    os.write(input, 0, input.length);
                }

                // lendo a resposta enviada pelo servidor (pull)
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(serv.getInputStream(), "utf-8"))) {
                    // variável para receber os dados retornados pelo servidor
                    String responseLine = null;
                    // percorrendo o conteúdo retornado
                    while ((responseLine = br.readLine()) != null) {
                        // coloca cada linha do retorno do servidor no response (array de string)
                        response.append(responseLine.trim());
                    }
                    // mostramos os dados retornados no Logcat
                    System.out.println("Resposta: " + response.toString());
                }

                // IO significa: I-input(entrada), O=output(saída)
            } catch (IOException io) {
                System.out.println(io.getMessage());
            }

            // criar um tipo personalizado para receber os dados retornados
            Type userType = new TypeToken<ArrayList<User>>() {
            }.getType();

            // criar uma lista para repassar os dados retornados para o objeto
            List<User> leituras = new Gson().fromJson(response.toString(), userType);
            return leituras;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // payload => {"type":"login","user":"valor","password":"valor"}

        // cria um objeto User
        User user = new User();
        user.setType("login");
        user.setUser("tio.patinhas");
        user.setPassword("patinhas123");
        user.setName("Tio Patinhas da Silva");

        loginResultado = findViewById(R.id.loginResultado);

        // chamar o serviço HTTP para a API
        try {
            ArrayList<User> dados = (ArrayList<User>) new UserService(user).execute().get();
            Toast.makeText(this, dados.get(0).getName(), Toast.LENGTH_LONG).show();

            loginResultado.setText(dados.get(0).getIdName());

        } catch (ExecutionException e) {

        } catch (InterruptedException i) {

        }
    }
}