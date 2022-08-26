package com.softka.pet.utils;

import static java.util.Objects.isNull;

public class EsNulo {

    static public String comparaString(String nuevo, String anterior) {
        if(isNull(nuevo)){
            return anterior;
        }
        return nuevo;
    }

    static public Integer comparaInteger(Integer nuevo, Integer anterior) {

        if(isNull(nuevo)){
            return anterior;
        }
        return nuevo;

    }

}
