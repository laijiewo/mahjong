package Display;

import javafx.stage.Stage;

/**
 * This interface defines the methods that are required for the objects that will be used to display information on the screen. This includes the menu, about
 * screen, rules screen, and the game screen.
 * @author Jingwang Li
 */
public interface Screen {
    /**
     * Loads the window on the specified stage. This method is responsible for setting up the user interface elements
     * on the stage, preparing it to be displayed. This could involve configuring scene size, adding components,
     * and setting up event handlers depending on the specific requirements of the screen.
     *
     * @param stage The primary stage on which the screen will be displayed. This stage is typically passed from the
     *              main application and reused for different screens.
     */
    public void loadWindow(Stage stage);
}
