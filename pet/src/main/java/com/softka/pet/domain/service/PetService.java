package com.softka.pet.domain.service;

import com.softka.pet.domain.model.PetModel;
import com.softka.pet.domain.repository.PetRepository;
import com.softka.pet.utils.EsNulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Objects;

@Service
public class PetService {

    // Enlace del repositorio con mi servicio

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Servicios [CRUD]

    public Flux<PetModel> getAllPets(){
        return petRepository.findAll().log();
    }

    public Mono<PetModel> getPetById(String id){
        return petRepository.findById(id).log();
    }

    public Mono<PetModel> postPet(PetModel pet){
        return petRepository.save(pet).log();
    }

    public Mono<ResponseEntity<PetModel>> updatePet(String id,PetModel pet){

        return petRepository.findById(id)

                .flatMap(petFind->{

                    petFind.setNamePet(EsNulo.comparaString(pet.getNamePet(),petFind.getNamePet()));
                    petFind.setParentsName(EsNulo.comparaString(pet.getParentsName(),petFind.getParentsName()));
                    petFind.setType(EsNulo.comparaString(pet.getType(), petFind.getType()));
                    petFind.setRace(EsNulo.comparaString(pet.getRace(), petFind.getRace()));
                    petFind.setAge(EsNulo.comparaInteger(pet.getAge(), petFind.getAge()));
                    petFind.setCharacteristicsPet(EsNulo.comparaString(pet.getCharacteristicsPet(), petFind.getCharacteristicsPet()));
                    petFind.setPhone(EsNulo.comparaString(pet.getPhone(), petFind.getPhone()));
                    petFind.setAdress(EsNulo.comparaString(pet.getAdress(), petFind.getAdress()));
                    petFind.setState(EsNulo.comparaInteger(pet.getState(), petFind.getState()));

                    return petRepository.save(petFind);

                })
                .map(updatedBook -> ResponseEntity.ok().<PetModel>build() )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));

    }
    
    public Mono<ResponseEntity<PetModel>> updatePetComplete(String id,PetModel pet){

        return petRepository.findById(id)

                .flatMap(petFind->{

                    petFind.setNamePet(pet.getNamePet());
                    petFind.setParentsName(pet.getParentsName());
                    petFind.setType(pet.getType());
                    petFind.setRace(pet.getRace());
                    petFind.setAge(pet.getAge());
                    petFind.setCharacteristicsPet(pet.getCharacteristicsPet());
                    petFind.setPhone(pet.getPhone());
                    petFind.setAdress(pet.getAdress());
                    petFind.setState(pet.getState());

                    return petRepository.save(petFind);

                })
                .map(updatedBook -> ResponseEntity.ok().<PetModel>build() )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));

    }

    public Mono<PetModel> deletePet(String id){

        if(Objects.isNull(id)){
            return Mono.empty();
        }

        return petRepository.findById(id).flatMap(petFind ->{
            petFind.setState(1); // 1 significa que se borro
            return petRepository.save(petFind);
        });

    }

}
