package sample;

import javafx.scene.control.TextArea;

/**
 * Created by Maxim on 02.03.2018.
 */
public class Recipient {
    private int m;
    private int s;
    private int r;

    private char[] symbols;

    public int getM() {
        return m;
    }

    public Recipient() {

    }

    public Recipient(int s, int r) {

        this.s = s;
        this.r = r;
    }

    public void findM(TextArea TextAreaText, String letters, int h, int p) {
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

    }

}
