package br.com.fiap.tapago.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tapago.model.Categoria;

@RestController //traz a funcionalidade de contorller e response body para todos os metodos, não preciso do conusmes, porduces, pois por padrão já é json
@RequestMapping("categoria")
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass()); 
    
    List<Categoria> repository = new ArrayList<>();

    @GetMapping  
    public List<Categoria> index(){ // listarCategorias() - ponto de partida
        return repository;
    }

    @PostMapping      
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria){ //categoria esta no corpo da requisição, biding - pega cada um dos campos do json e transofrma um objeto categira com os dados
        log.info("Cadastrando categoria {}", categoria); //"Cadastrando categoria" + categoria
        repository.add(categoria); //new Categoria(1L, "Alimentacao", "fast-food")
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Categoria> show(@PathVariable Long id){ //indica que id esta no path da requisição
        log.info("Buscando categoria com id {}", id);
        
        for(Categoria categoria: repository){
            if (categoria.id() == id)
                return ResponseEntity.status(HttpStatus.OK).body(categoria);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    


    // @ResponseStatus(code = HttpStatus.CREATED)
    // public Categoria create(@RequestBody Categoria categoria){ //categoria esta no corpo da requisição, biding - pega cada um dos campos do json e transofrma um objeto categira com os dados
    //     log.info("Cadastrando categoria {}", categoria); //"Cadastrando categoria" + categoria
    //     repository.add(categoria); //new Categoria(1L, "Alimentacao", "fast-food")
    //     return categoria;
    // }

        // //mock
        //return
        // """
        //     [
        //         {
        //             "id": 1,
        //             "nome": "Alimentação",
        //             "icone" : "fast-food"
    
        //         }
        //     ]                
        // """;

        // @GetMapping("{idCategoria}")
        // public void show(@PathVariable(name = "idCategoria") long id)
}
