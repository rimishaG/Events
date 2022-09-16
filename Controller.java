package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField nameField;
    @FXML
    private Button HelloButton;
    @FXML
    private Button ByeButton;
    @FXML
    private CheckBox ourCheckBox;
    @FXML
    private Label ourLabel;

    @FXML
    public void initialize(){
        HelloButton.setDisable(true);
        ByeButton.setDisable(true);
    }

    @FXML
    public void ButtonClicked(ActionEvent e){
        if(e.getSource().equals(HelloButton)) {
            System.out.println("Hello " + nameField.getText());
        }
        else if(e.getSource().equals(ByeButton)) {
            System.out.println("Bye " + nameField.getText());
        }
        Runnable task =new Runnable(){
            @Override
            public void run() {
                try{
                    String s= Platform.isFxApplicationThread()? "UI Thread" : "Background Thread";
                    System.out.println("I am going to sleep on the thread" + s);
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String s= Platform.isFxApplicationThread()? "UI Thread" : "Background Thread";
                            System.out.println("I am updating label using" + s);
                            ourLabel.setText("Updated label");
                        }
                    });

                }
                catch(InterruptedException event){
//                    we are not expecting exception
                    }
            }
        };
        new Thread(task).start();
//         System.out.println("The following Button was pressed: " + e.getSource());
        if(ourCheckBox.isSelected()){
            nameField.clear();
            HelloButton.setDisable(true);
            ByeButton.setDisable(true);
        }
    }
    @FXML
    public void handleKeyReleased(){
        String text=nameField.getText();
        boolean disableButtons=text.isEmpty() || text.trim().isEmpty();
        HelloButton.setDisable(disableButtons);
        ByeButton.setDisable(disableButtons);
    }
    @FXML
    public void handleChange(){
        System.out.println("The checkbox is" + (ourCheckBox.isSelected()? "checked" : " not checked"));

    }
}
