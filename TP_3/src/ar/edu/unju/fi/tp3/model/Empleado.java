package ar.edu.unju.fi.tp3.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public abstract class Empleado {
    private int id;
    private String legajo;
    private String nombre;
    private int cantidadHijos;
    private LocalDate fechaNacimiento;
    private int antiguedad;
    protected double sueldoBasico = 150000;
    protected static double PAGO_ANTIGUEDAD;
    protected static double PAGO_HIJOS;
    private static final String ARCHIVO_PAGOS = "src/ar/edu/unju/fi/tp3/main/resources/pagos_empleado.txt";
    
    public Empleado(int id, String legajo, String nombre, int cantidadHijos, LocalDate fechaNacimiento, int antiguedad) throws IOException {
        this.id = id;
        this.legajo = legajo;
        this.nombre = nombre;
        this.cantidadHijos = cantidadHijos;
        this.fechaNacimiento = fechaNacimiento;
        this.antiguedad = antiguedad;
        leerPagos();
    }
    
    private void leerPagos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_PAGOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                switch (partes[0].trim()) {
                    case "Antiguedad":
                        PAGO_ANTIGUEDAD = Double.parseDouble(partes[1].trim());
                        break;
                    case "Hijos":
                        PAGO_HIJOS = Double.parseDouble(partes[1].trim());
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de la excepción
            System.out.println("Error al leer el archivo de pagos por antigüedad e hijos");
        }
    }

    
    public abstract double calcularAdicional();

    public double calcularSueldoNeto() {
        double remunerativosBonificables = calcularRemunerativo();
        double salarioFamiliar = calcularSalarioFamiliar();
        double descuentos = calcularDescuentos();
        return remunerativosBonificables + salarioFamiliar - descuentos;
    }
    
    public double calcularRemunerativo() {
    	return getSueldoBasico() + calcularAdicional() + (PAGO_ANTIGUEDAD * antiguedad);    	
    }

    public double calcularSalarioFamiliar() {
    	return cantidadHijos * PAGO_HIJOS;   	
    }
    
    public double calcularDescuentos() {
    	return calcularRemunerativo() * 0.15;   	
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidadHijos() {
		return cantidadHijos;
	}

	public void setCantidadHijos(int cantidadHijos) {
		this.cantidadHijos = cantidadHijos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public static double getPAGO_ANTIGUEDAD() {
		return PAGO_ANTIGUEDAD;
	}

	public static void setPAGO_ANTIGUEDAD(double pAGO_ANTIGUEDAD) {
		PAGO_ANTIGUEDAD = pAGO_ANTIGUEDAD;
	}

	public static double getPAGO_HIJOS() {
		return PAGO_HIJOS;
	}

	public static void setPAGO_HIJOS(double pAGO_HIJOS) {
		PAGO_HIJOS = pAGO_HIJOS;
	}



	@Override
	public String toString() {
		return "[id=" + id + ", legajo=" + legajo + ", nombre=" + nombre + ", cantidadHijos=" + cantidadHijos
				+ ", fechaNacimiento=" + fechaNacimiento + ", antiguedad=" + antiguedad + ", sueldoBasico: "+ sueldoBasico +"]";
	}

	public double getSueldoBasico() {
		return sueldoBasico;
	}

	public void setSueldoBasico(double sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}
    
}

