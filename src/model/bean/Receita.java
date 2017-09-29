package model.bean;

public class Receita {
    String nome;
    String ingrdientes;
    String receita;
    int id_usuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngrdientes() {
        return ingrdientes;
    }

    public void setIngrdientes(String ingrdientes) {
        this.ingrdientes = ingrdientes;
    }

    public String getReceita() {
        return receita;
    }

    public void setReceita(String receita) {
        this.receita = receita;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
}
