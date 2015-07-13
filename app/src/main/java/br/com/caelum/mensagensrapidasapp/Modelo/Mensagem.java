package br.com.caelum.mensagensrapidasapp.Modelo;

import java.io.Serializable;

/**
 * Created by matheus on 13/07/15.
 */
public class Mensagem implements Serializable {

    private String nome;
    private String corpo;
    private Long id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Nome : " +getNome()+ "\n" + "Corpo : "+ getCorpo() ;
    }
}
