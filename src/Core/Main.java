package Core;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
// https://stackoverflow.com/questions/33494052/javafx-redirect-console-output-to-textarea-that-is-created-in-scenebuilder
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage gameWindow)
    {
        Simulation simulation = new Simulation();

        //Console Window
        Stage consoleWindow = new Stage();
        consoleWindow.setTitle("SeGame Console");
        consoleWindow.setScene(new Scene(simulation.getInterpreter().getConsole()));
        consoleWindow.show();

        //gameWindow.show();

    }
}
