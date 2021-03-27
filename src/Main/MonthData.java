package Main;

import java.text.DecimalFormat;

public class MonthData {
    public static String pavadinimas;
    public int metai;
    public String menuo;
    public float visoMoketi;
    public float procentuMoketi;
    public float moketiSuma;
    public float likoNesumoketa;
    DecimalFormat df2 = new DecimalFormat("0.00");

    public int getMetai(){
        return metai;
    }
    public String getMenuo(){
        return menuo;
    }
    public String getVisoMoketi(){
        return df2.format(visoMoketi) + "€";
        //return (float) (Math.round(visoMoketi*100.00)/100.00);
    }
    public String getProcentuMoketi(){
        return df2.format(procentuMoketi) + "€";
    }
    public String getMoketiSuma(){
        return df2.format(moketiSuma) + "€";
    }
    public String getLikoNesumoketa(){
        return df2.format(likoNesumoketa) + "€";
    }
    public static int getIsVisoMoketi(MonthData [] data){
        int temp = 0;
        for (int i = 0; i < Linijinis.kiekMen; i++){
            temp += data[i].visoMoketi;
        }
        return temp;
    }
}
