module Kappa {
    exports kappa;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.controls;
    opens kappa.model to javafx.base;
}