package Main;

public class Linijinis{
    public static int kiekMen;
    public static float visaSuma;
    protected int metai, menuo;
    protected String pavadinimas = "Linijinės";
    protected float procentas;
    public static MonthData[] data;

    private int currYear = 2021;
    private int currCount = 0;

    Linijinis (float suma, int metai, int menuo, float procentas){
        visaSuma = suma;
        this.metai = metai;
        this.menuo = menuo;
        this.procentas = procentas;
        kiekMen = (metai - 2021) * 12 + menuo - 3;
        data = new MonthData[kiekMen];
    }

    public void setData( int menuo, float paskolosImoka, float palukanuImoka, float visoMoketi, float likoNesumoketa) {
        data[currCount] = new MonthData();
        data[currCount].visoMoketi = visoMoketi;
        data[currCount].likoNesumoketa = likoNesumoketa;
        data[currCount].metai = currYear;
        data[currCount].procentuMoketi = palukanuImoka;
        data[currCount].moketiSuma = paskolosImoka;
        menuo = menuo % 12;
        if (menuo == 0) menuo = 12;
        switch (menuo) {
            case 1 -> data[currCount].menuo = "sausis";
            case 2 -> data[currCount].menuo = "vasaris";
            case 3 -> data[currCount].menuo = "kovas";
            case 4 -> data[currCount].menuo = "balandis";
            case 5 -> data[currCount].menuo = "gegužė";
            case 6 -> data[currCount].menuo = "birželis";
            case 7 -> data[currCount].menuo = "liepa";
            case 8 -> data[currCount].menuo = "rugpjūtis";
            case 9 -> data[currCount].menuo = "rugsėjis";
            case 10 -> data[currCount].menuo = "spalis";
            case 11 -> data[currCount].menuo = "lapkritis";
            case 12 -> data[currCount].menuo = "gruodis";
        }
        currCount++;
        if (menuo == 12) currYear++;
    }

    void calculateData(){
        MonthData.pavadinimas = pavadinimas;
        float moketi = 0;
        float liko = visaSuma;
        float perMen = visaSuma/kiekMen;
        for (int i = 3; i < kiekMen + 3; i++) {
            float procentuMoketi = liko * (procentas / 12 / 100);
            moketi = perMen + procentuMoketi;
            setData(i, perMen, procentuMoketi, moketi, liko);
            liko -= perMen;
        }
    }
}

class Anuiteto extends Linijinis{
    //Field overriding
    String pavadinimas = "Anuiteto";

    Anuiteto (float suma, int metai, int menuo, float procentas){
        super(suma, metai, menuo, procentas);
    }

    //Method overriding
    void calculateData(){
        MonthData.pavadinimas = pavadinimas;
        float procPerMen = procentas/12/100;
        float liko = visaSuma;
        float visoPerMen;
        float palukanuPerMen;
        visoPerMen = (float) ((procPerMen * visaSuma) / (1 - Math.pow((1 + procPerMen), -kiekMen)));
        for (int i = 3; i < kiekMen + 3; i++) {
            palukanuPerMen = procPerMen * liko;
            setData(i, (visoPerMen - palukanuPerMen), palukanuPerMen, visoPerMen, liko);
            liko = liko - (visoPerMen - palukanuPerMen);
        }
    }
}