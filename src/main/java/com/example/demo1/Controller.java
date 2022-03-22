package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField x0;
    @FXML
    private TextField y0;
    @FXML
    private TextField Xn;
    @FXML
    private ComboBox i_comboBox;
    @FXML
    private Button i_confirmButton;
    @FXML
    private Label i_resultsInfo;
    @FXML
    private LineChart<String, Double> i_graph;

    @FXML
    private void initialize() {
    }

    @FXML
    private void computeIntegral(ActionEvent actionEvent) {
        if(checkFields()) {
            double x_start = Double.parseDouble(x0.getText());
            double y_start = Double.parseDouble(y0.getText());
            double x_end = Double.parseDouble(Xn.getText());
            double step;
            if (Double.parseDouble(i_comboBox.getValue().toString()) >= 0.0001 ) step = 0.1;
            else step = 0.01;
            RungeKuttaMethod rkm = new RungeKuttaMethod(x_start, y_start, step, x_end);
            MilneMethod mm = new MilneMethod(rkm.getValues(), step);
            i_graph.getData().clear();
            XYChart.Series<String, Double> series = new XYChart.Series<>();
            for (int i = 0; i < mm.getValues().size(); i++) {
                series.getData().add(new XYChart.Data<>(mm.getValues().get(i).getKey().toString(), mm.getValues().get(i).getValue()));
            }
            i_graph.getData().add(series);
            i_resultsInfo.setText("We have answer!\n");
        } else {
            i_resultsInfo.setText("You mistake, don't u?");
        }
    }

    private boolean checkFields() {
        return x0.getText().matches("-?((\\d*)|(\\d+\\.\\d*))") && !x0.getText().equals("") &&
                y0.getText().matches("-?((\\d*)|(\\d+\\.\\d*))") && !y0.getText().equals("") &&
                Xn.getText().matches("-?((\\d*)|(\\d+\\.\\d*))") && !Xn.getText().equals("") &&
                Double.parseDouble(x0.getText()) < Double.parseDouble(Xn.getText()) &&
                i_comboBox.getValue() != null;
    }

}
