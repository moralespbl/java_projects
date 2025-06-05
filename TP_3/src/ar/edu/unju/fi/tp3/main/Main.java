package ar.edu.unju.fi.tp3.main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unju.fi.tp3.model.Empleado;
import ar.edu.unju.fi.tp3.model.Administrativo;
import ar.edu.unju.fi.tp3.model.Profesional;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Empleado> empleados = new ArrayList<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
	    // 2. Agregar empleados
		try {
			// Administrativos
			empleados.add(new Administrativo(1, "afa10", "Lionel Messi", 3, LocalDate.of(1987, 6, 24), 20, 1));
			empleados.add(new Administrativo(2, "afa9", "Ángel Di María", 2, LocalDate.of(1988, 2, 14), 16, 2));
			empleados.add(new Administrativo(3, "afa22", "Lautaro Martínez", 1, LocalDate.of(1997, 8, 22), 5, 3));
			empleados.add(new Administrativo(4, "afa11", "Julián Álvarez", 0, LocalDate.of(2000, 1, 31), 1, 4));
			empleados.add(new Administrativo(5, "afa7", "Rodrigo De Paul", 0, LocalDate.of(1994, 5, 24), 6, 5));

			// Profesionales
			empleados.add(new Profesional(6, "afa1", "Emiliano Martínez", 2, LocalDate.of(1992, 9, 2), 4));
			empleados.add(new Profesional(7, "afa2", "Nahuel Molina", 0, LocalDate.of(1998, 4, 6), 3));
			empleados.add(new Profesional(8, "afa3", "Cristian Romero", 1, LocalDate.of(1998, 4, 27), 3));
			empleados.add(new Profesional(9, "afa4", "Nicolás Otamendi", 2, LocalDate.of(1988, 2, 12), 14));
			empleados.add(new Profesional(10, "afa8", "Enzo Fernández", 0, LocalDate.of(2001, 1, 17), 1));
		 } catch (IOException e) {
		        e.printStackTrace(); 
		 }
		
        // 3. Mostrar los empleados con antigüedad mayor a X años, al final de todo mostrar el total acumulado
		// de los remunerativos bonificables, salario, descuentos e importe neto.
		int x;
		 System.out.print("3: Obtener remunerativo de los empleados con una antigüedad mayor a: ");
		 try {
			x = sc.nextInt();
			mostrarPorAntiguedad(x);
			System.out.print("\nTotal Remunerativo: " + calcularTotalRemunerativosBonificables(x));
			System.out.print("\nTotal Salari Familiar: " + calcularTotalSalario(x));
			System.out.print("\nTotal Descuentos: " + calcularTotalDescuento(x));
			System.out.print("\nImporte Neto: " + calcularTotalImporteNetoAntiguedad(x));

		 
		 } catch (Exception e) {
			e.printStackTrace();
			System.out.print("Ingrese un entero");
		}
		//4. Mostrar los empleados cuya edad sea mayor o igual a un valor solicitado al usuario.
		 System.out.print("\n4: Mostrar a los empleados con una edad mayor a: ");
		 try {
			x = sc.nextInt();			
			filtrarPorEdad(x);				
			//System.out.print("\nTotal Remunerativos Bonificables: " + calcularTotalRemunerativosBonificables(x));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Ingrese un entero");
		}
		
     
		//5. Calcular el importe neto acumulado de todos los empleados cuya edad sea mayor o igual a un valor
		//solicitado al usuario.
		 System.out.print("\n5: Calcular el importe neto total de los empleados con una edad mayor a: ");
		 try {
			x = sc.nextInt();			
			System.out.print("Sueldo Neto: " + calcularTotalImporteNeto(x));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("Ingrese un entero");
		}		
		
		 //6. Incrementar en un 10% el salario básico a todos los empleados cuya antigüedad sea menor o igual a
		 //2 años
	
	        empleados.forEach(empleado -> {
	            if (empleado.getAntiguedad() <= 2) {
	                empleado.setSueldoBasico(empleado.getSueldoBasico()*1.1); 
	            }
	        });
		 
	        //empleados.forEach(empleado-> System.out.println(empleado));
	}
	
    public static void mostrarPorAntiguedad(int x) {
        empleados.stream()
                .filter(empleado -> empleado.getAntiguedad() > x)
                .forEach(empleado -> System.out.println(empleado));
    }
	
	public static double calcularTotalRemunerativosBonificables(int x) {
	    return empleados.stream()
	            .filter(empleado -> empleado.getAntiguedad() > x)
	            .mapToDouble(empleado -> {
	            	return empleado.calcularRemunerativo();
	            })
	            .sum();
	}
	
	public static double calcularTotalSalario(int x) {
	    return empleados.stream()
	            .filter(empleado -> empleado.getAntiguedad() > x)
	            .mapToDouble(empleado -> {
	            	return empleado.calcularSalarioFamiliar();
	            })
	            .sum();
	}
	
	public static double calcularTotalDescuento(int x) {
	    return empleados.stream()
	            .filter(empleado -> empleado.getAntiguedad() > x)
	            .mapToDouble(empleado -> {
	            	return empleado.calcularDescuentos();
	            })
	            .sum();
	}

	public static double calcularTotalImporteNetoAntiguedad(int x) {
	    return empleados.stream()
	            .filter(empleado -> empleado.getAntiguedad() > x)
	            .mapToDouble(empleado -> {
	            	return empleado.calcularSueldoNeto();
	            })
	            .sum();
	}
	
    public static void filtrarPorEdad(int x) {
        empleados.stream()
                .filter(empleado -> Period.between(empleado.getFechaNacimiento(), LocalDate.now()).getYears() >= x)
                .forEach(empleado -> System.out.println(empleado));
    }
    
    public static double calcularTotalImporteNeto(int x) {
    	return empleados.stream()
                .filter(empleado -> Period.between(empleado.getFechaNacimiento(), LocalDate.now()).getYears() >= x)
    			.mapToDouble(empleado -> {
    				return empleado.calcularSueldoNeto();
    			})
    			.sum();
    }
    

}


