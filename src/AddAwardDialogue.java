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

public class AddAwardDialogue extends JDialog {
    //Text fields for each column
    private JTextField idAwardsField = new JTextField(2);
    private JTextField nameField = new JTextField(2);
    private JTextField categoryField = new JTextField(2);

    //Confirm the add
    private JButton addData;
    private JButton cancelAdd;

    public AddAwardDialogue(final JFrame lorde) {
        super(lorde, "Add Movie", true);
        setSize(300, 300);

        //create the panel
        JPanel middle = new JPanel();
        middle.setLayout(new GridLayout(3, 1));
        middle.add(new JLabel(" idAwards"));
        middle.add(idAwardsField);
        middle.add(new JLabel(" Name"));
        middle.add(nameField);
        middle.add(new JLabel(" Category"));
        middle.add(categoryField);

        //create the top panel where you can confirm/cancel
        JPanel top = new JPanel();
        addData = new JButton("Confirm");
        cancelAdd = new JButton("Cancel");
        addData.setEnabled(false);
        top.add(addData);
        top.add(cancelAdd);

        //Add listeners to the buttons
        idAwardsField.getDocument().addDocumentListener(new AddAwardDialogue.InputListener());
        nameField.getDocument().addDocumentListener(new AddAwardDialogue.InputListener());
        categoryField.getDocument().addDocumentListener(new AddAwardDialogue.InputListener());

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
        String idAwards = idAwardsField.getText().trim();
        String name = nameField.getText().trim();
        String category = categoryField.getText().trim();
        //SQL statement
        query = "insert into Awards values ('" + idAwards + "','"
                + name + "','"
                + category + "') ";
        return query;
    }

    // another inner class for simplicity
    class InputListener implements DocumentListener {
        @Override
        //Empty necessary for implementing doc listener
        public void changedUpdate(DocumentEvent e) { }

        @Override
        public void insertUpdate(DocumentEvent e) {
            if(nameField.getDocument().getLength() > 0) {
                addData.setEnabled(true);
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if(nameField.getDocument().getLength() == 0)
                addData.setEnabled(false);
        }
    }
}
