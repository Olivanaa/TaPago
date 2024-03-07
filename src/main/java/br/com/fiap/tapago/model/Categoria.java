package br.com.fiap.tapago.model;

import java.util.Random;

// public class Categoria {
    
//     private long id; //se fosse int, seriam só 32mil categorias
//     private String nome;
//     private String icone;

//     //codigo  boilerplate - construtor, get e setters, solução record 
//     public Categoria(long id, String nome, String icone) {
//         this.id = id;
//         this.nome = nome;
//         this.icone = icone;
//     }.....

// record é imutavel, não tem sets

public record Categoria(Long id, String nome, String icone) {
    public Categoria(Long id, String nome, String icone){ //sobreescrevendo o construtor do record
        this.id = (id == null )? Math.abs(new Random().nextLong()) : id;
        this.nome = nome;
        this.icone = icone;
    }
}

    

