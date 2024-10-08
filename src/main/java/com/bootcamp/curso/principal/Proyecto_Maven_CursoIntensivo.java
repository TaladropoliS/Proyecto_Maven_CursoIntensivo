package com.bootcamp.curso.principal;

import com.bootcamp.curso.principal.clases.*;
import com.bootcamp.curso.principal.interfaces.IProyecto;

import java.util.ArrayList;

public class Proyecto_Maven_CursoIntensivo implements IProyecto {

    public static ArrayList<CursoIntensivo> curso = new ArrayList<CursoIntensivo>();

    public static void main(String[] args) {
        int op = 0;
        try {
            do {
                System.out.println("MENU - CURSOS INTENSIVOS");
                System.out.println("------------------------");
                System.out.println("1) Ingresar un nuevo curso intensivo.");
                System.out.println("2) Mostrar todos los relatores con sus sueldos.");
                System.out.println("3) Mostrar la SF de cada alumno de un curso especifico.");
                System.out.println("4) Buscar un alumno por RUN en un curso especifico.");
                System.out.println("5) Eliminar un alumno por RUN en un curso especifico.");
                System.out.println("6) Exportar toda la informacion a un fichero.");
                System.out.println("7) Salir.");
                System.out.println("------------------------");
                System.out.println("Ingrese su opcion: ");
                op = leer.nextInt();
                leer.nextLine();
                switch (op) {
                    case 1:
                        CursoIntensivo c = new CursoIntensivo();
                        Proyecto_Maven_CursoIntensivo.curso.add(c);
                        break;
                    case 2:
                        for (CursoIntensivo cursoIntensivo : curso) {
                            System.out.println("Relator: " + cursoIntensivo.getRelator().getNombre() + " - Sueldo: " + cursoIntensivo.getRelator().getSueldo());
                        }
                        break;
                    case 3:
                        for (CursoIntensivo cursoIntensivo : curso) {
                            System.out.println("Codigo: " + cursoIntensivo.getCodigo() + " - Nombre: " + cursoIntensivo.getNombre());
                        }
                        System.out.println("Ingrese el codigo del curso para mostrar SF: ");
                        int cod = leer.nextInt();
                        CursoIntensivo.mostrarSF(cod);
                        leer.nextLine();
                        break;
                    case 4:
                        System.out.println("Ingrese el RUN del alumno a buscar: ");
                        String run = leer.nextLine();
                        CursoIntensivo.buscarAlumno(run);
                        break;
                    case 5:
//                        Eliminar un alumno por RUN en un curso especifico
                        System.out.println("Ingrese el codigo del curso para eliminar un alumno: ");
                        int codCurso = leer.nextInt();
                        leer.nextLine();
                        System.out.println("Ingrese el RUN del alumno a eliminar: ");
                        String runAlumno = leer.nextLine();
                        CursoIntensivo.eliminarAlumno(codCurso, runAlumno);

                        break;
                    case 6:
                        CursoIntensivo.exportarInformacionAExcel();
                        break;
                    case 7:
                        System.out.println("Finalizando el algoritmo.");
                        break;
                    default:
                        System.err.println("Opcion inválida.");
                        break;
                }
            } while (op < 7);
        } catch (Exception e) {
            System.err.println("Error de la aplicacion: " + e.getMessage());
        }
    }
}
