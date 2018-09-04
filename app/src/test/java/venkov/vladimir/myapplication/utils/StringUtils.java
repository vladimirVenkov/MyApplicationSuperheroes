package venkov.vladimir.myapplication.utils;

public class StringUtils {
    public static String shuffleCasing(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Math.random() < 0.5) {
                result.append(Character.toUpperCase(str.charAt(i)));
            } else {
                result.append(Character.toLowerCase(str.charAt(i)));
            }
        }

        return result.toString();
    }

    public static String repeat(String s, int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(s);
        }

        return result.toString();
    }
}
