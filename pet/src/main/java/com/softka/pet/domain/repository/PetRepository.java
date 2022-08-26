package com.softka.pet.domain.repository;

import com.softka.pet.domain.model.PetModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends ReactiveMongoRepository<PetModel, String> {}
