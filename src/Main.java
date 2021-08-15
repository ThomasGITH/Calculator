 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class Main extends Application implements CalculationListener{
	
	CalculationManager calc = new CalculationManager();	
	
    public static void main(String[] args) {
    	
    	Main main = new Main();

    	main.calc.addCalculationListener(main);
		main.calc.addCharacter("-");
		main.calc.addCharacter("2");
		main.calc.addCharacter("8");
		main.calc.addCharacter("+");
		main.calc.addCharacter("5");
		main.calc.addCharacter("6");
		main.calc.addCharacter("/");
		main.calc.addCharacter("3");
		main.calc.addCharacter("9");
		main.calc.calculate();
		
        launch(args);
       
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

	@Override
	public void updateCalculation() {
		// TODO Auto-generated method stub
		System.out.println(calc.getCalculation());
	}
}