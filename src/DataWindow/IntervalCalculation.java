package DataWindow;
import Main.MonthData;
import Main.Linijinis;

public class IntervalCalculation {
    public static boolean isDataCorrect(int y1, int y2, int m1, int m2, MonthData [] data) {
        if (y1 < 2021 || y2 < 2021 || m1 < 1 || m1 > 12 || m2 < 1 || m2 > 12 ) return false;
        else if (y1 == 2021 && m1 < 3) return false;
        else if (y2 < y1) return false;
        else if (y1 == y2 && m1 > m2) return false;
        int monthTotalInp1 = y1 * 12 + m1;
        int monthTotalInp2 = y2 * 12 + m2;

        int monthTotal = Linijinis.kiekMen;
        if (monthTotalInp2 - monthTotalInp1 > monthTotal) return false;

        int monthFirst = stringToInt(data[0].menuo);
        int monthLast = stringToInt(data[monthTotal - 1].menuo);

        if (monthLast + data[monthTotal-1].metai * 12 < monthTotalInp2) return false;
        else if (monthFirst + data[0].metai * 12 > monthTotalInp1) return false;
        return true;
    }
    public static int stringToInt(String menuo){
        return switch (menuo) {
            case "sausis" -> 1;
            case "vasaris" -> 2;
            case "kovas" -> 3;
            case "balandis" -> 4;
            case "gegužė" -> 5;
            case "birželis" -> 6;
            case "liepa" -> 7;
            case "rugpjūtis" -> 8;
            case "rugsėjis" -> 9;
            case "spalis" -> 10;
            case "lapkritis" -> 11;
            case "gruodis" -> 12;
            default -> 0;
        };
    }
}
