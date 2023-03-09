package com.testing.mock.services;

import com.testing.mock.models.Example;
import com.testing.mock.repositories.IExampleRepository;
import com.testing.mock.repositories.IQuestionRepository;

import java.util.List;
import java.util.Optional;

public class ExampleServiceImpl implements IExampleService {

    private IExampleRepository exampleRepository;
    private IQuestionRepository questionRepository;

    public ExampleServiceImpl(IExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public ExampleServiceImpl(IExampleRepository exampleRepository, IQuestionRepository questionRepository) {
        this.exampleRepository = exampleRepository;
        this.questionRepository = questionRepository;
    }

    /* @Override
    public Example findExampleByName(String name) {
        Example exampleGlobal = new Example();

        Optional<Example> exampleOptional = exampleRepository.findAll()
                .stream()
                .filter(example -> example.getName().contains(name))
                .findFirst();

        if (exampleOptional.isPresent()) {
            exampleGlobal = exampleOptional.orElseThrow(IllegalArgumentException::new);
        }

        return exampleGlobal;
    } */

    public Optional<Example> findExampleByName(String name) {
        return exampleRepository.findAll()
                .stream()
                .filter(example -> example.getName().contains(name))
                .findFirst();
    }

    @Override
    public Example findByIdExampleByNameAndQuestion(String name) {
        Example example = null;
        Optional<Example> exampleOptional = this.findExampleByName(name);

        if (exampleOptional.isPresent()) {
            example = exampleOptional.orElseThrow(IllegalAccessError::new);
            List<String> questions = questionRepository.questionFindByIdExample(exampleOptional.get().getId());
            questionRepository.questionFindByIdExample(exampleOptional.get().getId());
            example.setQuestions(questions);
        }

        return example;
    }

    @Override
    public Example exampleSave(Example example) {

        if (!example.getQuestions().isEmpty()) {
            questionRepository.saveAll(example.getQuestions());
        }

        return exampleRepository.saveExample(example);
    }


}
