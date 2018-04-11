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

public class AddRoleDialogue extends JDialog {
    //Text fields for each column
    private JTextField idWorks_OnField = new JTextField(2);
    private JTextField roleField = new JTextField(2);
    private JTextField starField = new JTextField(2);
    private JTextField idActorsField = new JTextField(2);
    private JTextField idFilmField = new JTextField(2);
    //Confirm the add
    private JButton addData;
    private JButton cancelAdd;

    public AddRoleDialogue(final JFrame lorde) {
        super(lorde, "Add Movie", true);
        setSize(300, 300);

        //create the panel
        JPanel middle = new JPanel();
        middle.setLayout(new GridLayout(5, 1));
        middle.add(new JLabel(" idWorks_On"));
        middle.add(idWorks_OnField);
        middle.add(new JLabel(" Role"));
        middle.add(roleField);
        middle.add(new JLabel(" Star"));
        middle.add(starField);
        middle.add(new JLabel(" Actor ID"));
        middle.add(idActorsField);
        middle.add(new JLabel(" Film ID"));
        middle.add(idFilmField);

        //create the top panel where you can confirm/cancel
        JPanel top = new JPanel();
        addData = new JButton("Confirm");
        cancelAdd = new JButton("Cancel");
        addData.setEnabled(false);
        top.add(addData);
        top.add(cancelAdd);

        //Add listeners to the buttons
        idWorks_OnField.getDocument().addDocumentListener(new AddRoleDialogue.InputListener());
        roleField.getDocument().addDocumentListener(new AddRoleDialogue.InputListener());
        starField.getDocument().addDocumentListener(new AddRoleDialogue.InputListener());
        idActorsField.getDocument().addDocumentListener(new AddRoleDialogue.InputListener());
        idFilmField.getDocument().addDocumentListener(new AddRoleDialogue.InputListener());

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
        String idWorks_on = idWorks_OnField.getText().trim();
        String role = roleField.getText().trim();
        String star = starField.getText().trim();
        String idActors = idActorsField.getText().trim();
        String idFilm = idFilmField.getText().trim();
        //SQL statement
        query = "insert into Works_on values ('" + idWorks_on + "','"
                + role + "','"
                + star + "','"
                + idActors + "','"
                + idFilm + "') ";
        return query;
    }

    // another inner class for simplicity
    class InputListener implements DocumentListener {
        @Override
        //Empty necessary for implementing doc listener
        public void changedUpdate(DocumentEvent e) { }

        @Override
        public void insertUpdate(DocumentEvent e) {
            if(roleField.getDocument().getLength() > 0) {
                addData.setEnabled(true);
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if(roleField.getDocument().getLength() == 0)
                addData.setEnabled(false);
        }
    }
}
