 import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
 
public class Main extends Application implements CalculationListener{
	
	private CalculationManager calc = new CalculationManager();	
    private Text displayText = new Text(calc.getCalculation());

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	calc.addCalculationListener(this);
    	
        double width = 450, height = 600;
        primaryStage.setTitle("Simple Calculator v1.0");
        primaryStage.setResizable(false);
        
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(1);
        gridPane.setHgap(1);
        
        Pane display = new Pane();
        display.setStyle("-fx-background-color: #9B9BA1;");
        display.setPrefSize(width, height / 3);
        GridPane.setColumnSpan(display, 5);    
        GridPane.setRowSpan(display, 2);  
        gridPane.add(display, 0, 0);
        
        Font displayTextFont = Font.loadFont(getClass().getResourceAsStream("fonts/digital-7.ttf"), 25);
        displayText.setFont(displayTextFont);
        displayText.setScaleX(3);
        displayText.setScaleY(3);
        displayText.setX(width / 2.8);
        displayText.setY(height / 6);
        display.getChildren().add(displayText);
        displayText.setTextAlignment(TextAlignment.LEFT);
        displayText.setWrappingWidth(width * 0.3);
        
        Font buttonFont = Font.font("Verdana Pro", 25);

        Button[] digits = new Button[10];
        for(int i = 0; i < 10; i++)
        {
        	Button digit = new Button(Integer.toString(i));
        	digit.setFont(buttonFont);
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
        
        String[] operations = {"/", "x", "-", "+", ".", "="};
        for(int i = 0; i < 6; i++)
        {
        	Button operation = new Button(operations[i]);
        	operation.setFont(buttonFont);
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
        	Button miscOperation = new Button(i == 0 ? "DEL" : "C");
        	miscOperation.setFont(buttonFont);
        	
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
        
        primaryStage.setScene(new Scene(gridPane, width, height));
        primaryStage.show();
    }

	@Override
	public void updateCalculation() {
		displayText.setText(calc.getCalculation());
	}
}