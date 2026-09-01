package servicios;

import java.util.Scanner;

public class glosarioTerminos {

    public static void mostrarGlosario(Scanner scanner) {
        var salirGlosario = false;
        var menuGlosario = """
                ========================================
                          GLOSARIO DE TÉRMINOS          
                ========================================
                1. Punto de Equilibrio
                2. Retención en la Fuente
                3. Arancel Logístico
                4. Volver al Menú Principal
                Seleccione un concepto para ver su definición: 
                """;

        do {
            System.out.print(menuGlosario);
            var opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1" -> {
                    var definicion = """
                            ----------------------------------------
                            PUNTO DE EQUILIBRIO:
                            "Es el nivel de ventas donde los ingresos igualan
                            a los costos totales; es decir, donde no hay
                            pérdida ni ganancia."
                            ----------------------------------------
                            """;
                    System.out.println(definicion);
                }
                case "2" -> {
                    var definicion = """
                            ----------------------------------------
                            RETENCIÓN EN LA FUENTE:
                            "Es un mecanismo de recaudo anticipado de impuestos
                            que el comprador descuenta del pago total al
                            agricultor."
                            ----------------------------------------
                            """;
                    System.out.println(definicion);
                }
                case "3" -> {
                    var definicion = """
                            ----------------------------------------
                            ARANCEL LOGÍSTICO:
                            "Costo adicional aplicado a mercancías destinadas
                            a la exportación para cubrir trámites portuarios."
                            ----------------------------------------
                            """;
                    System.out.println(definicion);
                }
                case "4" -> salirGlosario = true;
                default -> System.out.println("Opción no válida. Intente de nuevo.\n");
            }

            if (!salirGlosario) {
                System.out.print("Presione [ENTER] para continuar en el glosario...");
                scanner.nextLine();
                System.out.println();
            }
        } while (!salirGlosario);
    }
}
