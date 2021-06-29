package Controller;


import Exceptions.DivideByZeroPolynomialException;
import Exceptions.InvalidDataInputException;
import Model.OperationModel;
import Model.ParsingDataModel;
import Model.Polynomial.Polynomial;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    OperationModel operationModel;
    ParsingDataModel parsingDataModel;
    @FXML
    private TextField pTextField;
    @FXML
    private TextField qTextField;
    @FXML
    private TextField resultTextField;
    @FXML
    private ImageView image1;
    @FXML
    private MediaView mediaView;

    public Controller() {
        this.operationModel = new OperationModel();
        this.parsingDataModel = new ParsingDataModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImage1();
        setMediaView();
    }

    //Implement buttons action

    public void addButtonAction() {
        try {
            resultTextField.setStyle("-fx-text-fill: black;");
            Polynomial[] p = getTextFields();
            Polynomial sum = operationModel.addsPolynomials(p[0], p[1]);
            sum = new Polynomial(operationModel.convertToListOfIntegerMonomial(sum.getTerms()));
            resultTextField.setText(sum.toString());
        } catch (InvalidDataInputException e) {
            resultTextField.setText(e.getMessage());
            resultTextField.setStyle("-fx-text-fill: red;");
        }
    }

    public void decreaseButtonAction() {
        try {
            resultTextField.setStyle("-fx-text-fill: black;");
            Polynomial[] p = getTextFields();
            Polynomial difference = operationModel.decreasesPolynomials(p[0], p[1]);
            difference = new Polynomial(operationModel.convertToListOfIntegerMonomial(difference.getTerms()));
            resultTextField.setText(difference.toString());
        } catch (InvalidDataInputException e) {
            resultTextField.setText(e.getMessage());
            resultTextField.setStyle("-fx-text-fill: red;");
        }
    }

    public void multiplyButtonAction() {
        try {
            resultTextField.setStyle("-fx-text-fill: black;");
            Polynomial[] p = getTextFields();
            Polynomial result = operationModel.multipliesPolynomials(p[0], p[1]);
            result = new Polynomial(operationModel.convertToListOfIntegerMonomial(result.getTerms()));
            resultTextField.setText(result.toString());
        } catch (InvalidDataInputException e) {
            resultTextField.setText(e.getMessage());
            resultTextField.setStyle("-fx-text-fill: red;");
        }
    }

    public void divideButtonAction() {
        try {
            resultTextField.setStyle("-fx-text-fill: black;");
            Polynomial[] p = getTextFields();
            Polynomial[] result = operationModel.dividesPolynomials(p[0], p[1]);
            if (result[1].getTerms().get(0).getCoefficient() == 0)
                resultTextField.setText(result[0].toString());
            else
                resultTextField.setText(result[0].toString() + " + (" + result[1] + ")/(" + p[1] + ")");
        } catch (DivideByZeroPolynomialException e) {
            resultTextField.setText("!" + e.getMessage());
            resultTextField.setStyle("-fx-text-fill: red;");
        } catch (InvalidDataInputException e) {
            resultTextField.setText(e.getMessage());
            resultTextField.setStyle("-fx-text-fill: red;");
        }

    }

    public void deriveButtonAction() {
        try {
            resultTextField.setStyle("-fx-text-fill: black;");
            resultTextField.setText(operationModel.derivesPolynomial(getPTextField()).toString());
        } catch (InvalidDataInputException e) {
            resultTextField.setText(e.getMessage());
            resultTextField.setStyle("-fx-text-fill: red;");
        }
    }

    public void integrateButtonAction() {
        try {
            resultTextField.setStyle("-fx-text-fill: black;");
            resultTextField.setText(operationModel.integratesPolynomial(getPTextField()).toString());
        } catch (InvalidDataInputException e) {
            resultTextField.setText(e.getMessage());
            resultTextField.setStyle("-fx-text-fill: red;");
        }
    }

    public void resetButtonAction() {
        pTextField.setText("");
        qTextField.setText("");
        resultTextField.setText("");
    }

    //Extract data from TextFields

    public Polynomial[] getTextFields() throws InvalidDataInputException {
        Polynomial[] polynomials = new Polynomial[2];
        polynomials[0] = getPTextField();
        polynomials[1] = getQTextField();
        return polynomials;
    }

    public Polynomial getPTextField() throws InvalidDataInputException {
        return parsingDataModel.parseStingToPolynomial(pTextField.getText());
    }

    public Polynomial getQTextField() throws InvalidDataInputException {
        return parsingDataModel.parseStingToPolynomial(qTextField.getText());
    }

    //setters

    private void setImage1() {
        File file1 = new File("./src/Main/view/calculator.png");
        Image image2 = new Image(file1.toURI().toString());
        image1.setImage(image2);
    }

    private void setMediaView() {
        String newFile = new File("./src/Main/View/_School17_preview.mp4").getAbsolutePath();
        //String newFile = new File("./src/Main.Main.View/Fly_Through_Formulas_Black.mp4").getAbsolutePath();
        Media media = new Media(new File(newFile).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setPreserveRatio(false);
        mediaView.setMediaPlayer(player);
        player.play();
        player.setAutoPlay(true);
        player.setCycleCount(Integer.MAX_VALUE);
    }
}
