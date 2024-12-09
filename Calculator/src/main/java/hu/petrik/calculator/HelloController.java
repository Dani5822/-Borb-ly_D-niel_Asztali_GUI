package hu.petrik.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.stage.Popup;

public class HelloController {

    @FXML
    private Button minusz;
    @FXML
    private Button miltiply;
    @FXML
    private ColumnConstraints grid;
    @FXML
    private Text resoult;
    @FXML
    private Button szazalek;
    @FXML
    private Button plus;
    @FXML
    private Button devide;
    @FXML
    private Spinner<Double> szam2;
    @FXML
    private Spinner<Double> szam1;

    @FXML
    public void kivonas(ActionEvent actionEvent) {
        try{
        resoult.setText(( szam1.getValue()-szam2.getValue())+"");
        }catch (Exception e){
            System.out.println(e);
            showAlert("Hibás adat");
        }
    }

    public void initialize(){
        SpinnerValueFactory<Double> valueFactory1 = new SpinnerValueFactory.DoubleSpinnerValueFactory(-1000, 1000,0);
        szam1.setValueFactory(valueFactory1);
        SpinnerValueFactory<Double> valueFactory2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(-1000, 1000,0);
        szam2.setValueFactory(valueFactory2);
    }

    @FXML
    public void osszead(ActionEvent actionEvent) {
        try{
            resoult.setText(( szam1.getValue()+szam2.getValue())+"");
        }catch (Exception e){
            showAlert("Hibás adat");
        }
    }

    @FXML
    public void osztas(ActionEvent actionEvent) {
        try{
            resoult.setText(Math.round( (szam1.getValue()/szam2.getValue())*100)/100.0+"");
        }catch (Exception e){
            showAlert("Hibás adat");
        }
    }

    @FXML
    public void szazalek(ActionEvent actionEvent) {
        try{
            resoult.setText(Math.round(( szam1.getValue()/szam2.getValue())*10000)/100.0+"");
        }catch (Exception e){
            showAlert("Hibás adat");
        }
    }

    @FXML
    public void szorzas(ActionEvent actionEvent) {
        try{
            resoult.setText(( szam1.getValue()*szam2.getValue())+"");
        }catch (Exception x){
           showAlert("Hibás adat");

        }
    }

    private void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hiba");
        alert.setHeaderText("Hibás adat");
        alert.setContentText(message);
        alert.showAndWait();
    }
}