module Kappa {
    exports kappa;

    requires javafx.graphics;
    requires javafx.base;
    requires javafx.controls;
    requires java.sql;

    opens kappa to influxdb.java, influxdb.client.java;
    opens kappa.model to javafx.base;
}