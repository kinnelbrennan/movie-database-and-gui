import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoleListener implements ActionListener {
    // The application frame
    GUI movieFrame;
    //constructor
    public AddRoleListener(GUI movieFrame) {
        this.movieFrame = movieFrame;
    }

    public void actionPerformed(ActionEvent aEvent) {
        AddRoleDialogue addDialogue = new AddRoleDialogue(movieFrame);
        addDialogue.setVisible(true);
    }
}
