public class TasasDeCambio {
    public static final double USD_TO_COP = 3950.0;
    public static final double EUR_TO_COP = 4600.0;
    public static final double GBP_TO_COP = 5400.0;
    public static final double JPY_TO_COP = 36.75;
    public static final double KRW_TO_COP = 0.0036;

    // Método para convertir de COP a USD
    public static double convertirCOPaUSD(double cantidad) {
        return cantidad / USD_TO_COP;
    }

}
