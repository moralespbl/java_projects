package ar.edu.unju.fi.tp3.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class Administrativo extends Empleado {
    private static final String ARCHIVO_CATEGORIAS = "src/ar/edu/unju/fi/tp3/main/resources/categorias_administrativos.txt";
    private int categoria;

    public Administrativo(int id, String legajo, String nombre, int cantidadHijos, LocalDate fechaNacimiento, int antiguedad, int categoria) throws IOException {
        super(id, legajo, nombre, cantidadHijos, fechaNacimiento, antiguedad);
        this.categoria = categoria;
    }


	private double leerAdicionalPorCategoria(int categoria) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_CATEGORIAS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                int categoriaLeida = Integer.parseInt(partes[0].trim());
                if (categoriaLeida == categoria) {
                    return Double.parseDouble(partes[1].trim());
                }
            }
        }
        return 0; // en caso de que la categoría no se encuentre
    }

    @Override
    public double calcularAdicional() {
        try {
            return leerAdicionalPorCategoria(this.categoria);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }


	@Override
	public String toString() {
		return "Administrativo: " + super.toString() + " [categoria=" + categoria + "]";
	}
}



