package sample;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.math.BigInteger;

public class Controller {

    @FXML
    private TextField TextFieldInteger;
    @FXML
    private TextField TextFieldPrimeNumber;
    @FXML
    private TextField TextFieldX;
    @FXML
    private TextField TextFieldK;
    @FXML
    private TextField TextFieldM;
    @FXML
    private TextField TextFieldH;
    @FXML
    private TextField TextFieldS;
    @FXML
    private TextField TextFieldR;
    @FXML
    private TextArea TextAreaText;
    @FXML
    private TextArea TextAreaTextForDecoding;

    public Sender sender = new Sender();
    private int g;
    private int p;
    private int x;
    private int k;
    private int y;
    private int h;

    private String letters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    @FXML
    void encryption(ActionEvent event) {
        x = Integer.parseInt(TextFieldX.getText());
        g = Integer.parseInt(TextFieldInteger.getText());
        p = Integer.parseInt(TextFieldPrimeNumber.getText());
        k = Integer.parseInt(TextFieldK.getText());
        h = Integer.parseInt(TextFieldH.getText());
        sender.setP(p);
        sender.setX(x);
        sender.setG(g);
        sender.setK(k);
        sender.setH(h);
        y = sender.findY();
        sender.findM(TextAreaText, letters);
        sender.getHash();
        TextFieldM.setText("" + sender.getM());
        TextFieldS.setText("" + sender.getS());
        TextFieldR.setText("" + sender.getR());
    }

    @FXML
    void decryption(ActionEvent event) {
        int s = sender.getS();
        int r = sender.getR();
        Recipient recipient = new Recipient(s, r);
        recipient.findM(TextAreaTextForDecoding, letters, h, p);
        int m = recipient.getM();
        int a = calculateA(r, s);
        int b = calculateB(m);
        checkMessage(a, b);
    }

    private int calculateB(int m) {
        int b = (int) Math.pow(g, m);
        b = b % p;
        return b;
    }

    private int calculateA(int r, int s) {
        int a;
        long rs = (long) Math.pow(r, s);
        BigInteger yBig = BigInteger.valueOf(y);
        BigInteger yr = yBig.pow(r);
        BigInteger aBig = yr.multiply(BigInteger.valueOf(rs));
        aBig = aBig.mod(BigInteger.valueOf(p));
        a = aBig.intValue();
        return a;
    }

    private void checkMessage(int a, int b) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Вывод");
        alert.setHeaderText(null);
        if (a == b) {
            alert.setContentText("Достоверность сообщения подтверждена!");
        } else {
            alert.setContentText("Достоверность сообщения нарушена!");

        }
        alert.showAndWait();
    }
}
