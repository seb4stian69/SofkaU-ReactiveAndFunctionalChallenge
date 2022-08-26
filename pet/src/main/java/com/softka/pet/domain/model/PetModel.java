package com.softka.pet.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data /* */ @Getter@Setter
@Document(collection = "pet")
public class PetModel {

    @Id
    private String id;

    @NotBlank(message = "El nombre de la mascota es obligatorio")
    @NotNull
    private String namePet;

    @NotBlank(message="El nombre del dueño es obligatorio")
    @NotNull
    private String parentsName;

    @NotBlank(message="El tipo de mascota es obligatorio")
    @NotNull
    private String type;

    @NotBlank(message="La raza es obligatoria")
    @NotNull
    private String race;

    private Integer age;

    @NotBlank(message="Las caracteristicas de la mascota son obligatorias")
    @NotNull
    private String characteristicsPet;

    @NotBlank(message="El telefono del dueño es obligatorio")
    @NotNull
    private String phone;

    @NotBlank(message="La direccion de la residencia de la mascota es obligatoria")
    @NotNull
    private String adress;

    private Integer state;

    public PetModel() {/*Void constructor*/}

}

