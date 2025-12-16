package KI301.Oliynik.Lab6;

/**
 * Золотий злиток як цінність у сейфі.
 */
public class GoldBar implements Valuable {

    private final String serial;
    private final double weightGrams;
    private final double pricePerGram;

    /**
     * @param serial серійний номер
     * @param weightGrams вага в грамах
     * @param pricePerGram ціна за 1 грам
     */
    public GoldBar(String serial, double weightGrams, double pricePerGram) {
        if (serial == null || serial.isBlank()) {
            throw new IllegalArgumentException("Serial cannot be empty");
        }
        if (weightGrams <= 0 || pricePerGram <= 0) {
            throw new IllegalArgumentException("Weight and price must be > 0");
        }
        this.serial = serial;
        this.weightGrams = weightGrams;
        this.pricePerGram = pricePerGram;
    }

    /**
     * Цінність злитка = вага * ціна за грам.
     */
    @Override
    public double getValue() {
        return weightGrams * pricePerGram;
    }

    public String getSerial() {
        return serial;
    }

    public double getWeightGrams() {
        return weightGrams;
    }

    public double getPricePerGram() {
        return pricePerGram;
    }

    @Override
    public String toString() {
        return String.format("Золотий злиток [серія: %s, вага: %.1f г, ціна/г: %.2f, вартість: %.2f]",
                serial, weightGrams, pricePerGram, getValue());
    }
}