package Core;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
        Simulation simulation = Simulation.getSingleton();

        //Console Window
        Stage consoleWindow = new Stage();
        consoleWindow.setTitle("SeGame Console");
        consoleWindow.setScene(new Scene(simulation.getConsole()));
        consoleWindow.show();

        GuiSociety guiSociety = new GuiSociety();
        GuiPersonGrid guiPersonGrid = new GuiPersonGrid(new Person(new PersonName("Hans Hubertus"), 42, EducationalLayer.fromInt(2),12345));
        gameWindow.setScene(new Scene(guiSociety,600,400 ));

        gameWindow.setTitle("SeGame");
        gameWindow.show();

    }
}
