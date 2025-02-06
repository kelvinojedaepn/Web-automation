package com.nttdata.stepsdefinitions;

import com.nttdata.steps.ProductSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;

public class ProductStepsDef {
    private WebDriver driver;
    private ProductSteps productSteps;

    public ProductStepsDef() {
        this.driver = getDriver();
        this.productSteps = new ProductSteps(driver);
    }

    @Dado("estoy en la página de la tienda")
    public void que_me_encuentro_en_la_pagina_de_login() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/");

        Assertions.assertTrue(productSteps.isPageUp(), "Error: La página de login no está disponible.");
        if (!productSteps.isLoginSuccessful()) {
            productSteps.session();
        }
    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navego_a_la_categoria_y_subcategoria(String category, String subcategory) {
        productSteps.navigateToCategory(category, subcategory);
    }

    @Y("agrego {int} unidades del primer producto al carrito")
    public void agrego_unidades_del_primer_producto_al_carrito(int quantity) {
        productSteps.selectFirstProduct();
        productSteps.setQuantity(quantity);
        productSteps.addToCart();
    }
}
