package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    private int antalClips = 0;
    private int antalClippers = 0;

    @FXML
    private Label antalClipsLabel;

    @FXML
    private Label antalClippersLabel;

    @FXML
    private Button autoclipperButton;

    @FXML
    protected void makeClipButtonAction(ActionEvent event) {

        antalClips++;
        antalClipsLabel.setText(String.valueOf(antalClips));

        if (antalClips>=10) {
            autoclipperButton.setVisible(true);
        }

    }

    @FXML
    protected void autoclipperButtonAction(ActionEvent event) {

        antalClippers++;
        antalClippersLabel.setText(String.valueOf(antalClippers));


        // longrunning operation runs on different thread
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        myTask();
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();



    }


    private void myTask() {
        antalClips++;
        antalClipsLabel.setText(String.valueOf(antalClips));    }
}
