import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddActorListener implements ActionListener {
    // The application frame
    GUI movieFrame;
    //constructor
    public AddActorListener(GUI movieFrame) {
        this.movieFrame = movieFrame;
    }

    public void actionPerformed(ActionEvent aEvent) {
        AddActorDialogue addDialogue = new AddActorDialogue(movieFrame);
        addDialogue.setVisible(true);
    }
}
