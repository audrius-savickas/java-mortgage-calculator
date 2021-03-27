package DataWindow;

import Main.Linijinis;
import Main.MonthData;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Graph {
    static LineChart createChart(MonthData [] data){
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mėnesiai");
        yAxis.setLabel("Suma (€)");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(1);
        xAxis.setUpperBound(Linijinis.kiekMen);
        //xAxis.setTickUnit(1);
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        XYChart.Series palukanos = new XYChart.Series();
        XYChart.Series kreditas = new XYChart.Series();
        XYChart.Series bendra = new XYChart.Series();
        palukanos.setName("Palūkanų įmoka");
        kreditas.setName("Paskolos įmoka");
        bendra.setName("Bendra įmoka");
        for (int i = 0; i < Linijinis.kiekMen; i++){
            palukanos.getData().add(new XYChart.Data(i+1, data[i].procentuMoketi));
            kreditas.getData().add(new XYChart.Data(i+1, data[i].moketiSuma));
            bendra.getData().add(new XYChart.Data(i+1, data[i].visoMoketi));
        }
        lineChart.getData().addAll(palukanos, kreditas, bendra);
        return lineChart;
    }
}
