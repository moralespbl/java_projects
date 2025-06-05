package ar.edu.unju.fi.pto1.tradicional;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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


        
        // a
		System.out.println("Ingrese multiplo a eliminar:");
		n = validarEntero();
		
        for (i = 0; i < lista.size(); i++) {
            if(lista.get(i) % n == 0 ) {
            	lista.remove(i);
            	i--;
            }
        }

        
        //b
        n=lista.get(0);
        int j = 0;
        for (i = 0; i < lista.size(); i++) {
            if(lista.get(i) > n ) {
            	n = lista.get(i);
            	j = i;
            }
        }
        lista.set(j, (n*n));
        
        
        //c
        j = 0;
        for (i = 0; i < lista.size(); i++) {
            if(lista.get(i) < 5 ) {
            	n = lista.get(i);
            	for (j = 1; j < lista.get(i); j++) {
            		n=n*j;
            	}
            	lista.set(i, n + lista.get(i));
            }
        }
        
        //d
        n=0; // conteo de números
      
        for (i = 0; i < lista.size(); i++) {
            for ( j = 0; j < lista.size(); j++) {
            	if ( lista.get(i)==lista.get(j)) {
            		n++;
            	}	
            }
            repetidos.add(n);	
            n=0;
        }
        
        j=2;
        boolean bandera = true;
        while (j < lista.size() && bandera == true) {    
	        for(i=0 ; i<repetidos.size() ; i++) {
	        	if (repetidos.get(i)==j) {
	        		System.out.println("El numero que se repite " + j + " veces es: " + lista.get(i));
	        		bandera = false;
	        	}
	        }
	        j++;
	    } 
        if(bandera == true) {
        	System.out.println("No hay valores repetidos");
        }
        
        //e
        
        for (i = 0; i < lista.size(); i++) {
            if(lista.get(i) % 2 == 0 ) {
            	par.add(lista.get(i));
            }
            else
            	impar.add(lista.get(i));
        }
        // System.out.println(par);
        // System.out.println(impar);
 
        
        //f
        for (i = 0; i < lista.size(); i++) {
            System.out.println("Elemento en la posición " + i + ": " + lista.get(i));
        }
        

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
}