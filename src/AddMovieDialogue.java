import sun.jvm.hotspot.ui.action.ShowAction;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;


public class AddMovieDialogue extends JDialog {
    //Text fields for each column
    private JTextField idfilmField = new JTextField(2);
    private JTextField titleField = new JTextField(2);
    private JTextField releaseField = new JTextField(2);
    private JTextField lengthField = new JTextField(2);
    private JTextField ratingField = new JTextField(2);
    private JTextField plotField = new JTextField(2);
    //Confirm the add
    private JButton addData;
    private JButton cancelAdd;

    public AddMovieDialogue(final JFrame lorde) {
        super(lorde, "Add Movie", true);
        setSize(1440, 150);

        //create the panel
        JPanel middle = new JPanel();
        middle.setLayout(new GridLayout(1, 6));
        middle.add(new JLabel(" idFilm"));
        middle.add(idfilmField);
        middle.add(new JLabel(" Title"));
        middle.add(titleField);
        middle.add(new JLabel(" Release Date"));
        middle.add(releaseField);
        middle.add(new JLabel(" Length"));
        middle.add(lengthField);
        middle.add(new JLabel(" IMDB Rating"));
        middle.add(ratingField);
        middle.add(new JLabel(" Plot"));
        middle.add(plotField);

        //create the top panel where you can confirm/cancel
        JPanel top = new JPanel();
        addData = new JButton("Confirm");
        cancelAdd = new JButton("Cancel");
        addData.setEnabled(false);
        top.add(addData);
        top.add(cancelAdd);

        //Add listeners to the buttons
        idfilmField.getDocument().addDocumentListener(new InputListener());
        titleField.getDocument().addDocumentListener(new InputListener());
        releaseField.getDocument().addDocumentListener(new InputListener());
        lengthField.getDocument().addDocumentListener(new InputListener());
        ratingField.getDocument().addDocumentListener(new InputListener());
        plotField.getDocument().addDocumentListener(new InputListener());

        //listener for adding data
        addData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                databaseConnection lordeDB = ((GUI)lorde).getDbc();
                lordeDB.updateData(addQuery());
                dispose();
            }
        });

        //listening to close the window without update
        cancelAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        Container cp = getContentPane();
        cp.add(middle, BorderLayout.CENTER);
        cp.add(top, BorderLayout.NORTH);
    }

    public String addQuery() {
        String query = "";
        String idFilm = idfilmField.getText().trim();
        String title = titleField.getText().trim();
        String release = releaseField.getText().trim();
        String length = lengthField.getText().trim();
        String rating = ratingField.getText().trim();
        String plot = plotField.getText().trim();
        //SQL statement
        query = "insert into Film values ('" + idFilm + "','"
                + title + "','"
                + release + "','"
                + length + "','"
                + rating + "','"
                + plot + "') ";
        return query;
    }

        // another inner class for simplicity
        class InputListener implements DocumentListener {
            @Override
            //Empty necessary for implementing doc listener
            public void changedUpdate(DocumentEvent e) { }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if(titleField.getDocument().getLength() > 0) {
                    addData.setEnabled(true);
                    }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(titleField.getDocument().getLength() == 0)
                    addData.setEnabled(false);
            }
    }
}
