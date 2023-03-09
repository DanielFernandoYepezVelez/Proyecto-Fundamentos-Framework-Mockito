package com.testing.mock.services;

import com.testing.mock.models.Example;

import java.util.Optional;

public interface IExampleService {

    Optional<Example> findExampleByName(String name);
    Example findByIdExampleByNameAndQuestion(String name);
    Example exampleSave(Example example);

}
