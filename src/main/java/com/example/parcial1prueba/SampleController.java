package com.example.parcial1prueba;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TimePicker;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SampleController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button timePickerButton;

    @FXML
    private Button agendarButton;

    private TimePicker timePicker;
    private Label timeLabel;

    public void initialize() {
        // Crear un TimePicker y un Label para mostrar la hora seleccionada
        timePicker = new TimePicker();
        timeLabel = new Label();

        // Configurar el formato de la hora para que sea más legible
        timePicker.setConverter(new StringConverter<>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            @Override
            public String toString(LocalTime time) {
                if (time != null) {
                    return formatter.format(time);
                }
                return "";
            }

            @Override
            public LocalTime fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalTime.parse(string, formatter);
                }
                return null;
            }
        });
    }

    @FXML
    private void showTimePicker(ActionEvent event) {
        // Mostrar el TimePicker en un nuevo diálogo
        timePicker.showDialog(timePickerButton.getScene().getWindow());
        timePicker.setUserData(timeLabel);
    }

    @FXML
    private void agendarCita(ActionEvent event) {
        // Obtener la fecha y hora seleccionadas por el usuario
        LocalDate selectedDate = datePicker.getValue();
        LocalTime selectedTime = timePicker.getValue();

        if (selectedDate != null && selectedTime != null) {
            // Mostrar un mensaje de confirmación con la fecha y hora seleccionadas
            String message = "Cita agendada para el " +
                    selectedDate.atStartOfDay().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                    " a las " + selectedTime.format(DateTimeFormatter.ofPattern("HH:mm"));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmación de Cita");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        } else {
            // Mostrar un mensaje de error si no se seleccionó fecha o hora
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, seleccione una fecha y una hora para agendar la cita.");
            alert.showAndWait();
        }
    }
}