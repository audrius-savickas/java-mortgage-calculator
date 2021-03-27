package Main;

import DataWindow.DataWindow;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class Main extends Application {
    //Variables for input
    float suma = 0;
    int metai = 0, menuo = 0;
    String grafikas = new String("");
    float procentas = 0;
    boolean okPressed = false;

    public static void main(String args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Program title
        primaryStage.setTitle("Būsto paskolos skaičiuoklė");


        //Grid pane creation
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        //Field creation
        Text scenetitle = new Text("Įveskite duomenis");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        grid.add(scenetitle, 0,0, 3, 1);

        Label paskolosText = new Label("Paskolos suma:");
        paskolosText.setFont(Font.font("System Regular", FontWeight.NORMAL, 16));
        grid.add(paskolosText, 0, 1, 1, 1);
        TextField paskolosSuma = new TextField();
        paskolosSuma.setMaxWidth(120);
        HBox hbSuma = new HBox(5);
        hbSuma.getChildren().addAll(paskolosSuma, new Text("€"));
        hbSuma.setAlignment(Pos.CENTER_LEFT);
        grid.add(hbSuma, 1, 1);

        Label terminoText = new Label("Paskolos terminas (YYYY, MM):");
        terminoText.setFont(Font.font("System Regular", FontWeight.NORMAL, 16));
        grid.add(terminoText, 0, 2, 1, 1);
        TextField terminoMetai = new TextField();
        terminoMetai.setMaxWidth(45);
        TextField terminoMenuo = new TextField();
        terminoMenuo.setMaxWidth(30);
        HBox hbMetai = new HBox(10);
        hbMetai.getChildren().addAll(terminoMetai, terminoMenuo);
        grid.add(hbMetai, 1, 2);

        Label grafikoText = new Label("Paskolos tipas:");
        grafikoText.setFont(Font.font("System Regular", FontWeight.NORMAL, 16));
        grid.add(grafikoText, 0, 3);
        ObservableList<String> grafikuOptions =
                FXCollections.observableArrayList(
                        "Anuiteto",
                        "Linijinis"
                );
        final ComboBox grafikoDropdown = new ComboBox(grafikuOptions);
        grid.add(grafikoDropdown, 1, 3);

        Label procentuText = new Label("Metinis procentas:");
        procentuText.setFont(Font.font("System Regular", FontWeight.NORMAL, 16));
        grid.add(procentuText, 0, 4);
        TextField procentasInp = new TextField();
        procentasInp.setMaxWidth(30);
        HBox hbProcentas = new HBox(5);
        hbProcentas.getChildren().addAll(procentasInp, new Text("%"));
        hbProcentas.setAlignment(Pos.CENTER_LEFT);
        grid.add(hbProcentas, 1, 4);

        Button btn = new Button("OK");
        btn.setPrefSize(50, 30);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 5, 2, 1);


        //Check if input is correct after pressing OK
        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);

        Scene scene = new Scene(grid, 450, 300);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                boolean isGood = true;
                try {
                     suma =  Float.parseFloat(paskolosSuma.getText());
                     metai = Integer.parseInt(terminoMetai.getText());
                     menuo = Integer.parseInt(terminoMenuo.getText());
                     grafikas = (String) grafikoDropdown.getValue();
                     procentas = Float.parseFloat(procentasInp.getText());
                     if (suma < 0 || metai < 2021 || menuo < 1 || menuo > 12 || (menuo < 4 && metai <= 2021)
                             || grafikas.equals("") || procentas < 1) {
                         isGood = false;
                     }
                } catch (Exception exc){
                    isGood = false;
                }
                /*suma = 1000;
                metai = 2024;
                menuo = 3;
                procentas = 10;
                grafikas = (String) grafikoDropdown.getValue();
                isGood = true;*/
                if (isGood) {
                    actiontarget.setFill(Color.GREEN);
                    actiontarget.setText("Duomenys įvesti teisingai");
                    if (grafikas.equals("Linijinis")){
                        Linijinis linijinis = new Linijinis(suma, metai, menuo,  procentas);
                        linijinis.calculateData();
                    }
                    else if (grafikas.equals("Anuiteto")){
                        Anuiteto anuiteto = new Anuiteto(suma, metai, menuo, procentas);
                        anuiteto.calculateData();
                    }
                    primaryStage.close();
                    DataWindow.start(Linijinis.data);
                }
                else {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Duomenys įvesti neteisingai");
                }
            }
        });

        primaryStage.setScene(scene);

        //Show stage
        primaryStage.show();
    }
}


