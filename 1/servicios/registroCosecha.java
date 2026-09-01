package servicios;

import java.util.Scanner;
import menus.menusCreacion;
import calculos.calculoDIAN;
import calculos.convertidorToneladas;

public class registroCosecha {

    public record ResultadoCosecha(
            String producto,
            double toneladas,
            double kilos,
            double subtotal,
            double flete,
            double iva,
            double retefuente,
            double totalImpuestos,
            double utilidadNeta
    ) {}

    public static ResultadoCosecha registrar(Scanner scanner, menusCreacion menu) {
        System.out.println("""
                ========================================
                     REGISTRO Y PROYECCIÓN DE COSECHA   
                ========================================
                """);

        // A. Selección de Producto (Switch expression para asignar precio base por kilo)
        var producto = "";
        var precioBaseKilo = 0.0;
        do {
            System.out.print(menu.subMenuProductos());
            var opcion = scanner.nextLine().toUpperCase().trim();
            precioBaseKilo = switch (opcion) {
                case "A" -> {
                    producto = "Papa Capira";
                    yield 2500.0;
                }
                case "B" -> {
                    producto = "Café Pergamino";
                    yield 15000.0;
                }
                case "C" -> {
                    producto = "Aguacate Hass";
                    yield 8000.0;
                }
                default -> {
                    System.out.println("Opción no válida. Intente de nuevo.\n");
                    yield 0.0;
                }
            };
        } while (precioBaseKilo == 0.0);

        System.out.printf("-> Seleccionado: %s ($%,.0f COP/kg)%n%n", producto, precioBaseKilo);

        // B. Datos de Volumen (Toneladas -> Kilos) con validación do-while para números positivos
        var toneladas = 0.0;
        do {
            System.out.print("Ingrese la cantidad en Toneladas a cosechar: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Error: Por favor ingrese un valor numérico.");
                scanner.next();
                System.out.print("Ingrese la cantidad en Toneladas a cosechar: ");
            }
            toneladas = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer

            if (toneladas <= 0) {
                System.out.println("Error: La cantidad de toneladas debe ser mayor a cero.\n");
            }
        } while (toneladas <= 0);

        var kilos = convertidorToneladas.convertidorToneladas(toneladas);
        var subtotal = kilos * precioBaseKilo;
        System.out.printf("-> Volumen registrado: %,.2f Ton (%,.0f kg). Subtotal Bruto: $%,.2f COP%n%n", toneladas, kilos, subtotal);

        // C. Ubicación y Flete (Switch expression para determinar costo base por tonelada)
        var tarifaFlete = 0.0;
        var origen = "";
        do {
            System.out.print(menu.subMenuUbicacion());
            var opcionOrigen = scanner.nextLine().toUpperCase().trim();
            tarifaFlete = switch (opcionOrigen) {
                case "A" -> {
                    origen = "Boyacá";
                    yield 120000.0;
                }
                case "B" -> {
                    origen = "Huila";
                    yield 180000.0;
                }
                case "C" -> {
                    origen = "Antioquia";
                    yield 150000.0;
                }
                default -> {
                    System.out.println("Opción no válida. Intente de nuevo.\n");
                    yield 0.0;
                }
            };
        } while (tarifaFlete == 0.0);

        var fleteBase = tarifaFlete * toneladas;
        System.out.printf("-> Origen: %s - Costo base flete: $%,.2f COP%n%n", origen, fleteBase);

        // D. Destino y Recargos (15% si es exportación)
        var fleteFinal = fleteBase;
        var destinoValido = false;
        do {
            System.out.print(menu.subMenuDestino());
            var opcionDestino = scanner.nextLine().toUpperCase().trim();
            switch (opcionDestino) {
                case "A" -> {
                    System.out.println("-> Destino: Mercado Local (Sin recargos adicionales).\n");
                    destinoValido = true;
                }
                case "B" -> {
                    fleteFinal = fleteBase + (fleteBase * 0.15);
                    System.out.printf("-> Destino: Exportación (+15%% recargo logístico). Flete total: $%,.2f COP%n%n", fleteFinal);
                    destinoValido = true;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.\n");
            }
        } while (!destinoValido);

        // E. Cálculo de Impuestos (DIAN) y Proyección
        var iva = calculoDIAN.calcularIVA(producto, subtotal);
        var retefuente = calculoDIAN.calcularReteFuente(subtotal);
        var totalImpuestos = iva + retefuente;
        var utilidadNeta = subtotal - totalImpuestos - fleteFinal;

        var resumen = """
                ========================================
                   RESUMEN DE PROYECCIÓN DE COSECHA     
                ========================================
                Producto:                 %s
                Volumen:                  %,.2f Ton (%,.0f kg)
                Subtotal Bruto:           $%,.2f COP
                Costo de Flete Total:     $%,.2f COP
                Impuesto IVA (DIAN):      $%,.2f COP
                Retención en la Fuente:   $%,.2f COP
                Total Impuestos:          $%,.2f COP
                ----------------------------------------
                Utilidad Neta Proyectada: $%,.2f COP
                ========================================
                """.formatted(producto, toneladas, kilos, subtotal, fleteFinal, iva, retefuente, totalImpuestos, utilidadNeta);

        System.out.println(resumen);

        // Pausa para que el usuario pueda leer los resultados antes de regresar al menú
        System.out.print("Presione [ENTER] para volver al menú principal...");
        scanner.nextLine();
        System.out.println();

        return new ResultadoCosecha(producto, toneladas, kilos, subtotal, fleteFinal, iva, retefuente, totalImpuestos, utilidadNeta);
    }
}
