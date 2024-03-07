package br.com.fiap.tapago.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        
        // for(Categoria categoria: repository){
        //     if (categoria.id() == id)
        //         return ResponseEntity.status(HttpStatus.OK).body(categoria);
        // }
        //  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        //refatorado com stream

        // var categoriaEncontrada = repository
        //                             .stream()
        //                             .filter((c) -> { //expressão lambida, função anonima
        //         return c.id() == (id); //se o id da categoria for igual ao id retorna verdadeiro
        // }).findFirst(); //do stream retirado do filtro, pega a primeira e guarda na variavel
       
        var categoriaEncontrada = getCategoriaById(id);

        if (categoriaEncontrada.isEmpty()) //categoriaEcontrada é um optional
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(categoriaEncontrada.get());//sobrecarga do ok, ja retorna o corpo da resposta
     
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        log.info("apagando categoria {}", id);

        var categoriaEncontrada = getCategoriaById(id);

        if (categoriaEncontrada.isEmpty()) 
            return ResponseEntity.notFound().build();
        
        repository.remove(categoriaEncontrada.get());

        // var categoriaEncontrada = repository
        //         .stream()
        //         .filter(c -> c.id() == (id))
        //         .findFirst();

        // if (categoriaEncontrada.isEmpty()) 
        //     return ResponseEntity.notFound().build();

        //   repository = repository //vai colocar a lsita filtrada no repository original
        //                     .stream()
        //                     .filter(c -> c.id() != (id)) //vai passar no filtro todas as categorias exceto a que vai ser apagada
        //                     .toList(); //vai retorna uma lista com as categorias filtradas

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria){
        log.info("atualizando categoria {} para {}", id, categoria);
        // buscar a categoria antiga -> 404
        var categoriaEncontrada = getCategoriaById(id);

        if(categoriaEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        var categoriaAntiga = categoriaEncontrada.get();

        //criar a categoria nova com os dados atualizados
        var categoriaNova = new Categoria(id,categoria.nome(), categoria.icone());// esses dados vem do corpo da requisição


        //apagar a categoria antiga

        repository.remove(categoriaAntiga);

        //add a categoria nova

        repository.add(categoriaNova);

        return ResponseEntity.ok(categoriaNova);//build só pra resposta vazia
    }






    private Optional<Categoria> getCategoriaById(Long id) {
        var categoriaEncontrada = repository
                .stream()
                .filter(c -> c.id().equals(id))
                .findFirst();
        return categoriaEncontrada;
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
