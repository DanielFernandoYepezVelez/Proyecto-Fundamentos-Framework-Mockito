package com.testing.mock.repositories;

import com.testing.mock.models.Example;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 * ==== ESTO SE VA A CONVERTIR EN UN MOCK MÁS ADELANTE Y NO SE VA A NECESITAR ESTA CLASE ====
 * Esta es una implementación falsa, vamos a simular que se va a
 * comportar de cierta forma, para tener unos datos de prueba, pero,
 * esta clase no va a quedar operativa, por que nosotros en la vida real,
 * no vamos a tener acceso a este codigo de implementacion.
 * =============== Estos datos de prueba NO estan FUNCIONANDO ACTUALMENTE ====================
 * */


public class ExampleRpositoryImplOTROFALSO implements IExampleRepository {

    @Override
    public Example saveExample(Example example) {
        return null;
    }

    @Override
    public List<Example> findAll() {
        try {
            System.out.println("ESTO NUNCA VA A PASAR POR AQUI SIMULACIÓN");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
