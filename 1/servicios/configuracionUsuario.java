package servicios;

import java.util.Scanner;

public class configuracionUsuario {

    public record DatosAgricultor(String nombreUsuario, String nombreFinca) {}

    public static DatosAgricultor configurar(Scanner scanner, String nombreActual, String fincaActual) {
        System.out.println("""
                ========================================
                     CONFIGURACIÓN DE AGRICULTOR Y FINCA
                ========================================
                """);

        if (!nombreActual.isEmpty()) {
            System.out.println("Agricultor actual: " + nombreActual);
        }
        if (!fincaActual.isEmpty()) {
            System.out.println("Finca actual: " + fincaActual);
        }

        System.out.print("Ingrese el nombre del Agricultor: ");
        var nombre = scanner.nextLine().trim();
        while (nombre.isEmpty()) {
            System.out.print("El nombre no puede estar vacío. Ingrese el nombre del Agricultor: ");
            nombre = scanner.nextLine().trim();
        }

        System.out.print("Ingrese el nombre de la Finca: ");
        var finca = scanner.nextLine().trim();
        while (finca.isEmpty()) {
            System.out.print("El nombre de la finca no puede estar vacío. Ingrese el nombre de la Finca: ");
            finca = scanner.nextLine().trim();
        }

        System.out.printf("%n-> Datos guardados exitosamente: Agricultor '%s' - Finca '%s'%n%n", nombre, finca);

        System.out.print("Presione [ENTER] para volver al menú principal...");
        scanner.nextLine();
        System.out.println();

        return new DatosAgricultor(nombre, finca);
    }
}
