import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GUI extends JFrame {
    // height in pixels
    private static final int HEIGHT = 900;

    //width in pixels
    private static final int WIDTH = 1440;

    // gives us a cell format for the result set
    private JTable view;
    private MovieTableModel mModel;

    // so we can scroll through the results
    private JScrollPane scrollPane;

    // to enter varios types of data
    private  JTextField titleField = new JTextField(10);
    private  JTextField releaseField = new JTextField(10);
    private  JTextField lengthField = new JTextField(10);
    private  JTextField ratingField = new JTextField(10);
    private  JTextField plotField = new JTextField(10);

    // the DB
    private databaseConnection dbc;


    public GUI() {
        dbc = new databaseConnection();

        // buttons to provide functionality
        JButton query = new JButton("Query"); //queries for data
        JButton addData = new JButton("+"); //add a movie
        JButton removeData = new JButton("-"); //remove a movie

        //Setting up the window
        setTitle("Movie Database");
        setSize(WIDTH, HEIGHT);

        // panel for looking up movies
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));

        bottom.add(new JLabel("Title: "));
        bottom.add(titleField);
        bottom.add(new JLabel(" Release Date:"));
        bottom.add(releaseField);
        bottom.add(new JLabel(" Runtime:"));
        bottom.add(lengthField);
        bottom.add(new JLabel(" IMDB Rating:"));
        bottom.add(ratingField);
        bottom.add(new JLabel(" Plot:"));
        bottom.add(plotField);
        bottom.add(query);

        //Creating the panel for manipulating data
        JPanel left = new JPanel();

        GridBagLayout buttonLayout = new GridBagLayout();
        GridBagConstraints buttonLayoutConstraints = new GridBagConstraints();

        left.setLayout(buttonLayout);

        buttonLayoutConstraints.fill = GridBagConstraints.BOTH;
        buttonLayoutConstraints.gridwidth = GridBagConstraints.REMAINDER;

        buttonLayout.setConstraints(addData, buttonLayoutConstraints);
        buttonLayout.setConstraints(removeData, buttonLayoutConstraints);

        left.setLayout(buttonLayout);
        left.add(addData);
        left.add(removeData);

        // add the panels to our container
        Container cP = getContentPane();
        cP.add(bottom, BorderLayout.SOUTH);
        cP.add(left, BorderLayout.WEST);

        // Action listeners, close connection when we close the application
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dbc.close(true);
            }
        });

        addData.addActionListener(new AddMovieListener(this));

        removeData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selected = view.getSelectedRow();
                    ResultSet rs = dbc.getRs();
                    if(selected != -1 && selected < mModel.getRowCount()) {
                        rs.absolute(view.getSelectedRow() + 1);
                        rs.deleteRow();
                        view.repaint();
                        view.clearSelection();
                    }
                    } catch(SQLException r) {
                    System.out.println("SQL Exception thrown:");
                    r.printStackTrace();
                }
            }
        });

        query.addActionListener(new GetListener());
        /* When we first launch the UI we do not want it to look goofy so
           query on open
         */
        query.doClick();
        titleField.requestFocus(); //So the title is the mose obvious
    }

    //Just a get method for the databaseConnection
    public databaseConnection getDbc() {
        return dbc;
    }

    // get listener class (made it inner for simplicity sake)
    class GetListener implements ActionListener {
        public void actionPerformed(ActionEvent aEvent) {
            //Query the data in our DB
            dbc.queryData(simpleQuery());
            ResultSet rs = dbc.getRs();
            //Set up the tables
            mModel = new MovieTableModel(rs);
            view = new JTable(mModel);
            // so you can only delete one record at a time
            view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            //Add all these results to the container and then display it
            scrollPane = new JScrollPane(view);
            getContentPane().add(scrollPane, BorderLayout.CENTER);
            pack();
            doLayout();
        }

        public String simpleQuery() {
            return "select idfilm, Title, Release_Date, Length, imdbRating, Plot from Film";
        }
    }

}
