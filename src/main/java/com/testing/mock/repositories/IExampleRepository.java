package com.testing.mock.repositories;

import com.testing.mock.models.Example;

import java.util.List;

/*
==== IMPLEMENTACIÓN DE ESTA INTERFACE ====
* Esta interface es un crontrato de implementación.
* Es Decir, Su implementación puede ser aplicada de cualquier forma,
* es decir, con jpa - hibernate, JDBC O Incluso puede ser
* un cliente http que se comunica a un servicio o una
* api rest que esta desplegado en otro servidor y
* ahi obtengamos la lista de examenes.
* MOCK SUPLANTA LA IMPLEMENTACIÓN DE ESTA INTERFACE CON DATOS
* DE PRUEBA - CON DATOS DE EJEMPLO Y SIMULAR EL COMPORTAMIENTO
* DE ESTA INTERFACE.
*/

public interface IExampleRepository {
    Example saveExample(Example example);
    List<Example> findAll();
}
