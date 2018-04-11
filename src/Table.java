import javax.swing.JFrame;

public class Table {

    //Main function to send data to the GUI and run the db
    public static void main(String[] args) {
        GUI graphic = new GUI();

        graphic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graphic.setVisible(true);
    }
}
