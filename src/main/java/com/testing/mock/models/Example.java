package com.testing.mock.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Example {
    private Long id;
    private String name;
    private List<String> questions;

    public Example() {
    }

    public Example(Long id, String name) {
        this.id = id;
        this.name = name;
        this.questions = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Example example = (Example) o;
        return Objects.equals(id, example.id) && Objects.equals(name, example.name) && Objects.equals(questions, example.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, questions);
    }
}
