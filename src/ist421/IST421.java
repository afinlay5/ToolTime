/**
 * @author Adrian D. Finlay
 * 
 */

//Package Declaration
package ist421;

//Packages Imported
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
 

// IST 421 Semester Project
public class IST421 extends Application {
    
    // Main Method()
    public static void main(String[] args) {
        launch(args);
    }
    
    
    //Containers, Components, Primitives, Etc.
    private Button mainBtn, exitBtn, assBtn,addButton, addButton2, backBtn;
    private Separator sep1;
    private TextField userName, pass;

    private TableColumn jobCol = new TableColumn("Job");
    private TableColumn locCol = new TableColumn("Location");
    private TableColumn dateCol = new TableColumn("Date");
    
    private TableColumn toolCol = new TableColumn("Tool");
    private TableColumn condCol = new TableColumn("Condition");
    private TableColumn locCol2 = new TableColumn("Location");
    
    private final HBox hb = new HBox();
    private final TableView<Tool> table = new TableView<>();
    private final ObservableList<Tool> data = FXCollections.observableArrayList();
    
    private final HBox hb2 = new HBox();
    private final TableView<Assignment> table2 = new TableView<>();
    private final ObservableList<Assignment> data2 = FXCollections.observableArrayList();
       
    private final FlowPane mainRoot = new FlowPane(Orientation.VERTICAL);
    private final FlowPane assRoot = new FlowPane(Orientation.VERTICAL);
    private final FlowPane backRoot = new FlowPane(Orientation.VERTICAL);
    private final FlowPane back2AssRoot = new FlowPane(Orientation.VERTICAL);
    private static byte ctr; //Counter for LogIn Verification Mechanism
    private PasswordField psx;
    
    private Stage progStage;
    private Scene mainScene,upcAssScene,back2Tool, back2Ass;
    private TextField addJob, addLocation, addDate;
    private TextField addTool, addCondition, addLocation2;
    
    
/// JavaFX LIFECYCLE METHODS (inherited from javafx.Application.application)
    @Override
    public void init() throws InvocationTargetException { 
        /*        progStage = new Stage ();
        //Attempt to provide icon resource
        progStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png"))); */
    }    
    @Override
    public void start(Stage mainStage) {
        
        //Set Stage
        progStage = mainStage;        
                    
        //Root Node
        mainRoot.setAlignment(Pos.CENTER);
        
        //Attempt to provide Icon 
        progStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png"))); 
        
                
        //Scene
        mainScene = new Scene(mainRoot, 700, 550); //root, width, length

        //Scene -> Stage
        progStage.setTitle("GE: TOOL TIME APPLICATION");
        progStage.setScene(mainScene);

        //Set a background image
        Image image = new Image(this.getClass().getResourceAsStream("GE.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize (100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
     
        //Background -> Root Node
        mainRoot.setBackground(background);
        
        //Instantiate Components
        mainBtn = new Button ("Log-In: Application");
        mainBtn.setAlignment(Pos.CENTER_RIGHT);
        exitBtn = new Button ("  Exit:  Application ");
        exitBtn.setAlignment(Pos.CENTER_RIGHT);
        
        mainBtn.setFont(Font.font(null, FontWeight.BOLD, 11));
        exitBtn.setFont(Font.font(null, FontWeight.BOLD, 11));
     
        
        userName = new TextField();
        userName.setPromptText("Enter Username");
        pass = new TextField();
        psx = new PasswordField();
        psx.setPromptText("Enter Password");
        psx.setFont(new Font ("Times New Roman", 12.25));
        
        
        sep1 = new Separator ();
        sep1.setPrefWidth(180);
	sep1.setPrefHeight(20);
             
        
        //Event Handling
        mainBtn.setOnAction((ActionEvent ae) -> {
            if (ifLogOnHasOccured()) {
                //Method called in decision-control statement                
            }
            else 
                setLogInScreen();
        });
         
        exitBtn.setOnAction((ActionEvent ae) -> {
            exit();
            stop();
        });
        
        userName.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                if (ifLogOnHasOccured()) {
                    //Method called in decision-control statement
                }
                else 
                    setLogInScreen();
            }
        });
        
        psx.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                if (ifLogOnHasOccured()) {
                    //Method called in decision-control statement
                }
                else 
                    setLogInScreen();
            }
        });
              
        
        //Components -> SceneGraph
        mainRoot.getChildren().addAll (userName, psx, sep1, mainBtn, exitBtn);
        

        //Display Stage 
        progStage.show();
    }  
    @Override
    public void stop () {
        try {
            Thread.sleep(500);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
        System.exit(0);
    }
    
    
/// Ancillary Methods   
    //Utility Methids
    protected void exit () {
        Alert exit = new Alert(AlertType.INFORMATION);
        exit.setTitle("PROGRAM EXIT");
        exit.setHeaderText("Program Exit");
        exit.setContentText("You have been logged off.");
        exit.showAndWait();  
    }      
    protected boolean ifLogOnHasOccured() {
        
        boolean pressed;
        
        if (ctr > 0) { 
           Alert alert = new Alert(AlertType.WARNING);
           alert.setTitle("ACTION INVALID");
           alert.setHeaderText("Action Invalid!");
           alert.setContentText("You have already logged in.");
           alert.showAndWait();           
           // root.getChildren().add(alert);
           pressed = true;
        }  
        else
           pressed = false;
        
        
        return pressed;
    }  
    
    // Screen Progression
    @SuppressWarnings({"unchecked", "Convert2Lambda"})  //Login Screen -> Tool Inventory
    protected void setLogInScreen () {
         
        // -> Tool Inventory Scene
        progStage.setTitle("(GE) TOOL TIME: TOOL INVENTORY");
        
        //Set Scene Width()
        progStage.setWidth(775.0);
        
        final Label label = new Label("Tool Inventory");
        label.setFont(new Font("Arial", 20));
        label.setTextFill(Paint.valueOf("red"));
 
        table.setEditable(true);
        
        //ifLogOnHasOccured() mechanism
        ctr++; //ctr is >= 1
        
        StringBuilder user = new StringBuilder();
        user.append("USER: ");
        user.append(" \" ");
        user.append(userName.getText().toUpperCase(Locale.ENGLISH));
        user.append(" \" ");
        
        assBtn = new Button ("Upcoming Assignments - >");
        userName.setText(user.toString());
        mainBtn.setText(" Log-In: Application ");
        mainBtn.setFont(Font.font(null, FontWeight.BOLD, 11));
        exitBtn.setText("\"EXIT\" APPLICATION");
        exitBtn.setFont(Font.font(null, FontWeight.BOLD, 11));
        mainRoot.getChildren().removeAll(psx,sep1, mainBtn, exitBtn);
        pass.setText("***********");
        mainRoot.getChildren().addAll(pass, sep1,mainBtn, exitBtn);
                
       
        //Background -> Root Node
        mainRoot.setBackground(null);
        
        
        /// Table Components: Tool, Condition, Location
             
        //Tool
        toolCol.setMinWidth(180);
        toolCol.setCellValueFactory(
                new PropertyValueFactory<Tool, String>("Tool"));
        //Condition
        condCol.setMinWidth(120);
        condCol.setCellValueFactory(
                new PropertyValueFactory<Tool, String>("Condition"));
        //Location
        locCol2.setMinWidth(180);
        locCol2.setCellValueFactory(
                new PropertyValueFactory<Tool, String>("Location"));
        
        
        //GUI Components
        addTool = new TextField();
        addTool.setMaxWidth(toolCol.getPrefWidth());
        addTool.setPromptText("Tool");
        
        addCondition = new TextField();
        addCondition.setMaxWidth(condCol.getPrefWidth());
        addCondition.setPromptText("Condition");
        
        addLocation2 = new TextField();
        addLocation2.setMaxWidth(locCol2.getPrefWidth());
        addLocation2.setPromptText("Location");
        
        addButton = new Button("Add");
        addButton.setFont(Font.font(null, FontWeight.BOLD, 11));
        
        
        //Event Handling
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Tool(
                        addTool.getText(),
                        addCondition.getText(),
                        addLocation2.getText()));
                addTool.clear();
                addCondition.clear();
                addLocation2.clear();
            }
        });
        
        
        addTool.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
              data.add(new Tool(
                        addTool.getText(),
                        addCondition.getText(),
                        addLocation2.getText()));
                addTool.clear();
                addCondition.clear();
                addLocation2.clear();
            }
        });
        
        addCondition.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
              data.add(new Tool(
                        addTool.getText(),
                        addCondition.getText(),
                        addLocation2.getText()));
                addTool.clear();
                addCondition.clear();
                addLocation2.clear();
            }
        });
                
        addLocation2.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
              data.add(new Tool(
                        addTool.getText(),
                        addCondition.getText(),
                        addLocation2.getText()));
                addTool.clear();
                addCondition.clear();
                addLocation2.clear();
            }
        });
        
        userName.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                if (ifLogOnHasOccured()) {
                    //Method called in decision-control statement
                }
                else 
                    setLogInScreen();
            }
        });
        
        psx.setOnKeyPressed((KeyEvent ke) -> {  //Not 100% Functional
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                if (ifLogOnHasOccured()) {
                    //Method called in decision-control statement
                }
                else 
                    setLogInScreen();
            }
        });
        
        assBtn.setOnAction( (ae) -> {
                //Move to next scene -> stage
                upcomingAss();
        });
        
        
        //Set Table Values -> Scene
        table.setItems(data);
        table.getColumns().addAll(toolCol, condCol, locCol2);        
        
        //Components -> Root
        hb.getChildren().addAll(addTool, addCondition, addLocation2, addButton, assBtn);
        hb.setSpacing(3);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
        
        mainRoot.getChildren().addAll(vbox);
                       
    }       
 
    @SuppressWarnings({"unchecked", "Convert2Lambda"})  //Tool Inventory -> Upcoming Assignments
    protected void upcomingAss() {
               
        //Set Scene
        upcAssScene = new Scene(assRoot, 700, 550); //root, width, length
        progStage.setTitle("(GE) TOOL TIME: UPCOMING ASSIGNMENTS");

        final Label title = new Label("Upcoming Assignments");
        title.setFont(new Font("Arial", 20));
        title.setTextFill(Paint.valueOf("red"));

        table2.setEditable(true); //already editable?

        //Background -> Root Node
        assRoot.setBackground(null);        


        /// Table Components: Job, Location, Date

        //Job
        jobCol.setMinWidth(180);
        jobCol.setCellValueFactory(
                new PropertyValueFactory<Assignment, String>("Job"));
        //Location
        locCol.setMinWidth(120);
        locCol.setCellValueFactory(
                new PropertyValueFactory<Assignment, String>("Location"));
        //Date
        dateCol.setMinWidth(180);
        dateCol.setCellValueFactory(
                new PropertyValueFactory<Assignment, String>("Date"));


        //GUI Components
        addJob = new TextField();
        addJob.setPromptText("Job");
        addJob.setMaxWidth(jobCol.getPrefWidth());
        
        addLocation= new TextField();
        addLocation.setPromptText("Location");
        addLocation.setMaxWidth(locCol.getPrefWidth());
        
        
        addDate = new TextField();
        addDate.setPromptText("Date");
        addDate.setMaxWidth(dateCol.getPrefWidth());
        
        addButton2 = new Button("Add");
        addButton2.setFont(Font.font(null, FontWeight.BOLD, 11));


        //Event Handling
        addButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent a) {
                data2.add(new Assignment(
                        addJob.getText(),
                        addLocation.getText(),
                        addDate.getText()));
                addJob.clear();
                addLocation.clear();
                addDate.clear();
            }
        });

        addJob.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                data2.add(new Assignment(
                        addJob.getText(),
                        addLocation.getText(),
                        addDate.getText()));
                addJob.clear();
                addLocation.clear();
                addDate.clear();    
            }
        });

        addLocation.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                data2.add(new Assignment(
                        addJob.getText(),
                        addLocation.getText(),
                        addDate.getText()));
                addJob.clear();
                addLocation.clear();
                addDate.clear();
            }
        });

        addDate.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                data2.add(new Assignment(
                        addJob.getText(),
                        addLocation.getText(),
                        addDate.getText()));
                addJob.clear();
                addLocation.clear();
                addDate.clear();
            }
        });

        backBtn = new Button ("Back <-");
        backBtn.setFont(Font.font(null, FontWeight.BOLD, 11));
        backBtn.setOnAction((ae) -> {
           goBack();
        });


        //Set Table Values -> Scene
        table2.setItems(data2);
        table2.getColumns().addAll(jobCol, locCol, dateCol);


        //Components -> Root
        hb2.getChildren().addAll(addJob, addLocation, addDate, addButton2, backBtn, exitBtn);
        hb2.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(title, table2, hb2);

        assRoot.getChildren().addAll(vbox);


        //Scene -> Stage
        progStage.setScene(upcAssScene);
    
    }
    
    @SuppressWarnings({"unchecked", "Convert2Lamdbda"}) // (<-) Upcoming Assignments -> Tool Inventory
    protected void goBack () {
		
        //Set Scene
        back2Tool = new Scene(backRoot, 700, 550); //root, width, length
		
        // -> Tool Inventory Scene
        progStage.setTitle("(GE) TOOL TIME: TOOL INVENTORY");
        
        //Set Scene Width()
        progStage.setWidth(775.0);
        
        final Label label = new Label("Tool Inventory");
        label.setFont(new Font("Arial", 20));
        label.setTextFill(Paint.valueOf("red"));
 
        
        StringBuilder user = new StringBuilder();
        user.append("USER: ");
        user.append(" \" ");
        user.append(userName.getText().toUpperCase(Locale.ENGLISH));
        user.append(" \" ");
        
        backRoot.getChildren().addAll (userName, psx, sep1, mainBtn, exitBtn);
        backRoot.getChildren().removeAll(psx,sep1, mainBtn, exitBtn);        
        backRoot.getChildren().addAll(pass, sep1,mainBtn, exitBtn);
                
        
        //Set a background image
        Image image = new Image(this.getClass().getResourceAsStream("blk.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize (100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        //Background -> Root Node
        mainRoot.setBackground(background);
             
        
        //Event Handling
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Tool(
                        addTool.getText(),
                        addCondition.getText(),
                        addLocation.getText()));
                addTool.clear();
                addCondition.clear();
                addLocation.clear();
            }
        });
        
        
        addTool.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
              data.add(new Tool(
                        addTool.getText(),
                        addCondition.getText(),
                        addLocation.getText()));
                addTool.clear();
                addCondition.clear();
                addLocation.clear();
            }
        });
        
        addCondition.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
              data.add(new Tool(
                        addTool.getText(),
                        addCondition.getText(),
                        addLocation.getText()));
                addTool.clear();
                addCondition.clear();
                addLocation.clear();
            }
        });
                
        addLocation.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
              data.add(new Tool(
                        addTool.getText(),
                        addCondition.getText(),
                        addLocation.getText()));
                addTool.clear();
                addCondition.clear();
                addLocation.clear();
            }
        });
        
        userName.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                if (ifLogOnHasOccured()) {
                    //Method called in decision-control statement
                }
                else 
                    setLogInScreen();
            }
        });
        
        psx.setOnKeyPressed((KeyEvent ke) -> {  //Not 100% Functional
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                if (ifLogOnHasOccured()) {
                    //Method called in decision-control statement
                }
                else 
                    setLogInScreen();
            }
        });
        
        assBtn.setOnAction( (ae) -> {
                //Move to next scene -> stage
                resetUpcomingAss();
        });
        
        
        //Set Table Values -> Scene
        table.setItems(data);
             
        
        //Components -> Root
	//        hb.getChildren().addAll(addTool, addCondition, addLocation, addButton, assBtn);
    //    hb.setSpacing(3);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
        
        backRoot.getChildren().addAll(vbox);
        
		//Scene -> Stage
        progStage.setScene(back2Tool);
    }       
    
    protected void resetUpcomingAss() {
        
        //Set Scene
        back2Ass = new Scene(back2AssRoot, 700, 550); //root, width, length
        progStage.setTitle("(GE) TOOL TIME: UPCOMING ASSIGNMENTS");

        final Label title = new Label("Upcoming Assignments");
        title.setFont(new Font("Arial", 20));
        title.setTextFill(Paint.valueOf("red"));

        back2AssRoot.setBackground(null);        

        //Event Handling
        addButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent a) {
                data2.add(new Assignment(
                        addJob.getText(),
                        addLocation.getText(),
                        addDate.getText()));
                addJob.clear();
                addLocation.clear();
                addDate.clear();
            }
        });

        addJob.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                data2.add(new Assignment(
                        addJob.getText(),
                        addLocation.getText(),
                        addDate.getText()));
                addJob.clear();
                addLocation.clear();
                addDate.clear();    
            }
        });

        addLocation.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                data2.add(new Assignment(
                        addJob.getText(),
                        addLocation.getText(),
                        addDate.getText()));
                addJob.clear();
                addLocation.clear();
                addDate.clear();
            }
        });

        addDate.setOnKeyPressed((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                data2.add(new Assignment(
                        addJob.getText(),
                        addLocation.getText(),
                        addDate.getText()));
                addJob.clear();
                addLocation.clear();
                addDate.clear();
            }
        });

		//Replace "Back <-" wih Upcoming Assigments ->"
		hb2.getChildren().remove(assBtn);
                hb2.getChildren().add(exitBtn);
		assBtn.setOnAction( (ae) -> {
                //Move to next scene -> stage
                goBack();
        });


        //Set Table Values -> Scene
        table2.setItems(data2);
        // table2.getColumns().addAll(jobCol, locCol, dateCol);


        //Components -> Root
        // hb2.getChildren().addAll(addJob, addLocation, addDate, addButton2, backBtn, exitBtn);
        // hb2.setSpacing(3);

        final VBox vbox2 = new VBox();
        vbox2.setSpacing(5);
        vbox2.setPadding(new Insets(10, 0, 0, 10));
        vbox2.getChildren().addAll(title, table2, hb2);

        back2AssRoot.getChildren().addAll(vbox2);


        //Scene -> Stage
        progStage.setScene(back2Ass);
    
    }
    
    
    
/// Ancillary Classes    
    public static class Tool {
       private final SimpleStringProperty tool;
       private final SimpleStringProperty condition;
       private final SimpleStringProperty location;

       //Constructor
       private Tool(String tool, String condition, String location) {
           this.tool = new SimpleStringProperty(tool);
           this.condition = new SimpleStringProperty(condition);
           this.location = new SimpleStringProperty(location);
       }

       //Encapsulated Getters & Setters
       public String getTool() {
           return tool.get();
       }

       public String getCondition() {
           return condition.get();
       }
       
       public String getLocation() {
           return location.get();
       }

       
       public void setTool(String tool) {
           this.tool.set(tool);
       }
       public void setCondition(String condition) {
           this.condition.set(condition);
       }
       public void setLocation(String location) {
           this.location.set(location);
       }
       
   }
    public static class Assignment {
       private final SimpleStringProperty job;
       private final SimpleStringProperty location;
       private final SimpleStringProperty date;

       //Constructor
       private Assignment (String job, String loc, String date) {
           this.job = new SimpleStringProperty(job);
           this.location = new SimpleStringProperty(loc);
           this.date = new SimpleStringProperty(date);
       }

       //Encapsulated Getters & Setters
       public String getJob() {
           return job.get();
       }
       
       public String getLocation() {
           return location.get();
       }
       
       public String getDate() {
           return date.get();
       }
       
       
       public void setJob(String job) {
           this.job.set(job);
       }
       public void setLocation(String location) {
           this.location.set(location);
       }
       public void setDate(String date) {
           this.date.set(date);
       }
       
   }
       
}