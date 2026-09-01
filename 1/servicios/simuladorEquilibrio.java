package servicios;

import java.util.Scanner;

public class simuladorEquilibrio {

    public static void simular(Scanner scanner) {
        System.out.println("""
                ========================================
                   SIMULADOR DE PUNTO DE EQUILIBRIO     
                ========================================
                """);

        // 1. Costos Fijos Totales con validación do-while
        var costosFijos = 0.0;
        do {
            System.out.print("Ingrese los Costos Fijos Totales (ej. arriendo, jornales, insumos): $");
            while (!scanner.hasNextDouble()) {
                System.out.println("Error: Por favor ingrese un número válido.");
                scanner.next();
                System.out.print("Ingrese los Costos Fijos Totales: $");
            }
            costosFijos = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer

            if (costosFijos <= 0) {
                System.out.println("Error: Los costos fijos deben ser mayores a cero.\n");
            }
        } while (costosFijos <= 0);

        // 2. Precio de Venta por Unidad con validación do-while
        var precioVenta = 0.0;
        do {
            System.out.print("Ingrese el Precio de Venta por unidad: $");
            while (!scanner.hasNextDouble()) {
                System.out.println("Error: Por favor ingrese un número válido.");
                scanner.next();
                System.out.print("Ingrese el Precio de Venta por unidad: $");
            }
            precioVenta = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer

            if (precioVenta <= 0) {
                System.out.println("Error: El precio de venta debe ser mayor a cero.\n");
            }
        } while (precioVenta <= 0);

        // 3. Costo Variable por Unidad con validación do-while
        var costoVariable = 0.0;
        var costoValido = false;
        do {
            System.out.print("Ingrese el Costo Variable por unidad: $");
            while (!scanner.hasNextDouble()) {
                System.out.println("Error: Por favor ingrese un número válido.");
                scanner.next();
                System.out.print("Ingrese el Costo Variable por unidad: $");
            }
            costoVariable = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer

            if (costoVariable < 0) {
                System.out.println("Error: El costo variable no puede ser negativo.\n");
            } else if (costoVariable >= precioVenta) {
                System.out.println("Advertencia: El costo variable debe ser menor al precio de venta para generar margen de ganancia.\n");
            } else {
                costoValido = true;
            }
        } while (!costoValido);

        // Cálculo: Punto de Equilibrio = Costos Fijos / (Precio Venta - Costo Variable)
        var margenContribucion = precioVenta - costoVariable;
        var puntoEquilibrio = costosFijos / margenContribucion;
        var ingresosEquilibrio = puntoEquilibrio * precioVenta;

        var resultado = """
                ========================================
                   RESULTADO: PUNTO DE EQUILIBRIO       
                ========================================
                Costos Fijos Totales:      $%,.2f COP
                Precio de Venta / Unidad:  $%,.2f COP
                Costo Variable / Unidad:   $%,.2f COP
                Margen de Contribución:    $%,.2f COP
                ----------------------------------------
                UNIDADES MÍNIMAS A VENDER: %,.2f unidades
                INGRESOS PARA EQUILIBRIO:  $%,.2f COP
                ========================================
                * Debe vender al menos %,.0f unidades para cubrir todos los costos y no tener pérdidas.
                """.formatted(costosFijos, precioVenta, costoVariable, margenContribucion, puntoEquilibrio, ingresosEquilibrio, Math.ceil(puntoEquilibrio));

        System.out.println(resultado);

        System.out.print("Presione [ENTER] para volver al menú principal...");
        scanner.nextLine();
        System.out.println();
    }
}
