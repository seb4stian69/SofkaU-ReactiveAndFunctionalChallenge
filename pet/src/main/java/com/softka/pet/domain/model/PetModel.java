package com.softka.pet.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data /* */ @Getter@Setter
@Document(collection = "pet")
public class PetModel {

    @Id
    private String id;

    @NotBlank(message = "El nombre de la mascota es obligatorio")
    private String namePet;
    @NotBlank(message="El nombre del dueño es obligatorio")
    private String parentsName;
    @NotBlank(message="El tipo de mascota es obligatorio")
    private String type;
    @NotBlank(message="La raza es obligatoria")
    private String race;
    @NotBlank(message="La edad es obligatoria")
    private Integer age;
    @NotBlank(message="Las caracteristicas de la mascota son obligatorias")
    private String characteristicsPet;
    @NotBlank(message="El telefono del dueño es obligatorio")
    private String phone;
    @NotBlank(message="La direccion de la residencia de la mascota es obligatoria")
    private String adress;
    @NotBlank(message="El estado es obligatorio")
    private Integer state;

    public PetModel() {/*Void constructor*/}

}

