package com.nttdata.stepsdefinitions;

import com.nttdata.steps.CarSteps;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;

public class CarStepsDef {
    private WebDriver driver;
    private CarSteps carSteps;

    public CarStepsDef() {
        this.driver = getDriver();
        this.carSteps = new CarSteps(driver);
    }
    @Entonces("valido el titulo de la pagina del carrito")
    public void valido_el_titulo_de_la_pagina_del_carrito() {
        String actualTitle = carSteps.getCartTitle();
        Assertions.assertEquals("carrito", actualTitle.toLowerCase(),
                "El título de la página no es el esperado. Obtenido: " + actualTitle);
    }

    @Y("vuelvo a validar el calculo de precios en el carrito.")
    public void vuelvo_a_validar_el_calculo_de_precios_en_el_carrito() {
        carSteps.validateCartTotal();
        carSteps.finalizePurchase();
    }
}
