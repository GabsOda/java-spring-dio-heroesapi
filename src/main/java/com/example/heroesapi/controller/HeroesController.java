package com.example.heroesapi.controller;

import com.example.heroesapi.document.Heroes;
import com.example.heroesapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.heroesapi.constans.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j
public class HeroesController {

    @Autowired
    private HeroesService heroesService;

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItems() {
        log.info("Request list of all heroes");

        return heroesService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    public Mono<ResponseEntity<Heroes>> findById(@PathVariable String id) {
        log.info("Requesting the hero with id {}", id);

        return heroesService.findById(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Heroes> create(@RequestBody Heroes heroes) {
        log.info("New Hero was created!");

        return heroesService.save(heroes);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL + "?{id}")
    @ResponseStatus(HttpStatus.CONTINUE)
    public Mono<HttpStatus> deleteById(@PathVariable String id) {
        heroesService.deleteById(id);

        log.info("Deleting the hero with id {}", id);

        return Mono.just(HttpStatus.CONTINUE);
    }

}
