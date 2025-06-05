package ar.edu.unju.fi.pto1.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Integer> lista = new ArrayList<>();
		List<Integer> repetidos = new ArrayList<>();
		List<Integer> impar = new ArrayList<>();
		List<Integer> par = new ArrayList<>();

		int i;
		int n;
		System.out.println("Ingrese la cantidad de numeros a generar:");
		i=validarEntero();
		Random random = new Random(); 
		do {
			n = random.nextInt(101); // Genera un número aleatorio entre 0 y 100

			if (lista.isEmpty()) {
				lista.add(n);
				i--;
			}
			else {
				if(!lista.contains(n)) {
					lista.add(n);
					i--;
				}
			}
		}while(i>0);	

        for (i = 0; i < lista.size(); i++) {
            System.out.println("Elemento en la posición " + i + ": " + lista.get(i));
        }
        
        //a
		System.out.println("a:");
		System.out.println("Ingrese multiplo a eliminar:");
		n = validarEntero();
		final int nCopia = n;  // copia que será usada en la lambda
        lista = lista.stream()
        		.filter(num -> num % nCopia != 0)
        		.collect(Collectors.toList());
        lista.forEach(System.out::println);
        
        //b
		System.out.println("b:");
		int max = Collections.max(lista);
		lista.replaceAll(num -> num == max ? num * num : num);
        lista.forEach(System.out::println);
		
        //c
		System.out.println("c:");
		lista.replaceAll(num -> num < 5 ? num + factorial(num):num);
        lista.forEach(System.out::println);
        
        //d
		System.out.println("d:");
        Map<Object, Long> frecuencias = lista.stream()
                .collect(Collectors.groupingBy(num -> num, Collectors.counting()));
        
        Optional<Entry<Object,Long>> minEntry = frecuencias.entrySet().stream()
                .filter(entry -> entry.getValue() > 1) 
                .min(Map.Entry.comparingByValue());
        
        if (minEntry.isPresent()) {
            System.out.println("El número que se repite menos veces es: " + minEntry.get().getKey() +
                    " con " + minEntry.get().getValue() + " repeticiones.");
        } else {
            System.out.println("No hay números repetidos en la lista.");
        }
        
        //e
		System.out.println("e:"); 

        List<Integer> pares = lista.stream()
                                           .filter(num -> num % 2 == 0)
                                           .collect(Collectors.toList());

        List<Integer> impares = lista.stream()
                                          .filter(num -> num % 2 != 0)
                                          .collect(Collectors.toList());
        pares.forEach(System.out::println);
        impares.forEach(System.out::println);
	}

	public static int validarEntero() {
		int num=0;
		boolean bandera;
		do {
			bandera = true;
			try {
				num= sc.nextInt();
			}
			catch(InputMismatchException e ) {
				System.out.println("Error, ingrese un valor entero:");
				bandera = false;
			}
		    sc.nextLine();
			}while(bandera == false);
		return num;
	}
	
	public static int factorial(int n) {
	    int k = 1;
	    int i;
	    for (i = 1; i <= n; i++) {
	        k *= i;
	    }
	    return k;	
	}
}

