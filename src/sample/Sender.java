package sample;

import javafx.scene.control.TextArea;

/**
 * Created by Maxim on 26.02.2018.
 */
public class Sender {
    private int g;
    private int p;
    private int x;
    private int k;
    private int y;
    private int h;
    private int m;
    private int r;
    private int s;

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public void setH(int h) {
        this.h = h;
    }


    private char[] symbols;

    public int getS() {
        return s;
    }

    public int getR() {
        return r;
    }

    public int getM() {
        return m;
    }

    public Sender() {

    }

    public Sender(int p, int g, int k, int x, int h) {
        this.p = p;
        this.g = g;
        this.k = k;
        this.x = x;
        this.h = h;

    }

    public int findY() {
        y = (int) Math.pow(g, x);
        y = y % p;
        return y;
    }

    public void getHash() {

        r = (int) Math.pow(g, k);
        r = r % p;
        s = 1;
        while (s < p && (m != (x * r + k * s) % (p - 1))) {
            s++;
        }
    }

    public int findM(TextArea TextAreaText, String letters) {
        int symbol;
        m = 0;
        String text = TextAreaText.getText();
        symbols = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            symbols[i] = text.charAt(i);
            symbol = letters.indexOf(symbols[i]) + 1;
            h = ((h + symbol) * (h + symbol)) % p;
            m = h;
        }
        return m;

    }

}
