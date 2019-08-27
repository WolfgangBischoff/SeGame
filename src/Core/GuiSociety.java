package Core;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GuiSociety extends GridPane
{
    Simulation simulation;
    Society society;

    Text basedata;

    public GuiSociety()
    {
        simulation = Simulation.getSingleton();
        society = simulation.society;

        Text headline = new Text("Society Overview");
        headline.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        add(headline, 0,0);

        basedata = new Text(society.printSocStatistics());
        add(basedata, 0,1);

        Button calc = new Button("Refresh");
        calc.setOnAction(
                (none) -> calc()
        );
        add(calc, 1,2);
    }

    public void calc()
    {
        basedata.setText(society.printSocStatistics());
    }
}
