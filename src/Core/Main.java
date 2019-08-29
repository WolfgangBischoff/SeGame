package Core;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    public void start(Stage gameWindow) throws IOException
    {
        Simulation simulation = Simulation.getSingleton();

        //Console Window
        Stage consoleWindow = new Stage();
        consoleWindow.setTitle("SeGame Console");
        consoleWindow.setScene(new Scene(simulation.getConsole()));
        consoleWindow.show();


        //Parent gameroot = FXMLLoader.load(getClass().getResource("../../resources/fxml/mainMenu.fxml"));
        //Scene gamescene = new Scene(gameroot, 300,275);
        //gameWindow.setScene(gamescene);

        GuiSociety guiSociety = new GuiSociety();
        GuiPersonGrid guiPersonGrid = new GuiPersonGrid(new Person(new PersonName("Hans Hubertus"), 42, EducationalLayer.fromInt(2),12345));
        gameWindow.setScene(new Scene(guiSociety,600,400 ));


        gameWindow.setTitle("SeGame");
        gameWindow.show();

    }
}
