package com.bootcamp.curso.principal.clases;

import com.bootcamp.curso.principal.Proyecto_Maven_CursoIntensivo;
import com.bootcamp.curso.principal.interfaces.IProyecto;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class CursoIntensivo implements IProyecto {
    private Integer codigo, asistenciaMinima;
    private String nombre;
    private Relator relator;
    private ArrayList<Alumno> curso;

    public CursoIntensivo() {
        curso = new ArrayList<>();
        setCodigo();
        setNombre();
        setAsistenciaMinima();
        setRelator();
        setCurso();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo() {
        System.out.println("Ingrese el codigo del curso (N° entero): ");
        codigo = leer.nextInt();
        leer.nextLine();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre() {
        System.out.println("Ingrese el nombre del Curso: ");
        nombre = leer.nextLine();
    }

    public Relator getRelator() {
        return relator;
    }

    public void setRelator() {
        System.out.println("-------------------------------");
        System.out.println("Ingrese los datos del Relator: ");
        relator = new Relator();
    }

    public Integer getAsistenciaMinima() {
        return asistenciaMinima;
    }

    public void setAsistenciaMinima() {
        System.out.println("Ingrese la asistencia minima del curso: ");
        asistenciaMinima = leer.nextInt();
        leer.nextLine();
        if (asistenciaMinima <= 0 || asistenciaMinima > 100) {
            throw new IllegalArgumentException("La asistencia mínima debe estar entre 0 y 100.");
        }
    }

    public ArrayList<Alumno> getCurso() {
        return curso;
    }

    public void setCurso() {
        System.out.println("-------------------------------");
        System.out.println("Ingrese los datos del Alumnos: ");
        Alumno a = new Alumno();
        curso.add(a);
    }

    public static void mostrarSF(int cod) {
        Optional<CursoIntensivo> cursoOpt = Proyecto_Maven_CursoIntensivo.curso.stream()
                .filter(c -> c.getCodigo().equals(cod))
                .findFirst();

        if (cursoOpt.isPresent()) {
            CursoIntensivo cursoIntensivo = cursoOpt.get();

            if (!cursoIntensivo.getCurso().isEmpty()) {
                System.out.println("Situación final de los alumnos en el curso: " + cursoIntensivo.getNombre());
                cursoIntensivo.getCurso().forEach(alumno -> {
                    double promedio = calcularPromedio(alumno);
                    int asistencia = alumno.getAsistencia();
                    int asistenciaMinima = cursoIntensivo.getAsistenciaMinima();
                    String estado;

                    if (promedio >= 4.0 && asistencia >= asistenciaMinima) {
                        estado = "SF: AA";
                    } else if (promedio >= 4.0) {
                        estado = "SF: RI";
                    } else if (promedio < 4.0 && asistencia >= asistenciaMinima) {
                        estado = "SF: RN";
                    } else {
                        estado = "SF: RR";
                    }
                    System.out.println("Alumno: " + alumno.getNombre() + " - Estado: " + estado);
                });
            } else {
                System.out.println("El curso no tiene alumnos asignados.");
            }
        } else {
            System.out.println("Curso no encontrado con el código: " + cod);
        }
    }

    private static double calcularPromedio(Alumno alumno) {
        ArrayList<Evaluacion> evaluaciones = alumno.getEvaluaciones();
        double suma = evaluaciones.stream()
                .mapToDouble(Evaluacion::getNota)
                .sum();
        return !evaluaciones.isEmpty() ? suma / evaluaciones.size() : 0;
    }

    public static void buscarAlumno(String run) {
        Optional<Alumno> alumnoOpt = Proyecto_Maven_CursoIntensivo.curso.stream()
                .flatMap(c -> c.getCurso().stream())
                .filter(a -> a.getRun().equals(run))
                .findFirst();

        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get();
            System.out.println("Alumno encontrado: " + alumno.getNombre());
        } else {
            System.out.println("Alumno no encontrado con el RUN: " + run);
        }
    }

    public static void eliminarAlumno(int codCurso, String runAlumno) {
        Optional<CursoIntensivo> cursoOpt = Proyecto_Maven_CursoIntensivo.curso.stream()
                .filter(c -> c.getCodigo().equals(codCurso))
                .findFirst();

        if (cursoOpt.isPresent()) {
            CursoIntensivo cursoIntensivo = cursoOpt.get();
            Optional<Alumno> alumnoOpt = cursoIntensivo.getCurso().stream()
                    .filter(a -> a.getRun().equals(runAlumno))
                    .findFirst();

            if (alumnoOpt.isPresent()) {
                Alumno alumno = alumnoOpt.get();
                cursoIntensivo.getCurso().remove(alumno);
                System.out.println("Alumno eliminado: " + alumno.getNombre());
            } else {
                System.out.println("Alumno no encontrado con el RUN: " + runAlumno);
            }
        } else {
            System.out.println("Curso no encontrado con el código: " + codCurso);
        }
    }

    public static void exportarInformacion() {
        String dest = "informacion_cursos.pdf";
        try {
            PdfWriter writer = new PdfWriter(dest);

            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Informe de Cursos y Alumnos"));

            for (CursoIntensivo curso : Proyecto_Maven_CursoIntensivo.curso) {
                document.add(new Paragraph("Curso: " + curso.getNombre() + " (Código: " + curso.getCodigo() + ")"));
                document.add(new Paragraph("Relator: " + curso.getRelator().getNombre()));

                Table table = new Table(3);
                table.addCell("Nombre del Alumno");
                table.addCell("Promedio");
                table.addCell("Estado");

                for (Alumno alumno : curso.getCurso()) {
                    double promedio = calcularPromedio(alumno);
                    int asistencia = alumno.getAsistencia();
                    int asistenciaMinima = curso.getAsistenciaMinima();
                    String estado;

                    if (promedio >= 4.0 && asistencia >= asistenciaMinima) {
                        estado = "SF: AA";
                    } else if (promedio >= 4.0) {
                        estado = "SF: RI";
                    } else if (promedio < 4.0 && asistencia >= asistenciaMinima) {
                        estado = "SF: RN";
                    } else {
                        estado = "SF: RR";
                    }

                    table.addCell(alumno.getNombre());
                    table.addCell(String.valueOf(promedio));
                    table.addCell(estado);
                }

                document.add(table);

                document.add(new Paragraph("\n"));
            }

            document.close();
            System.out.println("PDF generado exitosamente en: " + dest);
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
