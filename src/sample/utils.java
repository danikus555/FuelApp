package sample;


import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/*
* Daniil Titov 27.06.2018
* Test
* Fuel App
*
* Class, that has methods to open, read, parse, calculate and store data for showing on Bar Chart
*
* */

public class utils {

    private static String fileNameOpened = null;
    protected static HashMap<String, Double> fuel95 = new HashMap<String, Double>();
    protected static HashMap<String, Double> fuel98 = new HashMap<String, Double>();
    protected static HashMap<String, Double> fuelE85 = new HashMap<String, Double>();
    protected static HashMap<String, Double> fuelD = new HashMap<String, Double>();
    protected static HashMap<String, Double> fuelAll = new HashMap<String, Double>();

    protected static String fuelType95 = "95";
    protected static String fuelTypeE85 = "E85";
    protected static String fuelType98 = "98";
    protected static String fuelTypeD = "D";
    protected static String fuelTypeAll = "All";


    public static String[] fuelData;


    public static void setFileNameOpened(String filename) {
        fileNameOpened = filename;
    }

    public static String getFileNameOpened() {
        return fileNameOpened;
    }


    public static void readFileData(String fileName) throws FileNotFoundException {

        File txt = new File(fileName);
        Scanner scan = new Scanner(txt);
        ArrayList<String> data = new ArrayList<String>();
        while (scan.hasNextLine()) {
            data.add(scan.nextLine());
        }
        System.out.println(data);
        String[] simpleArray = data.toArray(new String[]{});
        System.out.println(simpleArray.length);
        System.out.println(simpleArray.toString());
        fuelData = simpleArray;

    }


    public static void calculatedFuelData(String[] fuelData) {
        String[] elements;


        try {

            elements = fuelData;

            for (int i = 0; i < elements.length; i++) {
                System.out.println(elements[i]);

                String name = elements[i].replaceAll(" ", "").replaceAll(",", ".");
                System.out.println("name  " + name);
                String[] fuelType = name.split("\\|");
                System.out.println(fuelType[0]);

                if (fuelType[0].equals(fuelType95)) {
                    findAllRecordsForDifferentFuelTypes(fuelType, fuelType95);
                }

                if (fuelType[0].equals(fuelType98)) {
                    findAllRecordsForDifferentFuelTypes(fuelType, fuelType98);
                }

                if (fuelType[0].equals(fuelTypeE85)) {
                    findAllRecordsForDifferentFuelTypes(fuelType, fuelTypeE85);
                }
                if (fuelType[0].equals(fuelTypeD)) {
                    findAllRecordsForDifferentFuelTypes(fuelType, fuelTypeD);
                }

            }

        } catch (Exception e) {
            System.out.println(" Error in calculating data from file: " + e);
        }


    }

    //date with word month: like 'Apr'
    public static String stringDateFormat(String text) {
        String partText = text;

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");


        try {

            Date date = formatter.parse(partText);
            System.out.println("Month check" + date);
            partText = date.toString();

        } catch (Exception e) {
            System.out.println("Date convertion error: " + e);
        }


        return partText;


    }

    //format numbers like 1.111
    public static double amountFormat(double value) {

        double scale = Math.pow(10, 3);
        return Math.round(value * scale) / scale;
    }

    //create empty data storage with 0.000 values
    public static void createEmptyFuelMap() {

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        for (int i = 0; i < months.length; i++) {

            fuel95.put(fuelType95 + months[i], amountFormat(0.000));

            System.out.println("hashmap filling " + fuel95);
        }

        for (int i = 0; i < months.length; i++) {

            fuelE85.put(fuelTypeE85 + months[i], 0.000);
        }

        for (int i = 0; i < months.length; i++) {

            fuelD.put(fuelTypeD + months[i], 0.000);
        }

        for (int i = 0; i < months.length; i++) {

            fuel98.put(fuelType98 + months[i], 0.000);
        }

        for (int i = 0; i < months.length; i++) {

            fuelAll.put(fuelTypeAll + months[i], 0.000);
        }

    }

    //parse month to name 'Apr' --> 'April'
    public static String monthName(String month) {

        String monthName = null;
        String monthFromRequest = month;

        try {

            if (monthFromRequest.equals("Jan")) {

                monthName = "January";
            }

            if (monthFromRequest.equals("Feb")) {

                monthName = "February";
            }

            if (monthFromRequest.equals("Mar")) {

                monthName = "March";
            }

            if (monthFromRequest.equals("Apr")) {

                monthName = "April";
            }

            if (monthFromRequest.equals("May")) {

                monthName = "May";
            }

            if (monthFromRequest.equals("Jun")) {

                monthName = "June";
            }

            if (monthFromRequest.equals("Jul")) {

                monthName = "July";
            }

            if (monthFromRequest.equals("Aug")) {

                monthName = "August";
            }

            if (monthFromRequest.equals("Sep")) {

                monthName = "September";
            }

            if (monthFromRequest.equals("Oct")) {

                monthName = "October";
            }

            if (monthFromRequest.equals("Nov")) {

                monthName = "November";
            }

            if (monthFromRequest.equals("Dec")) {

                monthName = "December";
            }


        } catch (Exception e) {

            System.out.println("month name error: " + e);
        }


        return monthName;

    }

    //save fuel data from string to Data Storage (HashMap)
    public static void saveFuelAmount(String fuelType, Double amount, String month) {

        String fuel = fuelType;
        String monthFromRequest = month;
        Double sum = amount;

        try {


            if (fuel == fuelType95) {

                double oldAmount = fuel95.get(fuel + monthName(monthFromRequest));
                fuel95.put(fuel + monthName(monthFromRequest), oldAmount + sum);

                System.out.println("answer from hashmap: " + fuel95.get(fuel + monthName(monthFromRequest)));

            }

            if (fuel == fuelType98) {

                double oldAmount = fuel98.get(fuel + monthName(monthFromRequest));
                fuel98.put(fuel + monthName(monthFromRequest), oldAmount + sum);

                System.out.println("answer from hashmap: " + fuel98.get(fuel + monthName(monthFromRequest)));
            }

            if (fuel == fuelTypeE85) {

                double oldAmount = fuelE85.get(fuel + monthName(monthFromRequest));
                fuelE85.put(fuel + monthName(monthFromRequest), oldAmount + sum);

                System.out.println("answer from hashmap: " + fuelE85.get(fuel + monthName(monthFromRequest)));
            }

            if (fuel == fuelTypeD) {

                double oldAmount = fuelD.get(fuel + monthName(monthFromRequest));
                fuelD.put(fuel + monthName(monthFromRequest), oldAmount + sum);

                System.out.println("answer from hashmap: " + fuelD.get(fuel + monthName(monthFromRequest)));

            }

            if (fuel == fuelTypeAll) {

                double oldAmount = fuelAll.get(fuel + monthName(monthFromRequest));
                fuelAll.put(fuel + monthName(monthFromRequest), oldAmount + sum);

                System.out.println("answer from hashmap: " + fuelAll.get(fuel + monthName(monthFromRequest)));

            }


        } catch (Exception e) {
            System.out.println("save fuel amount error: " + e);
        }

    }


    public static void calculateAmount(String[] fuelType, String fuel, String monthName) {

        String monthABC = monthName;
        String[] fuelTypeArray = fuelType;
        String whatFuel = fuel;


        try {

            Double fuelPriceForL = 0.0;
            Double fuelAmount = 0.0;

            if (fuelType[1] != null && (Double.parseDouble(fuelType[1]) > 0)) {

                fuelPriceForL = Double.valueOf(fuelType[1]);

            }

            if (fuelType[2] != null && (Double.parseDouble(fuelType[2]) > 0)) {

                fuelAmount = Double.valueOf(fuelType[2]);

            }


            if (fuelAmount != null && fuelPriceForL != null) {

                Double amountTotal = amountFormat(fuelPriceForL * fuelAmount);
                System.out.println("Total amount in EUROS: " + amountTotal);

                saveFuelAmount(whatFuel, amountTotal, monthABC);

            }

            //System.out.println("95 fuel za Jan " +name);

        } catch (Exception e) {
            System.out.println("amount calc error: " + e);
        }


    }

    // find all records in file
    public static void findAllRecordsForDifferentFuelTypes(String[] fuelType, String fuelTypes) {
        try {

            //fuel Type January
            if (stringDateFormat(fuelType[3]).contains("Jan")) {
                calculateAmount(fuelType, fuelTypes, "Jan");
            }
            //fuel Type February
            if (stringDateFormat(fuelType[3]).contains("Feb")) {
                calculateAmount(fuelType, fuelTypes, "Feb");
            }
            // fuel Type March
            if (stringDateFormat(fuelType[3]).contains("Mar")) {
                calculateAmount(fuelType, fuelTypes, "Mar");
            }
            // fuel Type April
            if (stringDateFormat(fuelType[3]).contains("Apr")) {
                calculateAmount(fuelType, fuelTypes, "Apr");
            }
            // fuel Type May
            if (stringDateFormat(fuelType[3]).contains("May")) {
                calculateAmount(fuelType, fuelTypes, "May");
            }
            // fuel Type June
            if (stringDateFormat(fuelType[3]).contains("Jun")) {
                calculateAmount(fuelType, fuelTypes, "Jun");
            }
            // fuel Type July
            if (stringDateFormat(fuelType[3]).contains("Jul")) {
                calculateAmount(fuelType, fuelTypes, "Jul");
            }
            // fuel Type August
            if (stringDateFormat(fuelType[3]).contains("Aug")) {
                calculateAmount(fuelType, fuelTypes, "Aug");
            }
            // fuel Type September
            if (stringDateFormat(fuelType[3]).contains("Sep")) {
                calculateAmount(fuelType, fuelTypes, "Sep");
            }
            // fuel Type October
            if (stringDateFormat(fuelType[3]).contains("Oct")) {
                calculateAmount(fuelType, fuelTypes, "Oct");
            }
            // fuel Type November
            if (stringDateFormat(fuelType[3]).contains("Nov")) {
                calculateAmount(fuelType, fuelTypes, "Nov");
            }
            // fuel Type Dec
            if (stringDateFormat(fuelType[3]).contains("Dec")) {
                calculateAmount(fuelType, fuelTypes, "Dec");
            }

        } catch (Exception e) {
            System.out.println("Find all records and amount for fuel error " + e);
        }

    }

    //data, that will be used in combobox ALL
    public static void calculateAllAmountForAllFuelTypes() {

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        try {

            for (int i = 0; i < months.length; i++) {

                Double amountTotalEuros = fuelAll.get(fuelTypeAll + months[i]) + fuel95.get(fuelType95 + months[i]) + fuel98.get(fuelType98 + months[i]) + fuelE85.get(fuelTypeE85 + months[i]) + fuelD.get(fuelTypeD + months[i]);
                fuelAll.put(fuelTypeAll + months[i], amountFormat(amountTotalEuros));

                System.out.println("ALL fuel types amount value " + fuelAll);
            }

        } catch (Exception e) {

            System.out.println("Total amount in EUR for all fuel types error " + e);

        }


    }

    //Before opening another file clean all previous data.
    public static void cleanFuelDataMap() {

        try {

            fuel95.clear();
            fuel98.clear();
            fuelD.clear();
            fuelE85.clear();
            fuelAll.clear();

        } catch (Exception e) {

            System.out.println("clean Fuel Data Map error: " + e);
        }

    }

    public static BarChart fuelTypes() {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Amount EUR");
        BarChart barChart = new BarChart(xAxis, yAxis);

        return barChart;
    }

}
