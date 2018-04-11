import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAwardListener implements ActionListener {
    // The application frame
    GUI movieFrame;
    //constructor
    public AddAwardListener(GUI movieFrame) {
        this.movieFrame = movieFrame;
    }

    public void actionPerformed(ActionEvent aEvent) {
        AddAwardDialogue addDialogue = new AddAwardDialogue(movieFrame);
        addDialogue.setVisible(true);
    }
}
