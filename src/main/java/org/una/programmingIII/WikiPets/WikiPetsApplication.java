package org.una.programmingIII.WikiPets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class WikiPetsApplication {

    public static void main(String[] args) {
       // SpringApplication.run(WikiPetsApplication.class, args);
                Scanner scanner = new Scanner(System.in);
                Random random = new Random();

                // Generar un número aleatorio entre 1 y 100
                int numeroSecreto = random.nextInt(100) + 1;
                int intentos = 0;
                boolean adivinado = false;

                System.out.println("Bienvenido al juego de Adivina el Número!");
                System.out.println("He pensado en un número entre 1 y 100. ¡Intenta adivinarlo!");

                while (!adivinado) {
                    System.out.print("Introduce tu adivinanza: ");
                    int adivinanza = scanner.nextInt();
                    intentos++;

                    if (adivinanza < numeroSecreto) {
                        System.out.println("El número es mayor. Intenta de nuevo.");
                    } else if (adivinanza > numeroSecreto) {
                        System.out.println("El número es menor. Intenta de nuevo.");
                    } else {
                        adivinado = true;
                        System.out.println("¡Felicidades! Has adivinado el número en " + intentos + " intentos.");
                    }
                }

                scanner.close();
            }
        }

