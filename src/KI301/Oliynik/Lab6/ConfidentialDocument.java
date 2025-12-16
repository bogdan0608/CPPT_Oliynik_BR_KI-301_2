package KI301.Oliynik.Lab6;

/**
 * Конфіденційний документ як цінність у сейфі.
 */
public class ConfidentialDocument implements Valuable {

    private final String title;
    private final int secrecyLevel;     // 1..10
    private final double marketValue;   // умовна оцінка

    /**
     * @param title назва документа
     * @param secrecyLevel рівень секретності (1..10)
     * @param marketValue умовна грошова оцінка
     */
    public ConfidentialDocument(String title, int secrecyLevel, double marketValue) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (secrecyLevel < 1 || secrecyLevel > 10) {
            throw new IllegalArgumentException("Secrecy level must be in 1..10");
        }
        if (marketValue <= 0) {
            throw new IllegalArgumentException("Market value must be > 0");
        }
        this.title = title;
        this.secrecyLevel = secrecyLevel;
        this.marketValue = marketValue;
    }

    /**
     * Цінність документа: як приклад — marketValue * коефіцієнт секретності.
     */
    @Override
    public double getValue() {
        return marketValue * (1.0 + secrecyLevel / 10.0);
    }

    public String getTitle() {
        return title;
    }

    public int getSecrecyLevel() {
        return secrecyLevel;
    }

    public double getMarketValue() {
        return marketValue;
    }

    @Override
    public String toString() {
        return String.format("Документ [назва: %s, секретність: %d/10, базова оцінка: %.2f, підсумкова вартість: %.2f]",
                title, secrecyLevel, marketValue, getValue());
    }
}