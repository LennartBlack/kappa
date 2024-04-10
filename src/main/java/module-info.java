module Kappa {
    exports kappa;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.controls;
    requires influxdb.java;
    requires influxdb.client.java;

    opens kappa to influxdb.java;
    opens kappa.model to javafx.base;
}