package ar.edu.unju.fi.tp3.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class Profesional extends Empleado {
    private static final String ARCHIVO_ADICIONAL_PROFESIONALES = "src/ar/edu/unju/fi/tp3/main/resources/adicional_profesionales.txt";
    private double adicionalPorTitulo;

    public Profesional(int id, String legajo, String nombre, int cantidadHijos, LocalDate fechaNacimiento, int antiguedad) throws IOException {
        super(id, legajo, nombre, cantidadHijos, fechaNacimiento, antiguedad);
        this.adicionalPorTitulo = leerAdicional();
    }

    @Override
	public String toString() {
		return "Profesional :" + super.toString() ;
	}

	private double leerAdicional() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_ADICIONAL_PROFESIONALES))) {
            return Double.parseDouble(reader.readLine().trim());
        }
    }

    @Override
    public double calcularAdicional() {
        return adicionalPorTitulo;
    }
}

