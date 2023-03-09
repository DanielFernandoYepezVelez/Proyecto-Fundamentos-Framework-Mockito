package com.testing.mock.services;

import com.testing.mock.Datos;
import com.testing.mock.models.Example;
import com.testing.mock.repositories.IExampleRepository;

import java.util.List;

public class ExampleRepositoryImpl implements IExampleRepository {

    @Override
    public Example saveExample(Example example) {
        System.out.println("saveExampleImpl!");
        return Datos.EXAMPLE;
    }

    @Override
    public List<Example> findAll() {
        System.out.println("findAllImpl!");
        return Datos.EXAMPLES;
    }
}
