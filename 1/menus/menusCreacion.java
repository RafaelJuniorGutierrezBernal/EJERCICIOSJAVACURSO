package menus;

public class menusCreacion {

    public String menuPrincipal() {
        return """
                Bienvenido al SIPA
                OPCIONES
                1. Registrar Cosecha (Cálculo de proyección)
                2. Glosario de Términos (Análisis de viabilidad)
                3. Simulador de punto de equilibrio
                4. Configuración
                5. Generar Reporte y salir
                Recuerde enviar solo el número correspondiente a su opción seleccionada.
                """;
    }

    public String subMenuProductos() {
        return """
                --- PASO 1: SELECCIÓN DE PRODUCTO ---
                A. Papa Capira $2.500 COP/kg
                B. Café Pergamino $15.000 COP/kg
                C. Aguacate Hass $8.000 COP/kg

                Recuerde enviar solo la letra correspondiente a su opción seleccionada: 
                """;
    }

    public String subMenuUbicacion() {
        return """
                --- PASO 3: UBICACIÓN DE ORIGEN (FLETE) ---
                A. Boyacá: $120,000 por tonelada.
                B. Huila: $180,000 por tonelada.
                C. Antioquia: $150,000 por tonelada.

                Recuerde enviar solo la letra correspondiente a su opción seleccionada: 
                """;
    }

    public String subMenuDestino() {
        return """
                --- PASO 4: DESTINO Y RECARGOS ---
                A. Mercado Local / Corabastos: Sin recargos adicionales.
                B. Exportación (Puerto): Suma un 15% de recargo logístico sobre el costo del flete.

                Recuerde enviar solo la letra correspondiente a su opción seleccionada: 
                """;
    }
}
