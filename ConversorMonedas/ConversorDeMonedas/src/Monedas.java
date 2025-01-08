import java.util.Map;

public record Monedas(
        Map<String, Double> conversion_rates) {

    public Double obtenerTasaConversion(String moneda) {
        if (conversion_rates.containsKey(moneda)) {
            return conversion_rates.get(moneda);
        } else {
            throw new IllegalArgumentException("Esta moneda" + moneda + " no se encuentra disponible.");
        }
    }

    public void mostrarTasasConversion() {
        conversion_rates.forEach((moneda, tasa) -> {
            System.out.println(moneda + ": " + tasa);
        });
    }
}
