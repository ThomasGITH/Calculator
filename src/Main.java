 import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
 
public class Main extends Application implements CalculationListener{
	
	CalculationManager calc = new CalculationManager();	
	
    public static void main(String[] args) {
    	
    	Main main = new Main();

    	/*
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
		*/
        launch(args);
       
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	calc.addCalculationListener(this);
    	
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);
        
        double width = 450, height = 600;
        
       // btn.setPrefSize(60, 60);
       /* btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
                
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        
        gridPane.setVgap(1);
        gridPane.setHgap(1);
        
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: gray;");
        canvas.setPrefSize(width, height / 3);
        GridPane.setColumnSpan(canvas, 5);    
        GridPane.setRowSpan(canvas, 2);  
        gridPane.add(canvas, 0, 0);
        
        Button[] digits = new Button[10];
        for(int i = 0; i < 10; i++)
        {
        	Button digit = new Button(Integer.toString(i));
        	digit.setPrefSize(width / 5, height / 6);
        	digits[i] = digit;
        }
        
        for(int j = 0; j < 3; j++)
        {
            for(int i = 0; i < 3; i++)
            {
            	int index = 1 + j * 3 + i;
            	gridPane.add(digits[index], i, 4 - j);
            	digits[index].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    	calc.addCharacter(Integer.toString(index));
                    }
                });
            }
        }
        gridPane.add(digits[0], 1, 5);
        digits[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	calc.addCharacter("0");
            }
        });
        
        String[] operations = {"÷", "x", "-", "+", ",", "="};
        for(int i = 0; i < 6; i++)
        {
        	Button operation = new Button(operations[i]);
        	int currentIndex = i;
        	operation.setOnAction(i != 5 ? new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	calc.addCharacter(operations[currentIndex]);
                }
            }:
            	new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	calc.calculate();
                }
            });
        	
        	operation.setPrefSize(width / 5, height / 6);
        	if(i < 4) {
        		gridPane.add(operation, 3, 2 + i);}
        	else {
        		gridPane.add(operation, i == 4 ? 0 : 2, 5);
        	}
        }
        
        for(int i = 0; i < 2; i++)
        {
        	Button miscOperation = new Button(i == 0 ? "<" : "C");
        	
        	miscOperation.setOnAction(i == 0 ? new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	calc.deleteLastCharacter();
                }
            }:
            	new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	calc.clear();
                }
            });
        	
        	GridPane.setRowSpan(miscOperation, 2);
            miscOperation.setPrefSize(width / 5, height / 3);
            gridPane.add(miscOperation, 4, 2 + i * 2);
        }
        
        Scene scene = new Scene(gridPane, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	@Override
	public void updateCalculation() {
		// TODO Auto-generated method stub
		System.out.println(calc.getCalculation());
	}
}