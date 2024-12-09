package hu.petrik.countdown;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HelloController {

    @FXML
    private TextField dateInputField;
     @FXML
     private Text result;

    @FXML
    private Label timerLabel;

    private Timeline countdownTimer;

    public void startCountdown() {
        String input = dateInputField.getText();

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime targetTime = LocalDateTime.parse(input, formatter);
            LocalDateTime now = LocalDateTime.now();

            if (targetTime.isBefore(now)) {
                showAlert("Hiba", "A megadott időpontnak a jövőben kell lennie.");
                return;
            }

            if (countdownTimer != null) {
                countdownTimer.stop();
            }

            countdownTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                updateCountdown(targetTime);
            }));
            countdownTimer.setCycleCount(Animation.INDEFINITE);
            countdownTimer.play();

        } catch (DateTimeParseException e) {
            showAlert("Hiba", "Érvénytelen dátumformátum! Használj YYYY-MM-DD HH:mm:ss formátumot.");
        }
    }

    private void updateCountdown(LocalDateTime targetTime) {
        LocalDateTime now = LocalDateTime.now();

        java.time.Duration duration = java.time.Duration.between(now, targetTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        if (duration.isZero() || duration.isNegative()) {
            countdownTimer.stop();
            showAlert("Figyelem", "Az idő lejárt!");
            timerLabel.setText("Idő lejárt.");
            return;
        }

        timerLabel.setText(String.format("Hátralévő idő: %02d:%02d:%02d", hours, minutes, seconds));
    }

    private void showAlert(String title, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }
}