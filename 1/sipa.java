import java.util.Scanner;

public class sipa {
    public static void main() {
        // variables iniciales y configuracion del programa
        var salir = false;
        var scanner = new Scanner(System.in);
        var nombreUsuario = "";
        var nombreFinca = "";
        var flete = 0;
        var productoSeleccionado = "";
        var valorProducto = 0;

        var menuPrincipal = """
                Bienvenido al SIPA
                OPCIONES
                1.Registrar Cosecha(Calculo de proyeccion)
                2.Glosario de Terminos(Analisis de viabilidad)
                3.Simulador de punto de equilibrio
                4.Configuracion
                5.Generar Reporte y salir
                Recuerde enviar solo el numero correspondiente a su opcion seleccionada.
                """;

        while (!salir) {
            System.out.println(menuPrincipal);
            System.out.print("Seleccione una opción: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Por favor ingrese un numero.");
                scanner.next();
                continue;
            }
            var opcion = scanner.nextInt();
            scanner.nextLine();
            // switch general
            switch (opcion) {
                case 1 -> {
                    // switch registrar cosecha
                    var subMenu = """
                            Seleccione una opcion:
                            A.Seleccion de Producto
                            B.Datos de Volumen
                            C.Ubicación y Flete (Costo de Transporte)
                            D.Destino y Recargos
                            E.Cálculo de Impuestos (DIAN)
                            Recuerde enviar solo la letra correspondiente a su opcion seleccionada.
                            """;
                    System.out.print(subMenu);
                    var subOpcion = scanner.nextLine().toUpperCase().trim();
                    var salirSubMenu = false;
                    // switch submenu
                    do {
                        switch (subOpcion) {
                            case "A" -> {
                                // switch submenuA
                                var subMenuA = """
                                        A. Papa Capira $2.500 COP/kg
                                        B. Café Pergamino $15.000 COP/kg
                                        C. Aguacate Hass $8.000 COP/kg
                                        D. Volver al menú anterior

                                        Recuerde enviar solo la letra correspondiente a su opcion seleccionada.
                                        """;
                                System.out.print(subMenuA);
                                var opcionA = scanner.nextLine().toUpperCase();
                                productoSeleccionado = opcionA;
                                switch (opcionA) {
                                    case "A" -> {
                                        System.out.print("Ingrese la cantidad en kg de papa capira: ");
                                        var cantidad = scanner.nextInt();
                                        System.out.println("Cantidad registrada: " + cantidad);
                                        valorProducto = cantidad * 2500;
                                    }
                                    case "B" -> {
                                        System.out.print("Ingrese la cantidad en kg de café pergamino: ");
                                        var cantidad = scanner.nextInt();
                                        System.out.println("Cantidad registrada: " + cantidad);
                                        valorProducto = cantidad * 15000;
                                    }
                                    case "C" -> {
                                        System.out.print("Ingrese la cantidad en kg de aguacate hass: ");
                                        var cantidad = scanner.nextInt();
                                        System.out.println("Cantidad registrada: " + cantidad);
                                        valorProducto = cantidad * 8000;
                                    }
                                    case "D" -> {
                                        salirSubMenu = true;
                                    }
                                    default -> System.out.println("Opción no implementada o no válida.");
                                }
                            }
                            case "B" -> {
                                System.out.print("Ingrese las toneladas a transportar del producto: ");
                                var toneladas = scanner.nextDouble();
                                var kilos = convertidorToneladas.convertidorToneladas(toneladas);
                                System.out.println("El volumen registrado es: " + toneladas
                                        + " Toneladas, lo que equivale a " + kilos + " Kilos.");
                            }
                            case "C" -> {

                                var salirSubMenuC = false;

                                do {
                                    System.out.print(
                                            "Ingrese la ubicación de origen del producto para calcular el flete: ");
                                    System.out.println("A. Boyacá: $120,000 por tonelada.");
                                    System.out.println("B. Huila: $180,000 por tonelada.");
                                    System.out.println("C. Antioquia: $150,000 por tonelada.");
                                    System.out.println("D. Volver al menú anterior");
                                    System.out.println(
                                            "Recuerde enviar solo la letra correspondiente a su opcion seleccionada.");

                                    // Limpiar Buffer
                                    var ubicacion = scanner.nextLine().toUpperCase();
                                    if (ubicacion.isEmpty()) {
                                        ubicacion = scanner.nextLine().toUpperCase();
                                    }

                                    switch (ubicacion) {
                                        case "A" -> {
                                            flete = 120000;
                                            salirSubMenuC = true;
                                        }
                                        case "B" -> {
                                            flete = 180000;
                                            salirSubMenuC = true;
                                        }
                                        case "C" -> {
                                            flete = 150000;
                                            salirSubMenuC = true;
                                        }
                                        case "D" -> {
                                            salirSubMenuC = true;
                                        }
                                        default -> System.out.println("Opción no válida. Intente de nuevo.");
                                    }
                                } while (!salirSubMenuC);

                                System.out.println("El costo de flete es: " + flete);
                            }

                            case "D" -> {
                                var salirSubMenuD = false;
                                System.out.print("Ingrese el tipo de destino del producto: ");
                                do {
                                    System.out.println("A. Mercado Local / Corabastos: Sin recargos adicionales.");
                                    System.out.println(
                                            "B. Exportación (Puerto): Suma un 15% de recargo logístico sobre el costo del flete.");
                                    System.out.println("C. Volver al menú anterior");
                                    System.out.println(
                                            "Recuerde enviar solo la letra correspondiente a su opcion seleccionada.");

                                    var destino = scanner.nextLine().toUpperCase();
                                    if (destino.isEmpty()) {
                                        destino = scanner.nextLine().toUpperCase();
                                    }
                                    switch (destino) {
                                        case "A" -> {
                                            salirSubMenuD = true;
                                        }
                                        case "B" -> {
                                            flete += (flete * 0.15);
                                            salirSubMenuD = true;
                                        }
                                        case "C" -> {
                                            salirSubMenuD = true;
                                        }
                                        default -> System.out.println("Opción no válida. Intente de nuevo.");
                                    }
                                } while (!salirSubMenuD);
                            }

                            case "E" -> {
                                var iva = calculoDIAN.calcularIVA(productoSeleccionado, valorProducto);
                                var retefuente = calculoDIAN.calcularReteFuente(valorProducto);
                                System.out.println("El iva del producto es: " + iva);
                                System.out.println("La retefuente del producto es: " + retefuente);
                            }
                        }
                        System.out.print("¿Desea continuar? (S/N): ");
                        var continuar = scanner.nextLine().toUpperCase();
                        if (continuar.equals("N")) {
                            salirSubMenu = true;
                        }
                    } while (!salirSubMenu);
                }

                case 5 -> {
                    System.out.println("\n========================================");
                    System.out.println("            REPORTE FINAL SIPA          ");
                    System.out.println("========================================");
                    System.out.println("Usuario: " + (nombreUsuario.isEmpty() ? "No registrado" : nombreUsuario));
                    System.out.println("Finca: " + (nombreFinca.isEmpty() ? "No registrado" : nombreFinca));
                    System.out.println("Producto Seleccionado: "
                            + (productoSeleccionado.isEmpty() ? "Ninguno" : productoSeleccionado));
                    System.out.println("Valor del Producto: $" + valorProducto + " COP");
                    System.out.println("Costo de Flete: $" + flete + " COP");

                    double iva = calculoDIAN.calcularIVA(productoSeleccionado, valorProducto);
                    double retefuente = calculoDIAN.calcularReteFuente(valorProducto);
                    double totalImpuestos = iva + retefuente;
                    double neto = valorProducto - totalImpuestos - flete;

                    System.out.println("Impuesto IVA: $" + iva + " COP");
                    System.out.println("Retención en la Fuente (Retefuente): $" + retefuente + " COP");
                    System.out.println("Total Impuestos: $" + totalImpuestos + " COP");
                    System.out.println("----------------------------------------");
                    System.out.println("Total Neto (Proyección): $" + neto + " COP");
                    System.out.println("========================================\n");

                    salir = true;
                }
                default -> System.out.println("Opción no implementada o no válida.");
            }
        }
    }
}
