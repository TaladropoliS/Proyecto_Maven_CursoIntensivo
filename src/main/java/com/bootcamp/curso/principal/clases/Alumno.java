package com.bootcamp.curso.principal.clases;

import java.util.ArrayList;


public class Alumno extends Persona {
    private Integer asistencia;
    private final ArrayList<Evaluacion> evaluaciones;

    public Alumno() {
        super();
        evaluaciones = new ArrayList<>();
        setAsistencia();
        setEvaluaciones();
    }

    public Integer getAsistencia() {
        return asistencia;
    }

    public void setAsistencia() {
        System.out.println("Ingrese la asistencia del alumno (entre 0 y 100): ");
        asistencia = leer.nextInt();
        leer.nextLine();
        if (asistencia <= 0 || asistencia > 100) {
            throw new IllegalArgumentException("La asistencia debe estar entre 0 y 100.");
        }
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones() {
        System.out.println("Ingrese la cantidad de evaluaciones (mínimo 3): ");
        Integer cant = leer.nextInt();
        leer.nextLine();
        if (cant < 3) {
            throw new IllegalArgumentException("La cantidad de evaluaciones debe ser mínimo 3.");
        }

        for (int i = 0; i < cant; i++) {
//            System.out.println("Ingrese Nota " + (i + 1) + ":");
            int nro = i + 1;
            Evaluacion e = new Evaluacion();
            e.setNota(nro);
            evaluaciones.add(e);
        }
    }
}
