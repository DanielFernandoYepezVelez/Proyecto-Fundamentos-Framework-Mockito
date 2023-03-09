package com.testing.mock.services;

import com.testing.mock.Datos;
import com.testing.mock.models.Example;
import com.testing.mock.repositories.IExampleRepository;
import com.testing.mock.repositories.IQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class ExampleServiceImplTest {

    @Mock
    IQuestionRepository questionRepository;

    @Mock
    IExampleRepository exampleRepository;

    /* @Mock
    QuestionRepositoryImpl questionRepositoryImpl; Activar*/

    @InjectMocks
    ExampleServiceImpl service; // Tipo De Una Clase Concreta

    @Captor
    ArgumentCaptor<Long> captor;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this); Habilitamos El Uso De Annotaciones Para Los Mocks
        /* Los Objetos Que Necesito Para Que Interactuen En Mi Escenario (GLOBALES) SIMULADOS CON MOCKITO */
        //exampleRepository = Mockito.mock(IExampleRepository.class);
        //questionRepository = Mockito.mock(IQuestionRepository.class);
        //service = new ExampleServiceImpl(exampleRepository, questionRepository);
    }

    /* Simulando Un Repository Que No Tengo Acceso A Su Código O Implementación */
    @Test
    void findExampleByNameOneTest() {
        // Los Objetos Que Necesito Para Que Interactuen En Mi Escenario Con Mockito
        IExampleRepository exampleRepositoryLocal = Mockito.mock(IExampleRepository.class);
        IExampleService serviceLocal = new ExampleServiceImpl(exampleRepositoryLocal);

        // SOLO SON DATOS DE PRUEBA
        List<Example> data = Arrays.asList(
                new Example(5L, "Example Matemáticas"),
                new Example(6L, "Example Lenguaje"),
                new Example(7L, "Example Historia")
        );

        // CONTEXTO DE PRUEBA QUE ESTOY EJECUTANDO
        Mockito.when(exampleRepositoryLocal.findAll()).thenReturn(data);

        // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba
        Optional<Example> exampleLocal = serviceLocal.findExampleByName("Example Matemáticas");

        // Afirmamos El Test
        assertNotNull(exampleLocal);
        assertTrue(exampleLocal.isPresent());
        assertEquals(5L, exampleLocal.get().getId());
        assertEquals("Example Matemáticas", exampleLocal.get().getName());
    }

    @Test
    void findExampleByNameTwoTest() {
        /* Los Objetos Que Necesito Para Que Interactuen En Mi Escenario YA SON GLOBALES */

        // CONTEXTO DE PRUEBA QUE ESTOY EJECUTANDO
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES);

        // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba
        Optional<Example> example = service.findExampleByName("Example Matemáticas");

        /* Afirmamos El Test */
        assertTrue(example.isPresent());
        assertEquals(5L, example.orElseThrow(IllegalAccessError::new).getId());
        assertEquals("Example Matemáticas", example.get().getName());
    }

    @Test
    void findExampleByNameEmptyTest() {
        /* Los Objetos Que Necesito Para Que Interactuen En Mi Escenario YA SON GLOBALES */

        // SOLO ES UNA LISTA VACIA
        List<Example> data = Collections.emptyList();

        // CONTEXTO DE PRUEBA QUE ESTOY EJECUTANDO
        Mockito.when(exampleRepository.findAll()).thenReturn(data);

        // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba
        Optional<Example> example = service.findExampleByName("Example Matemáticas");

        /* Afirmamos El Test */
        assertFalse(example.isPresent());
    }

    @Test
    void findQuestionsExampleByIdTest() {
        /* Los Objetos De Mi Escenario Simulados O Reales YA SON GLOBALES */

        // CONTEXTO DE PRUEBA QUE ESTOY EJECUTANDO
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES);
        //Mockito.when(questionRepository.questionFindByIdExample(5L)).thenReturn(Datos.QUESTIONS);
        Mockito.when(questionRepository.questionFindByIdExample(ArgumentMatchers.anyLong())).thenReturn(Datos.QUESTIONS);

        // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba
        Example example = service.findByIdExampleByNameAndQuestion("Example Matemáticas");

        /* Afirmar El Test */
        assertEquals(5, example.getQuestions().size());
        assertTrue(example.getQuestions().contains("Aritmética"));
    }

    @Test
    void findQuestionsExampleByIdVerifyMethodTest() {
        /* Los Objetos De Mi Escenario Simulados O Reales YA SON GLOBALES */

        // CONTEXTO DE PRUEBA QUE ESTOY EJECUTANDO
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES);
        //Mockito.when(questionRepository.questionFindByIdExample(5L)).thenReturn(Datos.QUESTIONS);
        Mockito.when(questionRepository.questionFindByIdExample(ArgumentMatchers.anyLong())).thenReturn(Datos.QUESTIONS);

        // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba
        Example example = service.findByIdExampleByNameAndQuestion("Example Matemáticas");

        /* Afirmar El Test */
        assertEquals(5, example.getQuestions().size());
        assertTrue(example.getQuestions().contains("Aritmética"));

        /* Verificar Los Métodos Del Mock (Simulación) Se Ejecutaron Correctamente */
        Mockito.verify(exampleRepository).findAll();
        Mockito.verify(questionRepository).questionFindByIdExample(anyLong());
    }

    @Test
    void findExampleByIdNoExistVerifyTest() {
        /* Los Objetos De Mi Escenario Simulados O Reales YA SON GLOBALES */

        // CONTEXTO DE PRUEBA QUE ESTOY EJECUTANDO
        Mockito.when(exampleRepository.findAll()).thenReturn(Collections.emptyList());
        Mockito.when(questionRepository.questionFindByIdExample(ArgumentMatchers.anyLong())).thenReturn(Datos.QUESTIONS);

        // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba
        Example example = service.findByIdExampleByNameAndQuestion("Example Matemáticas");

        /* Afirmar El Test */
        assertNull(example);

        /* Verificar Los Métodos Del Mock (Simulación) Se Ejecutaron Correctamente */
        Mockito.verify(exampleRepository).findAll();
        Mockito.verify(questionRepository).questionFindByIdExample(5L);
    }

    @Test
    void questionsAndExampleSaveTest() {
        /* Los Objetos De Mi Escenario Simulados O Reales Algunos YA SON GLOBALES */
        Example newExample = new Example();

        // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba
        newExample.setQuestions(Datos.QUESTIONS);

        // CONTEXTO DE PRUEBA QUE ESTOY EJECUTANDO
        Mockito.when(exampleRepository.saveExample(ArgumentMatchers.any(Example.class))).thenReturn(Datos.EXAMPLE_TWO);

        // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba
        //Example example = service.exampleSave(Datos.EXAMPLE);
        Example example = service.exampleSave(newExample);

        /* Afirmar El Test */
        assertNotNull(example.getId());
        assertEquals(8L, example.getId());
        assertEquals("Example Fisica", example.getName());

        /* Verificar Los Métodos Del Mock (Simulación) Se Ejecutaron Correctamente */
        Mockito.verify(exampleRepository).saveExample(ArgumentMatchers.any(Example.class));
        Mockito.verify(questionRepository).saveAll(ArgumentMatchers.anyList());
    }

    @Test
    void questionsAndExampleSaveIdIncrementalTest() {
        // GIVEN (DADO EL CONTEXTO DE PRUEBA) BDD
            /* Los Objetos De Mi Escenario Simulados O Reales Algunos YA SON GLOBALES TDD */
        Example newExample = new Example();

            // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba TDD
        newExample.setQuestions(Datos.QUESTIONS);

        // CONTEXTO DE PRUEBA QUE ESTOY EJECUTANDO
        Mockito.when(exampleRepository.saveExample(ArgumentMatchers.any(Example.class))).then(new Answer<Example>() {
            Long secuence = 8L;

            @Override
            public Example answer(InvocationOnMock invocationOnMock) throws Throwable {
                Example example = invocationOnMock.getArgument(0);
                example.setId(secuence++);
                example.setName(Datos.EXAMPLE.getName());
                return example;
            }
        });

        // WHEN (INVOCAMOS LOS MÉTODOS A EJECUTAR) BDD
            // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba
        //Example example = service.exampleSave(Datos.EXAMPLE);
        Example example = service.exampleSave(newExample);

        // THEN (VALIDADOS SI PASA O NO LA PRUEBA) BDD
            /* Afirmar El Test */
        assertNotNull(example.getId());
        assertEquals(8L, example.getId());
        assertEquals("Example Fisica", example.getName());

            /* Verificar Los Métodos Del Mock (Simulación) Se Ejecutaron Correctamente */
        Mockito.verify(exampleRepository).saveExample(ArgumentMatchers.any(Example.class));
        Mockito.verify(questionRepository).saveAll(ArgumentMatchers.anyList());
    }

    @Test
    void exampleExceptionsTest() {
        // GIVEN (DADO EL CONTEXTO DE PRUEBA) BDD
            /* Los Objetos De Mi Escenario Simulados O Reales Algunos YA SON GLOBALES TDD */
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES);
        Mockito.when(questionRepository.questionFindByIdExample(ArgumentMatchers.anyLong())).thenThrow(IllegalArgumentException.class);

        // THEN (VALIDADOS SI PASA O NO LA PRUEBA) BDD
            /* Afirmar El Test */
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.findByIdExampleByNameAndQuestion("Example Matemáticas");
        });

        // THEN (VALIDADOS SI PASA O NO LA PRUEBA) BDD
            /* Afirmar El Test */
        assertEquals(IllegalArgumentException.class, exception.getClass());

        /* Verificar Los Métodos Del Mock (Simulación) Se Ejecutaron Correctamente */
        Mockito.verify(exampleRepository).findAll();
        Mockito.verify(questionRepository).questionFindByIdExample(anyLong());
    }

    @Test
    void exampleTwoExceptionsTest() {
        // GIVEN (DADO EL CONTEXTO DE PRUEBA) BDD
        /* Los Objetos De Mi Escenario Simulados O Reales Algunos YA SON GLOBALES TDD */
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES_ID_NULL);
        Mockito.when(questionRepository.questionFindByIdExample(ArgumentMatchers.isNull())).thenThrow(
                IllegalArgumentException.class);

        // THEN (VALIDADOS SI PASA O NO LA PRUEBA) BDD
        /* Afirmar El Test */
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.findByIdExampleByNameAndQuestion("Example Matemáticas");
        });

        // THEN (VALIDADOS SI PASA O NO LA PRUEBA) BDD
        /* Afirmar El Test */
        assertEquals(IllegalArgumentException.class, exception.getClass());

        /* Verificar Los Métodos Del Mock (Simulación) Se Ejecutaron Correctamente */
        Mockito.verify(exampleRepository).findAll();
        Mockito.verify(questionRepository).questionFindByIdExample(ArgumentMatchers.isNull());
    }

    @Test
    void argumentMatchersTest() {
        /* GIVEN */
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES);
        Mockito.when(questionRepository.questionFindByIdExample(ArgumentMatchers.anyLong())).thenReturn(Datos.QUESTIONS);

        /* WHEN */
        service.findByIdExampleByNameAndQuestion("Example Matemáticas");

        /* THEN */
        Mockito.verify(exampleRepository).findAll();
        //Mockito.verify(questionRepository).questionFindByIdExample(ArgumentMatchers.eq(5L));
        //Mockito.verify(questionRepository).questionFindByIdExample(ArgumentMatchers.argThat(aLong -> aLong != null && aLong.equals(5L)));
        Mockito.verify(questionRepository).questionFindByIdExample(ArgumentMatchers.argThat(aLong -> aLong != null && aLong >= 5L));
    }

    @Test
    void argumentMatchersTwoTest() {
        /* GIVEN */
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES_ID_NEGATIVES);
        Mockito.when(questionRepository.questionFindByIdExample(ArgumentMatchers.anyLong())).thenReturn(Datos.QUESTIONS);

        /* WHEN */
        service.findByIdExampleByNameAndQuestion("Example Matemáticas");

        /* THEN */
        Mockito.verify(exampleRepository).findAll();
        Mockito.verify(questionRepository).questionFindByIdExample(ArgumentMatchers.argThat(new MyArgsMatchers()));
    }

    public static class MyArgsMatchers implements ArgumentMatcher<Long> {
        private Long aLong;

        @Override
        public boolean matches(Long aLong) {
            this.aLong = aLong;
            return aLong != null && aLong > 0;
        }

        // Mensaje de un error en el argumento del verify
        @Override
        public String toString() {
            return "Es un mensaje personalizado error para un argumento negativo: " + aLong;
        }
    }

    @Test
    void argumentCaptorTest() {
        /* GIVEN */
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES);

        /* WHEN */
        service.findByIdExampleByNameAndQuestion("Example Matemáticas");

        /* THEN */
        //ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(questionRepository).questionFindByIdExample(captor.capture());
        assertEquals(5L, captor.getValue());
    }

    @Test
    void voidDoThrowTest() {
        /* GIVEN */
        Example example = Datos.EXAMPLE;
        example.setQuestions(Datos.QUESTIONS);

        //Mockito.when(questionRepository.saveAll(ArgumentMatchers.anyList())).thenThrow(IllegalArgumentException.class);
        Mockito.doThrow(IllegalArgumentException.class).when(questionRepository).saveAll(ArgumentMatchers.anyList());

        /* WHEN */
        assertThrows(IllegalArgumentException.class, () -> {
           service.exampleSave(example);
        });

        /* THEN */
    }

    @Test
    void doAnswerTest() {
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES);
        //Mockito.when(questionRepository.questionFindByIdExample(ArgumentMatchers.anyLong())).thenReturn(Datos.QUESTIONS);

        Mockito.doAnswer(invocationOnMock -> {
            // Entras Por El Argumento En La Posición 0
           Long id = invocationOnMock.getArgument(0);
           return id == 5L ? Datos.QUESTIONS : Collections.emptyList();
        }).when(questionRepository).questionFindByIdExample(anyLong());

        Example example = service.findByIdExampleByNameAndQuestion("Example Matemáticas");

        assertEquals(5L, example.getId());
        assertTrue(example.getQuestions().contains("Geometría"));
        assertEquals("Example Matemáticas", example.getName());
        assertEquals(5, example.getQuestions().size());

        Mockito.verify(questionRepository).questionFindByIdExample(ArgumentMatchers.anyLong());
    }

    @Test
    void doAnswerQuestionsAndExampleSaveIdIncrementalTest() {
        // GIVEN (DADO EL CONTEXTO DE PRUEBA) BDD
        /* Los Objetos De Mi Escenario Simulados O Reales Algunos YA SON GLOBALES TDD */
        Example newExample = new Example();

        // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba TDD
        newExample.setQuestions(Datos.QUESTIONS);

        // CONTEXTO DE PRUEBA QUE ESTOY EJECUTANDO
        Mockito.doAnswer(new Answer<Example>() {
            Long secuence = 8L;

            @Override
            public Example answer(InvocationOnMock invocationOnMock) throws Throwable {
                Example example = invocationOnMock.getArgument(0);
                example.setId(secuence++);
                example.setName(Datos.EXAMPLE.getName());
                return example;
            }
        }).when(exampleRepository).saveExample(ArgumentMatchers.any(Example.class));

        // WHEN (INVOCAMOS LOS MÉTODOS A EJECUTAR) BDD
        // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba
        //Example example = service.exampleSave(Datos.EXAMPLE);
        Example example = service.exampleSave(newExample);

        // THEN (VALIDADOS SI PASA O NO LA PRUEBA) BDD
        /* Afirmar El Test */
        assertNotNull(example.getId());
        assertEquals(8L, example.getId());
        assertEquals("Example Fisica", example.getName());

        /* Verificar Los Métodos Del Mock (Simulación) Se Ejecutaron Correctamente */
        Mockito.verify(exampleRepository).saveExample(ArgumentMatchers.any(Example.class));
        Mockito.verify(questionRepository).saveAll(ArgumentMatchers.anyList());
    }

    @Test
    @Disabled
    void doCallRealMethodTest() {
        // GIVEN (DADO EL CONTEXTO DE PRUEBA) BDD
        /* Los Objetos De Mi Escenario Simulados O Reales Algunos YA SON GLOBALES TDD */
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES);
        //Mockito.when(questionRepository.questionFindByIdExample(ArgumentMatchers.anyLong())).thenReturn(Datos.QUESTIONS);

        // Metodo Real
        // MALO - ERROR Mockito.doCallRealMethod().when(questionRepository).questionFindByIdExample(ArgumentMatchers.anyLong());
        //Mockito.doCallRealMethod().when(questionRepositoryImpl).questionFindByIdExample(ArgumentMatchers.anyLong()); Activar

        // WHEN (INVOCAMOS LOS MÉTODOS A EJECUTAR) BDD
        // Invocamos Los Métodos Que Se Necesitan Para Ejecutar Mi Contexto De Prueba
        Example exampleQuestions = service.findByIdExampleByNameAndQuestion("Example Matemáticas");

        // Assertions
        assertEquals(5L, exampleQuestions.getId());
        assertEquals("Example Matemáticas", exampleQuestions.getName());

        /* Verificar Los Métodos Del Mock (Simulación) Se Ejecutaron Correctamente */
        Mockito.verify(exampleRepository).findAll();
       // Mockito.verify(questionRepositoryImpl).questionFindByIdExample(ArgumentMatchers.anyLong()); Activar
    }

    @Test
    void spyExplorandoTest() {
        /* Trabajando Con Los Spy De Forma Local */
        IExampleRepository exampleRepositorySpy = Mockito.spy(ExampleRepositoryImpl.class);
        IQuestionRepository questionRepositorySpy = Mockito.spy(QuestionRepositoryImpl.class);
        IExampleService exampleServiceSpy = new ExampleServiceImpl(exampleRepositorySpy, questionRepositorySpy);
        //Mockito.when(questionRepositorySpy.questionFindByIdExample(ArgumentMatchers.anyLong())).thenReturn(Datos.QUESTIONS);

        List<String> preguntas = Arrays.asList("ARIMETICA");
        Mockito.doReturn(preguntas).when(questionRepositorySpy).questionFindByIdExample(ArgumentMatchers.anyLong());
        Example example = exampleServiceSpy.findByIdExampleByNameAndQuestion("Example Matemáticas");

        assertEquals(5L, example.getId());
        assertEquals("Example Matemáticas", example.getName());
        assertEquals(1, example.getQuestions().size());
        assertTrue(example.getQuestions().contains("ARIMETICA"));

        Mockito.verify(exampleRepositorySpy).findAll();
        Mockito.verify(questionRepositorySpy).questionFindByIdExample(ArgumentMatchers.anyLong());
    }

    @Test
    void ordenInvocacionesVerifyTest() {
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES);

        service.findByIdExampleByNameAndQuestion("Example Matemáticas");
        service.findByIdExampleByNameAndQuestion("Example Lenguaje");

        InOrder order = Mockito.inOrder(questionRepository);

        order.verify(questionRepository).questionFindByIdExample(5L);
        order.verify(questionRepository).questionFindByIdExample(6L);
    }

    @Test
    void ordenInvocacionesVerify2Test() {
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES);

        service.findByIdExampleByNameAndQuestion("Example Matemáticas");
        service.findByIdExampleByNameAndQuestion("Example Lenguaje");

        InOrder order = Mockito.inOrder(exampleRepository, questionRepository);

        // El Orden Aqui, Debe Ser Igual, Al Orden De Invocación En Los Services
        order.verify(exampleRepository).findAll();
        order.verify(questionRepository).questionFindByIdExample(5L);

        order.verify(exampleRepository).findAll();
        order.verify(questionRepository).questionFindByIdExample(6L);
    }

    @Test
    void numberInvocationTest() {
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES);

        service.findByIdExampleByNameAndQuestion("Example Matemáticas");

        Mockito.verify(questionRepository).questionFindByIdExample(5L);
        Mockito.verify(questionRepository, Mockito.times(1)).questionFindByIdExample(5L);
        Mockito.verify(questionRepository, Mockito.atLeast(1)).questionFindByIdExample(5L);
        Mockito.verify(questionRepository, Mockito.atLeastOnce()).questionFindByIdExample(5L);
        Mockito.verify(questionRepository, Mockito.atMost(10)).questionFindByIdExample(5L);
        Mockito.verify(questionRepository, Mockito.atMostOnce()).questionFindByIdExample(5L);
    }

    @Test
    void numberInvocation2Test() {
        Mockito.when(exampleRepository.findAll()).thenReturn(Datos.EXAMPLES);

        service.findByIdExampleByNameAndQuestion("Example Matemáticas");

        //Mockito.verify(questionRepository).questionFindByIdExample(5L); falla
        Mockito.verify(questionRepository, Mockito.times(2)).questionFindByIdExample(5L);
        Mockito.verify(questionRepository, Mockito.atLeast(1)).questionFindByIdExample(5L);
        Mockito.verify(questionRepository, Mockito.atLeastOnce()).questionFindByIdExample(5L);
        Mockito.verify(questionRepository, Mockito.atMost(20)).questionFindByIdExample(5L);
        //Mockito.verify(questionRepository, Mockito.atMostOnce()).questionFindByIdExample(5L); falla
    }

    @Test
    void numberInvocation3Test() {
        Mockito.when(exampleRepository.findAll()).thenReturn(Collections.emptyList());

        service.findByIdExampleByNameAndQuestion("Example Matemática");

        Mockito.verify(questionRepository, Mockito.never()).questionFindByIdExample(5L);
        Mockito.verifyNoInteractions(questionRepository);

        Mockito.verify(exampleRepository).findAll();
        Mockito.verify(exampleRepository, Mockito.times(1)).findAll();
        Mockito.verify(exampleRepository, Mockito.atLeast(1)).findAll();
        Mockito.verify(exampleRepository, Mockito.atLeastOnce()).findAll();
        Mockito.verify(exampleRepository, Mockito.atMost(1)).findAll();
        Mockito.verify(exampleRepository, Mockito.atMostOnce()).findAll();
    }
}