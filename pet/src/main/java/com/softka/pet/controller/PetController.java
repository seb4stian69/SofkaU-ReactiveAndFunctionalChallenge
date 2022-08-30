package com.softka.pet.controller;

import com.softka.pet.domain.model.PetModel;
import com.softka.pet.domain.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/clinica/pets")
public class PetController {

    // Enlace

    private final PetService petService;
    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    // Controladores

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PetModel> listarMascotas(){
        return petService.getAllPets();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PetModel> listarMascotasPorId(@PathVariable String id){
        return petService.getPetById(id);
    }

    @PostMapping("/")
    public Mono<PetModel> publicarMascota(@RequestBody @Valid PetModel pet) {
        return petService.postPet(pet).log();
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<PetModel>> actualizarMascota(@PathVariable String id, @RequestBody PetModel pet) {
        return petService.updatePet(id, pet);
    }


    @PutMapping("/updateComplete/{id}")
    public Mono<ResponseEntity<PetModel>> actualizarMascotaCompleta(@PathVariable String id, @RequestBody @Valid PetModel pet){
        return petService.updatePetComplete(id,pet);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminarMascota(@PathVariable String id){
        return petService.deletePet(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
}
