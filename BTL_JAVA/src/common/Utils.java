package common;

public enum Utils {
    GIAVE(50000);

    private final int value;

    Utils(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String tangMaKH(String maKH) {
        int number = Integer.parseInt(maKH);
        number++;
        return String.valueOf(number);
    }
}

