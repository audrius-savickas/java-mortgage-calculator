package DataWindow;

import Main.Main;
import Main.Linijinis;
import Main.MonthData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class DataWindow{
    static Stage secondaryStage = new Stage();
    static boolean isGraph = false;
    public static void start(MonthData [] data) {

        TableView table = new TableView();
        secondaryStage.setTitle(MonthData.pavadinimas + " paskolos " + "mokėjimas");
        Scene scene = new Scene(new Group());
        table.setEditable(true);

        TableColumn metaiCol = new TableColumn("Metai");
        metaiCol.setPrefWidth(80);
        TableColumn menuoCol = new TableColumn("Mėnuo");
        menuoCol.setPrefWidth(100);
        TableColumn moketiSumaCol = new TableColumn("Paskolos įmoka");
        moketiSumaCol.setPrefWidth(200);
        TableColumn procentuSumaCol = new TableColumn("Palūkanų įmoka");
        procentuSumaCol.setPrefWidth(200);
        TableColumn visoMoketiCol = new TableColumn("Bendra įmoka");
        visoMoketiCol.setPrefWidth(200);
        TableColumn likoNesumoketaCol = new TableColumn("Paskolos liko");
        likoNesumoketaCol.setPrefWidth(200);

        table.getColumns().addAll(metaiCol, menuoCol, moketiSumaCol, procentuSumaCol, visoMoketiCol, likoNesumoketaCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        metaiCol.setCellValueFactory(new PropertyValueFactory<>("metai"));
        menuoCol.setCellValueFactory(new PropertyValueFactory<>("menuo"));
        moketiSumaCol.setCellValueFactory(new PropertyValueFactory<>("moketiSuma"));
        procentuSumaCol.setCellValueFactory(new PropertyValueFactory<>("procentuMoketi"));
        visoMoketiCol.setCellValueFactory(new PropertyValueFactory<>("visoMoketi"));
        likoNesumoketaCol.setCellValueFactory(new PropertyValueFactory<>("likoNesumoketa"));

        VBox vbox = new VBox();
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(10, 10, 20, 10));
        vbox.setPrefWidth(1050);
        vbox.setPrefHeight(600);

        Text instrukcija = new Text("Pasirinkite metų ir mėnesio intervalą, kurio mokėjimus norite pamatyti");
        instrukcija.setFont(Font.font("System Regular", FontWeight.NORMAL, 18));
        Text from = new Text("NUO:    ");
        Text year1 = new Text("Metai:");
        Text month1 = new Text("Mėnuo:");
        from.setFont(Font.font("System Regular", FontWeight.NORMAL, 18));
        year1.setFont(Font.font("System Regular", FontWeight.NORMAL, 18));
        month1.setFont(Font.font("System Regular", FontWeight.NORMAL, 18));
        TextField yearInp1 = new TextField();
        TextField monthInp1 = new TextField();
        yearInp1.setMaxWidth(45);
        monthInp1.setMaxWidth(30);
        Text to = new Text("    IKI:    ");
        Text year2 = new Text("Metai:");
        Text month2 = new Text("Mėnuo:");
        Text sumokesite = new Text("Sumokėsite: iš viso " + MonthData.getIsVisoMoketi(data) + "€, "
        + "bankui " + (MonthData.getIsVisoMoketi(data)- Linijinis.visaSuma) + "€");
        sumokesite.setFont(Font.font("System Regular", FontWeight.NORMAL, 18));
        to.setFont(Font.font("System Regular", FontWeight.NORMAL, 18));
        year2.setFont(Font.font("System Regular", FontWeight.NORMAL, 18));
        month2.setFont(Font.font("System Regular", FontWeight.NORMAL, 18));
        TextField yearInp2 = new TextField();
        TextField monthInp2 = new TextField();
        yearInp2.setMaxWidth(45);
        monthInp2.setMaxWidth(30);

        final Text message = new Text();
        HBox hb0 = new HBox();
        HBox hb1 = new HBox(10);
        HBox hb2 = new HBox(10);
        HBox hb3 = new HBox(10);
        HBox hb4 = new HBox(10);
        HBox hb5 = new HBox();
        HBox hbNuo = new HBox(50);
        HBox hbIki = new HBox(50);
        HBox hbBtns = new HBox(10);
        Button btn = new Button("GERAI");
        Button reset = new Button("IŠVALYTI");
        Button back = new Button("Grįžti");
        Button fileBtn = new Button("Gauti ataskaitą");
        Button chartBtn = new Button("Grafikas");
        Region region1 = new Region();
        Region region2 = new Region();
        btn.setPrefSize(80, 40);
        reset.setPrefSize(100, 40);
        back.setPrefSize(80, 40);
        fileBtn.setPrefSize(140, 40);
        chartBtn.setPrefSize(80, 40);
        btn.setAlignment(Pos.CENTER);
        reset.setAlignment(Pos.CENTER);
        btn.setFont(Font.font("System Regular", FontWeight.NORMAL, 16));
        reset.setFont(Font.font("System Regular", FontWeight.NORMAL, 16));
        back.setFont(Font.font("System Regular", FontWeight.NORMAL, 16));
        fileBtn.setFont(Font.font("System Regular", FontWeight.NORMAL, 16));
        chartBtn.setFont(Font.font("System Regular", FontWeight.NORMAL, 16));

        hb0.getChildren().add(from);
        hb1.getChildren().addAll(year1, yearInp1);
        hb2.getChildren().addAll(month1, monthInp1);
        hb3.getChildren().addAll(year2, yearInp2);
        hb4.getChildren().addAll(month2, monthInp2);
        hb5.getChildren().add(to);
        hbNuo.getChildren().addAll(hb0, hb1, hb2, btn);
        hbIki.getChildren().addAll(hb5, hb3, hb4, reset);
        hbBtns.getChildren().addAll(back, region1, sumokesite, region2, chartBtn, fileBtn);
        hbNuo.setAlignment(Pos.CENTER);
        hbIki.setAlignment(Pos.CENTER);
        hbBtns.setAlignment(Pos.CENTER);
        HBox.setHgrow(region1, Priority.SOMETIMES);
        HBox.setHgrow(region2, Priority.SOMETIMES);
        vbox.getChildren().addAll(table, instrukcija, hbNuo, hbIki, hbBtns);
        vbox.setAlignment(Pos.CENTER);
        message.setFont(Font.font("System Regular", FontWeight.NORMAL, 20));
        message.setX(800);
        message.setY(450);

        final ObservableList<MonthData> list = FXCollections.observableArrayList(
                data
        );

        //GERAI BUTTON PRESS
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                System.out.println(btn.getLayoutX() + " " + btn.getLayoutY());
                int y1 = 0, y2 = 0, m1 = 0, m2 = 0;
                boolean isCorrect = true;
                try {
                    y1 = Integer.parseInt(yearInp1.getText());
                    y2 = Integer.parseInt(yearInp2.getText());
                    m1 = Integer.parseInt(monthInp1.getText());
                    m2 = Integer.parseInt(monthInp2.getText());
                    if (!IntervalCalculation.isDataCorrect(y1, y2, m1, m2, data)) isCorrect = false;
                } catch (Exception exc){
                    isCorrect = false;
                }
                if (!isCorrect) {
                    message.setFill(Color.FIREBRICK);
                    message.setText("Duomenys įvesti\nneteisingai");
                }
                else {
                    message.setFill(Color.GREEN);
                    message.setText("Duomenys išfiltruoti");
                    list.setAll();
                    for (int i = (y1*12 + m1); i <= (y2*12 + m2); i++){
                        list.add(data[i - (data[0].metai * 12 + IntervalCalculation.stringToInt(data[0].menuo))]);
                    }
                }
            }
        });
        //IŠVALYTI BUTTON PRESS
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                message.setText("");
                list.setAll(data);
            }
        });
        //GRĮŽTI BUTTON PRESS
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                secondaryStage.close();
                Main main = new Main();
                main.start(new Stage ());
            }
        });
        //ATASKAITOS BUTTON PRESS
        fileBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                System.out.println(fileBtn.getLayoutX() + " " + fileBtn.getLayoutY());
                try {
                    FileCreation.outputFile(data);
                    message.setFill(Color.GREEN);
                    message.setText("Ataskaita išvesta į\n\"ataskaita.xlsx\"");
                } catch (IOException ioException) {
                    message.setFill(Color.FIREBRICK);
                    message.setText("Uždarykite \"ataskaita.xlsx\"\nprieš tęsdami");
                }
            }
        });
        //GRAFIKO/LENTELĖS BUTTON PRESS
        chartBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                if (!isGraph){
                    isGraph = true;
                    vbox.getChildren().remove(0);
                    vbox.getChildren().add(0, Graph.createChart(data));
                    chartBtn.setText("Lentelė");
                }
                else {
                    isGraph = false;
                    vbox.getChildren().remove(0);
                    vbox.getChildren().add(0, table);
                    chartBtn.setText("Grafikas");
                }
            }
        });

        table.setItems(list);

        ((Group) scene.getRoot()).getChildren().addAll(vbox, message);

        secondaryStage.setScene(scene);
        secondaryStage.show();
    }
}
