package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;

import static sample.utils.*;


/*
 * Daniil Titov 27.06.2018
 * Test
 * Fuel App
 *
 * IN: read .txt file from IN folder
 * OUT: show statistics, based on fuel types: All, E85, 95, 98, D
 *
 * */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Fuel Price");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        Pane root = new Pane();
        Button btnFile = new Button();
        btnFile.setText("Open");
        btnFile.setPrefWidth(70);
        btnFile.setPrefHeight(20);
        btnFile.setLayoutX(25);
        btnFile.setLayoutY(40);


        ComboBox fuelTypeComboBox = new ComboBox();
        fuelTypeComboBox.getItems().addAll(
                fuelType95,
                fuelType98,
                fuelTypeE85,
                fuelTypeD,
                fuelTypeAll
        );
        fuelTypeComboBox.setPrefHeight(20);
        fuelTypeComboBox.setPrefWidth(80);
        fuelTypeComboBox.setLayoutX(100);
        fuelTypeComboBox.setLayoutY(40);
        fuelTypeComboBox.setOpacity(0);


        //BarChart for fuel 95
        BarChart barChart95 = fuelTypes();
        barChart95.setPrefHeight(450);
        barChart95.setPrefWidth(750);
        barChart95.setLayoutX(0);
        barChart95.setLayoutY(100);
        barChart95.setOpacity(0);
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Fuel 95");
        //BarChart for fuel 98
        BarChart barChart98 = fuelTypes();
        barChart98.setPrefHeight(450);
        barChart98.setPrefWidth(750);
        barChart98.setLayoutX(0);
        barChart98.setLayoutY(100);
        barChart98.setOpacity(0);
        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("Fuel 98");
        //BarChart for fuel E85
        BarChart barChartE85 = fuelTypes();
        barChartE85.setPrefHeight(450);
        barChartE85.setPrefWidth(750);
        barChartE85.setLayoutX(0);
        barChartE85.setLayoutY(100);
        barChartE85.setOpacity(0);
        XYChart.Series dataSeries3 = new XYChart.Series();
        dataSeries3.setName("Fuel E85");
        //BarChart for fuel D
        BarChart barChartD = fuelTypes();
        barChartD.setPrefHeight(450);
        barChartD.setPrefWidth(750);
        barChartD.setLayoutX(0);
        barChartD.setLayoutY(100);
        barChartD.setOpacity(0);
        XYChart.Series dataSeries4 = new XYChart.Series();
        dataSeries4.setName("Fuel D");
        //BarChart for fuel All
        BarChart barChartAll = fuelTypes();
        barChartAll.setPrefHeight(450);
        barChartAll.setPrefWidth(750);
        barChartAll.setLayoutX(0);
        barChartAll.setLayoutY(100);
        barChartAll.setOpacity(0);
        XYChart.Series dataSeries5 = new XYChart.Series();
        dataSeries5.setName("Fuel All types");


        btnFile.setOnAction((event) -> {
            System.out.println("Button Action Open file");

            FileChooser fileChooser = new FileChooser();
            File fileChosen = fileChooser.showOpenDialog(null);

            setFileNameOpened(fileChosen.getAbsolutePath().toString());

            System.out.println(getFileNameOpened());
            try {
                cleanFuelDataMap();
                createEmptyFuelMap();

                readFileData(getFileNameOpened());

                calculatedFuelData(fuelData);
                calculateAllAmountForAllFuelTypes();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            fuelTypeComboBox.setOpacity(100);


        });


        BarChart finalBarChart95 = barChart95;
        BarChart finalBarChart98 = barChart98;
        BarChart finalBarChartE85 = barChartE85;
        BarChart finalBarChartD = barChartD;
        BarChart finalbarChartAll = barChartAll;
        fuelTypeComboBox.setOnAction((event) -> {
            System.out.println("ComboboxAction");


            if (fuelTypeComboBox.getValue() != null &&
                    !fuelTypeComboBox.getValue().toString().isEmpty()) {

                if (fuelTypeComboBox.getValue().toString().equals(fuelType95)) {
                    finalBarChart95.setOpacity(100);
                    finalBarChart98.setOpacity(0);
                    finalBarChartE85.setOpacity(0);
                    finalBarChartD.setOpacity(0);
                    finalbarChartAll.setOpacity(0);

                    dataSeries1.getData().add(new XYChart.Data("January", fuel95.get("95January")));
                    dataSeries1.getData().add(new XYChart.Data("February", fuel95.get("95February")));
                    dataSeries1.getData().add(new XYChart.Data("March", fuel95.get("95March")));
                    dataSeries1.getData().add(new XYChart.Data("April", fuel95.get("95April")));
                    dataSeries1.getData().add(new XYChart.Data("May", fuel95.get("95May")));
                    dataSeries1.getData().add(new XYChart.Data("June", fuel95.get("95June")));
                    dataSeries1.getData().add(new XYChart.Data("July", fuel95.get("95July")));
                    dataSeries1.getData().add(new XYChart.Data("August", fuel95.get("95August")));
                    dataSeries1.getData().add(new XYChart.Data("September", fuel95.get("95September")));
                    dataSeries1.getData().add(new XYChart.Data("October", fuel95.get("95October")));
                    dataSeries1.getData().add(new XYChart.Data("November", fuel95.get("95November")));
                    dataSeries1.getData().add(new XYChart.Data("December", fuel95.get("95December")));

                    finalBarChart95.getData().add(dataSeries1);
                    finalBarChart95.setAnimated(false);

                }


                if (fuelTypeComboBox.getValue().toString().equals(fuelType98)) {
                    finalBarChart95.setOpacity(0);
                    finalBarChart98.setOpacity(100);
                    finalBarChartE85.setOpacity(0);
                    finalBarChartD.setOpacity(0);
                    finalbarChartAll.setOpacity(0);

                    dataSeries2.getData().add(new XYChart.Data("January", fuel98.get("98January")));
                    dataSeries2.getData().add(new XYChart.Data("February", fuel98.get("98February")));
                    dataSeries2.getData().add(new XYChart.Data("March", fuel98.get("98March")));
                    dataSeries2.getData().add(new XYChart.Data("April", fuel98.get("98April")));
                    dataSeries2.getData().add(new XYChart.Data("May", fuel98.get("98May")));
                    dataSeries2.getData().add(new XYChart.Data("June", fuel98.get("98June")));
                    dataSeries2.getData().add(new XYChart.Data("July", fuel98.get("98July")));
                    dataSeries2.getData().add(new XYChart.Data("August", fuel98.get("98August")));
                    dataSeries2.getData().add(new XYChart.Data("September", fuel98.get("98September")));
                    dataSeries2.getData().add(new XYChart.Data("October", fuel98.get("98October")));
                    dataSeries2.getData().add(new XYChart.Data("November", fuel98.get("98November")));
                    dataSeries2.getData().add(new XYChart.Data("December", fuel98.get("98December")));


                    finalBarChart98.getData().add(dataSeries2);
                    finalBarChart98.setAnimated(false);

                }


                if (fuelTypeComboBox.getValue().toString().equals(fuelTypeE85)) {
                    finalBarChart95.setOpacity(0);
                    finalBarChart98.setOpacity(0);
                    finalBarChartE85.setOpacity(100);
                    finalBarChartD.setOpacity(0);
                    finalbarChartAll.setOpacity(0);

                    dataSeries3.getData().add(new XYChart.Data("January", fuelE85.get("E85January")));
                    dataSeries3.getData().add(new XYChart.Data("February", fuelE85.get("E85February")));
                    dataSeries3.getData().add(new XYChart.Data("March", fuelE85.get("E85March")));
                    dataSeries3.getData().add(new XYChart.Data("April", fuelE85.get("E85April")));
                    dataSeries3.getData().add(new XYChart.Data("May", fuelE85.get("E85May")));
                    dataSeries3.getData().add(new XYChart.Data("June", fuelE85.get("E85June")));
                    dataSeries3.getData().add(new XYChart.Data("July", fuelE85.get("E85July")));
                    dataSeries3.getData().add(new XYChart.Data("August", fuelE85.get("E85August")));
                    dataSeries3.getData().add(new XYChart.Data("September", fuelE85.get("E85September")));
                    dataSeries3.getData().add(new XYChart.Data("October", fuelE85.get("E85October")));
                    dataSeries3.getData().add(new XYChart.Data("November", fuelE85.get("E85November")));
                    dataSeries3.getData().add(new XYChart.Data("December", fuelE85.get("E85December")));

                    finalBarChartE85.getData().add(dataSeries3);
                    finalBarChartE85.setAnimated(false);

                }

                if (fuelTypeComboBox.getValue().toString().equals(fuelTypeD)) {
                    finalBarChart95.setOpacity(0);
                    finalBarChart98.setOpacity(0);
                    finalBarChartE85.setOpacity(0);
                    finalBarChartD.setOpacity(100);
                    finalbarChartAll.setOpacity(0);

                    dataSeries4.getData().add(new XYChart.Data("January", fuelD.get("DJanuary")));
                    dataSeries4.getData().add(new XYChart.Data("February", fuelD.get("DFebruary")));
                    dataSeries4.getData().add(new XYChart.Data("March", fuelD.get("DMarch")));
                    dataSeries4.getData().add(new XYChart.Data("April", fuelD.get("DApril")));
                    dataSeries4.getData().add(new XYChart.Data("May", fuelD.get("DMay")));
                    dataSeries4.getData().add(new XYChart.Data("June", fuelD.get("DJune")));
                    dataSeries4.getData().add(new XYChart.Data("July", fuelD.get("DJuly")));
                    dataSeries4.getData().add(new XYChart.Data("August", fuelD.get("DAugust")));
                    dataSeries4.getData().add(new XYChart.Data("September", fuelD.get("DSeptember")));
                    dataSeries4.getData().add(new XYChart.Data("October", fuelD.get("DOctober")));
                    dataSeries4.getData().add(new XYChart.Data("November", fuelD.get("DNovember")));
                    dataSeries4.getData().add(new XYChart.Data("December", fuelD.get("DDecember")));

                    finalBarChartD.getData().add(dataSeries4);
                    finalBarChartD.setAnimated(false);


                }

                if (fuelTypeComboBox.getValue().toString().equals(fuelTypeAll)) {
                    finalBarChart95.setOpacity(0);
                    finalBarChart98.setOpacity(0);
                    finalBarChartE85.setOpacity(0);
                    finalBarChartD.setOpacity(0);
                    finalbarChartAll.setOpacity(100);

                    dataSeries5.getData().add(new XYChart.Data("January", fuelAll.get("AllJanuary")));
                    dataSeries5.getData().add(new XYChart.Data("February", fuelAll.get("AllFebruary")));
                    dataSeries5.getData().add(new XYChart.Data("March", fuelAll.get("AllMarch")));
                    dataSeries5.getData().add(new XYChart.Data("April", fuelAll.get("AllApril")));
                    dataSeries5.getData().add(new XYChart.Data("May", fuelAll.get("AllMay")));
                    dataSeries5.getData().add(new XYChart.Data("June", fuelAll.get("AllJune")));
                    dataSeries5.getData().add(new XYChart.Data("July", fuelAll.get("AllJuly")));
                    dataSeries5.getData().add(new XYChart.Data("August", fuelAll.get("AllAugust")));
                    dataSeries5.getData().add(new XYChart.Data("September", fuelAll.get("AllSeptember")));
                    dataSeries5.getData().add(new XYChart.Data("October", fuelAll.get("AllOctober")));
                    dataSeries5.getData().add(new XYChart.Data("November", fuelAll.get("AllNovember")));
                    dataSeries5.getData().add(new XYChart.Data("December", fuelAll.get("AllDecember")));

                    finalbarChartAll.getData().add(dataSeries5);
                    finalbarChartAll.setAnimated(false);

                }


            }

        });


        Scene scene = new Scene(root);
        root.getChildren().addAll(btnFile, fuelTypeComboBox, barChart95, barChart98, barChartE85, barChartD, barChartAll);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
