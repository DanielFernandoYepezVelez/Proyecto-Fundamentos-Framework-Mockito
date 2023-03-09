package com.testing.mock;

import com.testing.mock.models.Example;

import java.util.Arrays;
import java.util.List;

public class Datos {
    public static final List<Example> EXAMPLES = Arrays.asList(
            new Example(5L, "Example Matemáticas"),
            new Example(6L, "Example Lenguaje"),
            new Example(7L, "Example Historia")
    );

    public static final List<Example> EXAMPLES_ID_NULL = Arrays.asList(
            new Example(null, "Example Matemáticas"),
            new Example(null, "Example Lenguaje"),
            new Example(null, "Example Historia")
    );

    public static final List<Example> EXAMPLES_ID_NEGATIVES = Arrays.asList(
            new Example(-3L, "Example Matemáticas"),
            new Example(-4L, "Example Lenguaje"),
            new Example(-6L, "Example Historia")
    );

    public static final List<String> QUESTIONS = Arrays.asList(
            "Aritmética", "Integrales", "Derivadas", "Trigonometría", "Geometría"
    );

    public final static Example EXAMPLE = new Example(null, "Example Fisica");

    public final static Example EXAMPLE_TWO = new Example(8L, "Example Fisica");
}
