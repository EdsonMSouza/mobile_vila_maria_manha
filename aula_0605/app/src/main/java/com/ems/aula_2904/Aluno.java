package com.ems.aula_2904;

import java.io.Serializable;

public class Aluno implements Serializable {
    // atributos da classe
    private String ra;
    private String nome;
    private String curso;

    // construtor da classe (inicializado)
    // toda vez que usar o new Aluno(ra, nome, curso)
    public Aluno(String ra, String nome, String curso) {
        this.ra = ra;
        this.nome = nome;
        this.curso = curso;
    }

    // getters e setters

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override // sobrescrita de método
    public String toString() {
        return nome;
    }

    // método para mostrar todos os dados do objeto
    // é diferente do toString() que só mostra o nome
    public String getDados() {
        return "RA: " + ra + "\nNOME: " + nome + "\nCURSO: " + curso;
    }
}
