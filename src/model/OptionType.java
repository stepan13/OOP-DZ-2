package model;

public enum OptionType {
    MODEL("Model"),
    RAM("RAM size", true),
    DISKSIZE("DISK sise", true),
    OS("OS name"),
    COLOR("Color");

    private static OptionType[] list = OptionType.values();
    private String name;
    private boolean isNumber;

    OptionType(String name, boolean isNumber) {
        this.name = name;
        this.isNumber = isNumber;
    }

    OptionType(String name) {
        this(name, false);
    }

    public static OptionType getByNumber(int i) {
        return getByIndex(i - 1);
    }

    public static OptionType getByIndex(int i) {
        if (i < list.length) {
            return list[i];
        }
        return null;
    }

    public int number() {
        return this.ordinal() + 1;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean isNumber() {
        return isNumber;
    }
}
