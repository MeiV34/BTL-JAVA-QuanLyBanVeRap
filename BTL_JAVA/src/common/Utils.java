package common;

public enum Utils {
	INSTANCE; // Định nghĩa một instance duy nhất của enum

    public static String tangMaKH(String maKH) {
        int number = Integer.parseInt(maKH);
        number++;
        String newNumberStr = String.valueOf(number);
        return newNumberStr;
    }
}
