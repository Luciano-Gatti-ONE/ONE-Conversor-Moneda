package com.oneproyect.conversormonedas;

import com.oneproyect.conversormonedas.classes.ExchangeRateResponse;
import com.oneproyect.conversormonedas.service.CurrencyService;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Aplicación de consola para convertir montos entre diferentes monedas.
 * Usa una clase de servicio para obtener tasas de cambio desde una API externa
 * y realizar conversiones entre USD, ARS, BRL y COP mediante un menú interactivo.
 *
 * @author Luciano Emmanuel Gatti Flekenstein
 */

public class CurrencyConverter {

    public static void main(String[] args) {
        // Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Instancia del servicio que se encarga de obtener tasas y hacer conversiones
        CurrencyService service = new CurrencyService();

        // Obtener las tasas de cambio usando USD como base
        ExchangeRateResponse tasas = service.obtenerTasas("USD");

        // Validar si la respuesta fue exitosa
        if (tasas == null || !"success".equalsIgnoreCase(tasas.getResult())) {
            System.out.println("No se pudieron obtener las tasas de cambio. Saliendo...");
            return;
        }

        // Variable para controlar la opción seleccionada por el usuario
        int opcion = 0;
        System.out.println("*** Sea bienvenido/a al Conversor de Moneda =] ***\n");

        // Bucle principal del menú
        while (opcion != 13) {
            mostrarMenu(); // Mostrar opciones

            try {
                // Leer opción seleccionada
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer del scanner

                // Validar la opción
                if (opcion < 1 || opcion > 13) {
                    System.out.println("Opción inválida.");
                } else if (opcion != 13) {
                    // Realizar la conversión según la opción elegida
                    manejarConversion(opcion, tasas, service, scanner);
                } else {
                    // Salir
                    System.out.println("Gracias por usar el conversor. ¡Hasta luego!");
                }

            } catch (Exception e) {
                // Manejo de errores si se ingresa algo que no es un número
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Limpiar buffer
            }
        }

        // Cerrar el scanner al finalizar
        scanner.close();
    }

    /**
     * Muestra el menú de opciones al usuario.
     */
    public static void mostrarMenu() {
        System.out.println("""
                ************************************************************

                Seleccione una de las opciones a continuación:

                1) USD => ARS
                2) USD => BRL
                3) USD => COP
                4) ARS => USD
                5) ARS => BRL
                6) ARS => COP
                7) BRL => USD
                8) BRL => ARS
                9) BRL => COP
                10) COP => USD
                11) COP => ARS
                12) COP => BRL

                13) SALIR
                ************************************************************
                """);
    }

    /**
     * Maneja la lógica para interpretar la opción elegida por el usuario
     * y realizar la conversión correspondiente.
     *
     * @param opcion  número de opción del menú
     * @param tasas   respuesta con las tasas de cambio
     * @param service instancia del servicio de conversión
     * @param scanner objeto para leer entrada del usuario
     */
    public static void manejarConversion(int opcion, ExchangeRateResponse tasas, CurrencyService service, Scanner scanner) {
        String desde = "", hacia = "";

        // Asociar la opción con las monedas correspondientes
        switch (opcion) {
            case 1 -> { desde = "USD"; hacia = "ARS"; }
            case 2 -> { desde = "USD"; hacia = "BRL"; }
            case 3 -> { desde = "USD"; hacia = "COP"; }
            case 4 -> { desde = "ARS"; hacia = "USD"; }
            case 5 -> { desde = "ARS"; hacia = "BRL"; }
            case 6 -> { desde = "ARS"; hacia = "COP"; }
            case 7 -> { desde = "BRL"; hacia = "USD"; }
            case 8 -> { desde = "BRL"; hacia = "ARS"; }
            case 9 -> { desde = "BRL"; hacia = "COP"; }
            case 10 -> { desde = "COP"; hacia = "USD"; }
            case 11 -> { desde = "COP"; hacia = "ARS"; }
            case 12 -> { desde = "COP"; hacia = "BRL"; }
        }

        // Pedir el monto al usuario
        System.out.printf("Ingrese monto en %s: ", desde);
        try {
            BigDecimal monto = new BigDecimal(scanner.nextLine());

            // Llamar al servicio para hacer la conversión
            BigDecimal resultado = service.convertirMoneda(monto, desde, hacia, tasas);

            // Mostrar resultado
            System.out.println("\n***************CONVERSION***************");
            System.out.printf("\n%.4f %s equivalen a %.4f %s%n\n", monto, desde, resultado, hacia);
            
            // Pausar para que el usuario pueda leer el resultado antes de continuar
            System.out.println("\nPresione ENTER para continuar...");
            scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Monto inválido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la conversión: " + e.getMessage());
        }
    }
}