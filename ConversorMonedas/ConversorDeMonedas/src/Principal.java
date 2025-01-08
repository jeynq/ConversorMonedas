import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String monedaConvertir;
        String respuesta;

        do {
            try {
                System.out.println("""
                        ********************************************
                            SEA BIENVENIDO AL CONVERTOR DE MONEDAS
                        
                            COP para pesos colombianos
                            MXN para pesos mexicanos
                            USD para dolares americanos
                            BRL para reales brasileños
                            ARS para pesos argentinos
                            
                            Seleccione el tipo de MONEDA que desea escribiendo el codigo de la moneda:
                        ********************************************
                        """);

                monedaConvertir = scanner.nextLine().toUpperCase();
                ConsultaMonedas consulta = new ConsultaMonedas();
                Monedas monedas = consulta.buscarMoneda(monedaConvertir);

                Generador generador = new Generador();
                generador.guardarJson(monedas);

            } catch (IllegalArgumentException | IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Asegúrate de usar correctamente el código de la moneda.");
                return;
            }

            try {
                System.out.printf("""
                        ********************************************
                        ¿Qué cantidad de dinero %s deseas convertir?
                        ********************************************
                        """, monedaConvertir);

                double cantidad = scanner.nextDouble();

                ConsultaMonedas consulta = new ConsultaMonedas();
                Monedas monedas = consulta.buscarMoneda(monedaConvertir);

                System.out.println(""" 
                        **********************************************************
                        Escribe EL codigo de la MONEDA a la que deseas convertir:
                          (USD, MXN, ARS, BRL, COP)
                         **********************************************************
                        """);

                String divisaDestino = scanner.next().toUpperCase();
                Double tasaConversion = monedas.obtenerTasaConversion(divisaDestino);

                double resultado = cantidad * tasaConversion;
                System.out.printf("La cantidad de %.2f %s es igual a %.2f %s %n", cantidad, monedaConvertir, resultado, divisaDestino);

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("¿Desea realizar mas conversiones? (Escriba SI/NO)");
            respuesta = scanner.next().toLowerCase();
            scanner.nextLine();
        } while (respuesta.equals("si") || respuesta.equals("sí"));

        scanner.close();
        System.out.println("GRACIAS POR USAR EL CONVERTOR DE MONEDAS");
    }
}