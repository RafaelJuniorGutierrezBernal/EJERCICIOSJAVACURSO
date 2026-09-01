import java.util.Scanner;
import menus.menusCreacion;
import servicios.registroCosecha;
import servicios.glosarioTerminos;
import servicios.simuladorEquilibrio;
import servicios.configuracionUsuario;

public class SIPA {
    void main() {
        var salir = false;
        var scanner = new Scanner(System.in);
        var menu = new menusCreacion();

        // Variables para persistir datos durante la ejecución (usadas en el reporte final)
        var nombreUsuario = "";
        var nombreFinca = "";
        var productoSeleccionado = "";
        var subtotal = 0.0;
        var flete = 0.0;
        var totalImpuestos = 0.0;
        var utilidadNeta = 0.0;

        while (!salir) {
            System.out.println(menu.menuPrincipal());
            System.out.print("Seleccione una opción: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Por favor ingrese un número.\n");
                scanner.next();
                scanner.nextLine(); // Limpiar buffer
                continue;
            }
            var opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            // Switch principal de opciones
            switch (opcion) {
                case 1 -> {
                    var resultado = registroCosecha.registrar(scanner, menu);
                    // Guardar los datos calculados para el reporte final (Opción 5)
                    productoSeleccionado = resultado.producto();
                    subtotal = resultado.subtotal();
                    flete = resultado.flete();
                    totalImpuestos = resultado.totalImpuestos();
                    utilidadNeta = resultado.utilidadNeta();
                }

                case 2 -> glosarioTerminos.mostrarGlosario(scanner);

                case 3 -> simuladorEquilibrio.simular(scanner);

                case 4 -> {
                    var config = configuracionUsuario.configurar(scanner, nombreUsuario, nombreFinca);
                    nombreUsuario = config.nombreUsuario();
                    nombreFinca = config.nombreFinca();
                }

                case 5 -> {
                    var reporteFinal = """
                            ========================================
                                        REPORTE FINAL SIPA          
                            ========================================
                            Agricultor:                %s
                            Finca:                     %s
                            Último Producto Calculado: %s
                            Subtotal Bruto:            $%,.2f COP
                            Costo de Flete:            $%,.2f COP
                            Impuestos Totales:         $%,.2f COP
                            ----------------------------------------
                            Utilidad Neta:             $%,.2f COP
                            ========================================
                            Gracias por usar SIPA. ¡Buen provecho con su cosecha!
                            """.formatted(
                                nombreUsuario.isEmpty() ? "No registrado" : nombreUsuario,
                                nombreFinca.isEmpty() ? "No registrado" : nombreFinca,
                                productoSeleccionado.isEmpty() ? "Ninguno" : productoSeleccionado,
                                subtotal,
                                flete,
                                totalImpuestos,
                                utilidadNeta
                            );
                    System.out.println(reporteFinal);
                    salir = true;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.\n");
            }
        }
    }
}
