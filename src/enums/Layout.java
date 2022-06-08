package enums;

public enum Layout {
    FONT(14);

    private final int value;
    Layout(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
