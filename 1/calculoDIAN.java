public class calculoDIAN {
    public static double calcularIVA(String producto, double valor) {
        return !producto.equals("B") && !producto.contains("CAFÉ") && !producto.contains("CAFE")
                ? (double) 0.0F
                : valor * 0.05;
    }

    public static double calcularReteFuente(double valor) {
        return valor > (double) 4000000.0F ? valor * 0.025 : valor * 0.015;
    }
}