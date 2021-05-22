package com.github.almggmla.projetocidades.countries;

import com.github.almggmla.projetocidades.countries.Country;
import com.github.almggmla.projetocidades.countries.repository.CountryRepository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries") //minha rota
public class CountryResource {

    //@Autowired ---> não precisaria criar o construtor, ele mesmo resolviria isso.
    private CountryRepository repository;

    public CountryResource(CountryRepository repository) {
        this.repository = repository;
    }

//    @GetMapping
//    public List<Country> countries(){
//        return repository.findAll();
//    }

    @GetMapping
    public Page<Country> countries(Pageable page){
        return repository.findAll(page);
    }


//    public Country getOne(@PathVariable Long id){
//        Optional<Country> optional = repository.findById(id);
//        return optional.get();
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        Optional<Country> optional = repository.findById(id);
        if (optional.isPresent()){ // verifica se existe um país no ID informado.
            return ResponseEntity.ok().body(optional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
