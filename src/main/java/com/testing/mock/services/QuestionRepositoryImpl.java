package com.testing.mock.services;

import com.testing.mock.Datos;
import com.testing.mock.repositories.IQuestionRepository;

import java.util.List;

public class QuestionRepositoryImpl implements IQuestionRepository {
    @Override
    public void saveAll(List<String> questions) {
        System.out.println("Ejecución Del Método REAL QUESTIÓN SAVE");
    }

    @Override
    public List<String> questionFindByIdExample(Long id) {
        System.out.println("Ejecución Del Método REAL QUESTIONS By ID EXAMPLE");
        return Datos.QUESTIONS;
    }
}
