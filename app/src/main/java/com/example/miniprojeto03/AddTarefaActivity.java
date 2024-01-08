package com.example.miniprojeto03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojeto03.dao.TarefaDAO;
import com.example.miniprojeto03.modelo.Tarefa;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTarefaActivity extends AppCompatActivity {

    private EditText etAddTitulo;
    private EditText etAddTarefa;
    private EditText etAddData;
    private Tarefa tarefaAtual;

    private Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarefa);

        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        etAddTitulo = findViewById(R.id.etAddTitulo);
        etAddTarefa = findViewById(R.id.etAddTarefa);
        etAddData = findViewById(R.id.etAddData);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        Intent it = getIntent();

        try{
            //edita tarefa
            tarefaAtual = (Tarefa) it.getExtras().getSerializable("tarefa");
        }catch(Exception e){
            //criar um nova tarefa
            tarefaAtual = null;
        }

        if(tarefaAtual != null){
            etAddTitulo.setText(tarefaAtual.getTitulo());
            etAddTarefa.setText(tarefaAtual.getDescricao());
            etAddData.setText(tarefaAtual.getData_criacao());
        }

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());


                if(tarefaAtual != null){//editar tarefa

                    String titulo = etAddTitulo.getText().toString();
                    String descricao = etAddTarefa.getText().toString();
                    String data_criacao = etAddData.getText().toString();
                    if(!titulo.isEmpty() && !descricao.isEmpty() && !data_criacao.isEmpty()){
                        tarefaAtual.setTitulo(titulo);
                        tarefaAtual.setDescricao(descricao);
                        tarefaAtual.setData_criacao(data_criacao);
                        if(tarefaDAO.atualizar(tarefaAtual)){
                            Toast.makeText(getApplicationContext(), "Nota atualizada", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else { //adicionar tarefa
                    String titulo = etAddTitulo.getText().toString();
                    String descricao = etAddTarefa.getText().toString();
                    etAddData.setText(formatador.format(data));
                    String data_criacao = etAddData.getText().toString();
                    if(!titulo.isEmpty() && !descricao.isEmpty() && !data_criacao.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setTitulo(titulo);
                        tarefa.setDescricao(descricao);
                        tarefa.setData_criacao(data_criacao);
                        if(tarefaDAO.salvar(tarefa)){
                            Toast.makeText(getApplicationContext(), "Nota cadastrada", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao ao cadastrar tarefa", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }
}