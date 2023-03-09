package com.testing.mock.services;

import com.testing.mock.models.Example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ExampleServiceImplSpyTest {
    @Spy
    QuestionRepositoryImpl questionRepositorySpy;

    @Spy
    ExampleRepositoryImpl exampleRepositorySpy;

    @InjectMocks
    ExampleServiceImpl service; // Tipo De Una Clase Concreta

    @Test
    void spyExplorandoTest() {
        //Mockito.when(questionRepositorySpy.questionFindByIdExample(ArgumentMatchers.anyLong())).thenReturn(Datos.QUESTIONS);

        List<String> preguntas = Arrays.asList("ARIMETICA");
        Mockito.doReturn(preguntas).when(questionRepositorySpy).questionFindByIdExample(ArgumentMatchers.anyLong());

        Example example = service.findByIdExampleByNameAndQuestion("Example Matemáticas");

        assertEquals(5L, example.getId());
        assertEquals("Example Matemáticas", example.getName());
        assertEquals(1, example.getQuestions().size());
        assertTrue(example.getQuestions().contains("ARIMETICA"));

        Mockito.verify(exampleRepositorySpy).findAll();
        Mockito.verify(questionRepositorySpy).questionFindByIdExample(ArgumentMatchers.anyLong());
    }
}