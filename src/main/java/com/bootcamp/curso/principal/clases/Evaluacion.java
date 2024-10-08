package com.bootcamp.curso.principal.clases;

import com.bootcamp.curso.principal.interfaces.IProyecto;


public class Evaluacion implements IProyecto {
    private Double nota;

    public Evaluacion() {
//        setNota();
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(int nro) {
        System.out.println("Ingrese Nota " + nro + ":");
        nota = leer.nextDouble();
        if (!validarNota(nota)) {
            throw new IllegalArgumentException("La nota debe estar entre 1 y 7.");
        }
    }

    public Boolean validarNota(Double n) {
        return n >= 1 && n <= 7;
    }
}
