 
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
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
    	
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);
       // btn.setPrefSize(60, 60);
       /* btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
                
        Button[] digits = new Button[10];
        for(int i = 0; i < 10; i++)
        {
        	Button digit = new Button(Integer.toString(i));
        	digit.setPrefSize(450 / 5, 600 / 6);
        	digits[i] = digit;
        }
        
        System.out.println(Screen.getPrimary().getBounds().getWidth());
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        
        //root.setVgap(10);
        //root.setHgap(10);
        

        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: gray;");
        canvas.setPrefSize(digits[0].getPrefWidth() * 5, digits[0].getPrefHeight() * 2);
        GridPane.setColumnSpan(canvas, 5);    
        GridPane.setRowSpan(canvas, 2);        
        root.add(canvas, 0, 0);

        root.add(digits[7], 0, 2);
        root.add(digits[8], 1, 2);
        root.add(digits[9], 2, 2);
        Button divide = new Button("÷");
        divide.setPrefSize(digits[0].getPrefWidth(), digits[0].getPrefHeight());
        root.add(divide, 3, 2);
        
        root.add(digits[4], 0, 3);
        root.add(digits[5], 1, 3);
        root.add(digits[6], 2, 3);
        Button multiply = new Button("x");
        multiply.setPrefSize(digits[0].getPrefWidth(), digits[0].getPrefHeight()); 
        root.add(multiply, 3, 3);
        
        root.add(digits[1], 0, 4);
        root.add(digits[2], 1, 4);
        root.add(digits[3], 2, 4);
        Button minus = new Button("-");
        minus.setPrefSize(digits[0].getPrefWidth(), digits[0].getPrefHeight());
        root.add(minus, 3, 4);
        
        
        Button back = new Button("<");
        GridPane.setRowSpan(back,2);
        back.setPrefSize(digits[0].getPrefWidth(), digits[0].getPrefHeight() * 2);
        root.add(back, 4, 2);
        
        Button comma = new Button(",");
        comma.setPrefSize(digits[0].getPrefWidth(), digits[0].getPrefHeight());
        root.add(comma, 0, 5);

        root.add(digits[0], 1, 5);
        
        Button equals = new Button("=");
        equals.setPrefSize(digits[0].getPrefWidth(), digits[0].getPrefHeight());
        root.add(equals, 2, 5);
        
        Button plus = new Button("+");
        plus.setPrefSize(digits[0].getPrefWidth(), digits[0].getPrefHeight());
        root.add(plus, 3, 5);
        
        Button C = new Button("C");
        GridPane.setRowSpan(C,2);
        C.setPrefSize(digits[0].getPrefWidth(), digits[0].getPrefHeight() * 2);
        root.add(C, 4, 4);
                        
        Scene scene = new Scene(root, 450,600);
        primaryStage.setScene(scene);
        
        for(int i = 0; i < 10; i++)
        {
        	digits[i].setPrefSize(scene.getWidth() / 5, scene.getHeight() / 6);
        }
        
        primaryStage.show();
    }

	@Override
	public void updateCalculation() {
		// TODO Auto-generated method stub
		System.out.println(calc.getCalculation());
	}
}