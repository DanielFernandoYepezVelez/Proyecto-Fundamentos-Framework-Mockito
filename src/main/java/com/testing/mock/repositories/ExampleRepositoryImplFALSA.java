package com.testing.mock.repositories;

import com.testing.mock.models.Example;

import java.util.Arrays;
import java.util.List;

/*
* ==== ESTO SE VA A CONVERTIR EN UN MOCK MÁS ADELANTE Y NO SE VA A NECESITAR ESTA CLASE ====
* Esta es una implementación falsa, vamos a simular que se va a
* comportar de cierta forma, para tener unos datos de prueba, pero,
* esta clase no va a quedar operativa, por que nosotros en la vida real,
* no vamos a tener acceso a este codigo de implementacion.
* =============== Estos datos de prueba NO estan FUNCIONANDO ACTUALMENTE ====================
* */

public class ExampleRepositoryImplFALSA implements IExampleRepository {
    @Override
    public Example saveExample(Example example) {
        return null;
    }

    @Override
    public List<Example> findAll() {
        return Arrays.asList(
                new Example(5L, "Example Matemáticas"),
                new Example(6L, "Example Lenguaje"),
                new Example(7L, "Example Historia")
        );
    }
}
