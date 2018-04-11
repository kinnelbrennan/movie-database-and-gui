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
    //private  JTextField titleField = new JTextField(10);
    //private  JTextField releaseField = new JTextField(10);
    //private  JTextField lengthField = new JTextField(10);
    //private  JTextField ratingField = new JTextField(10);
    //private  JTextField plotField = new JTextField(10);

    // the DB
    private databaseConnection dbc;


    public GUI() {
        dbc = new databaseConnection();

        // buttons to provide functionality
        JButton queryMovies = new JButton("Query Movies"); //queries for data
        JButton queryActors = new JButton("Query Actors"); //queries for data
        JButton queryRoles = new JButton("Query Roles"); //queries for data
        JButton queryAwards = new JButton("Query Awards"); //queries for data
        JButton addMovieData = new JButton("Add Movie"); //add a movie
        JButton addActorData = new JButton("Add Actor"); //add an Actor
        JButton addRoleData = new JButton("Add Role"); //add a Role
        JButton addAwardData = new JButton("Add Award"); //add an Award
        JButton removeData = new JButton("Remove Row"); //remove a movie

        //Setting up the window
        setTitle("Movie Database");
        setSize(WIDTH, HEIGHT);

        // panel for looking up movies
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));

        bottom.add(queryMovies);
        bottom.add(queryActors);
        bottom.add(queryAwards);
        bottom.add(queryRoles);

        //Creating the panel for manipulating data
        JPanel left = new JPanel();

        GridBagLayout buttonLayout = new GridBagLayout();
        GridBagConstraints buttonLayoutConstraints = new GridBagConstraints();

        left.setLayout(buttonLayout);

        buttonLayoutConstraints.fill = GridBagConstraints.BOTH;
        buttonLayoutConstraints.gridwidth = GridBagConstraints.REMAINDER;

        buttonLayout.setConstraints(addMovieData, buttonLayoutConstraints);
        buttonLayout.setConstraints(removeData, buttonLayoutConstraints);

        left.setLayout(buttonLayout);
        left.add(addMovieData);
        left.add(addActorData);
        left.add(addAwardData);
        left.add(addRoleData);
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

        addMovieData.addActionListener(new AddMovieListener(this));

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

        queryMovies.addActionListener(new GetListenerMovie());
        /* When we first launch the UI we do not want it to look goofy so
           query on open
         */
        queryMovies.doClick();
    }

    //Just a get method for the databaseConnection
    public databaseConnection getDbc() {
        return dbc;
    }

    // get listener class (made it inner for simplicity sake)
    class GetListenerMovie implements ActionListener {
        public void actionPerformed(ActionEvent aEvent) {
            if (scrollPane != null)
                getContentPane().remove(scrollPane);
            //Query the data in our DB
            dbc.queryData(simpleQueryMovie());
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

        public String simpleQueryMovie() {
            return "select idfilm, Title, Release_Date, Length, imdbRating, Plot from Film";
        }
    }
    // get listener class for actors(inner for simplicity sake)
    class GetListenerActor implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (scrollPane != null)
                getContentPane().remove(scrollPane);
            dbc.queryData(simpleQueryActor());
            ResultSet rs = dbc.getRs();
            aModel = new ActorTableModel(rs);
            view = new JTable(aModel);
            view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            //Add all these results to the container and then display it
            scrollPane = new JScrollPane(view);
            getContentPane().add(scrollPane, BorderLayout.CENTER);
            pack();
            doLayout();
        }
        public String simpleQueryActor() {
            return "select idActors, Name, Birthday, Birthplace from Actors";
        }
    }
    class GetListenerAwards implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (scrollPane != null)
                getContentPane().remove(scrollPane);
            dbc.queryData(simpleQueryAwards());
            ResultSet rs = dbc.getRs();
            awModel = new AwardTableModel(rs);
            view = new JTable(awModel);
            view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            //Add all these results to the container and then display it
            scrollPane = new JScrollPane(view);
            getContentPane().add(scrollPane, BorderLayout.CENTER);
            pack();
            doLayout();
        }
        public String simpleQueryAwards() {
            return "select idAwards, Name, Category from Awards";
        }
    }
    class GetListenerRoles implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (scrollPane != null)
                getContentPane().remove(scrollPane);
            dbc.queryData(simpleQueryRoles());
            ResultSet rs = dbc.getRs();
            rModel = new RoleTableModel(rs);
            view = new JTable(rModel);
            view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            //Add all these results to the container and then display it
            scrollPane = new JScrollPane(view);
            getContentPane().add(scrollPane, BorderLayout.CENTER);
            pack();
            doLayout();
        }
        public String simpleQueryRoles() {
            return "select idWorks_on, Role, star, idActors, idFilm from Works_on";
        }
    }

}
