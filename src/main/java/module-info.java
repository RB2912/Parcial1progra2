module com.example.parcial1prueba {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.parcial1prueba to javafx.fxml;
    exports com.example.parcial1prueba;
}