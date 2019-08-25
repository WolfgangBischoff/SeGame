package Core;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application
{
// https://stackoverflow.com/questions/33494052/javafx-redirect-console-output-to-textarea-that-is-created-in-scenebuilder
    public static void main(String[] args)  throws IOException
    {
        launch(args);
        //Simulation simulation = new Simulation();
        //simulation.runInterpreter();

    }

    @Override
    public void start(Stage gameWindow) throws Exception
    {
        Simulation simulation = new Simulation();
        gameWindow.setTitle("Se Game");
        //Game Scene Init

        //Interpreter Window
        Stage interpreterWindow = new Stage();
        interpreterWindow.setTitle("Se Game Interpreter");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label userName = new Label("Input:");
        grid.add(userName, 0, 0);
        Text out = new Text("Nothing here atm");
        grid.add(out, 1,2);
        TextField userTextField = new TextField();
        grid.add(userTextField, 0, 1);
        userTextField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    simulation.getInterpreter().readInstruction(userTextField.getText());
                    out.setText(userTextField.getText());
                    System.out.println("Console");
                }
            }
        });


        Scene interpreterScene = new Scene(grid, 300, 350);
        gameWindow.setScene(interpreterScene);
        gameWindow.show();



        interpreterWindow.setScene(interpreterScene);
        interpreterWindow.show();

    }
}
