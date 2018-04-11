import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMovieListener implements ActionListener {
    // The application frame
    GUI movieFrame;
    //constructor
    public AddMovieListener(GUI movieFrame) {
        this.movieFrame = movieFrame;
    }

    public void actionPerformed(ActionEvent aEvent) {
        AddMovieDialogue addDialogue = new AddMovieDialogue(movieFrame);
        addDialogue.setVisible(true);
    }
}
