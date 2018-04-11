import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class MovieDocListener implements DocumentListener {
    private JTextField txt;
    private int numsAllowed;

    public MovieDocListener(JTextField txt, int numsAllowed) {
        this.txt = txt;
        this.numsAllowed = numsAllowed;
    }

    public void insertUpdate(DocumentEvent docEvent) {
        if(docEvent.getDocument().getLength() == numsAllowed)
            txt.transferFocus();
    }

    public void removeUpdate(DocumentEvent docEvent) {}
    public void changedUpdate(DocumentEvent docEvent) {}
}
