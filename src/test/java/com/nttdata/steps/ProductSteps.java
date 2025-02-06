package com.nttdata.steps;

import com.nttdata.page.ProductPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductSteps {
    private WebDriver driver;

    public ProductSteps(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageUp() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(ProductPage.session));
            return true;
        } catch (Exception e) {
            System.out.println("Error: La página no está disponible o está caída.");
            return false;
        }
    }

    public void session() {
        WebElement sessionElement = driver.findElement(ProductPage.session);
        String sessionText = sessionElement.getText().trim();

        if (sessionText.equalsIgnoreCase("Iniciar sesión")) {
            System.out.println("Iniciando sesión...");
            sessionElement.click();
        } else {
            System.out.println("Ya estás autenticado. No es necesario iniciar sesión.");
        }
    }

    public boolean isLoginSuccessful() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement sessionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ProductPage.session));
            String sessionText = sessionElement.getText().trim();

            if (sessionText.equalsIgnoreCase("Cerrar sesión") || sessionText.equalsIgnoreCase("Denis Araque")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: No se detectó el botón de sesión después del login.");
            return false;
        }
    }

    public void navigateToCategory(String expectedCategory, String expectedSubcategory) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement categoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ProductPage.categoryClothes));
        String actualCategory = categoryElement.getText().trim();

        if (!actualCategory.equalsIgnoreCase(expectedCategory)) {
            throw new AssertionError("Error: La categoría en la página es '" + actualCategory + "', pero se esperaba '" + expectedCategory + "'.");
        }
        categoryElement.click();
        System.out.println("Categoría '" + actualCategory + "' validada correctamente.");

        // Validar el texto de la subcategoría
        WebElement subcategoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ProductPage.subcategoryMen));
        String actualSubcategory = subcategoryElement.getText().trim();

        if (!actualSubcategory.equalsIgnoreCase(expectedSubcategory)) {
            throw new AssertionError("Error: La subcategoría en la página es '" + actualSubcategory + "', pero se esperaba '" + expectedSubcategory + "'.");
        }
        subcategoryElement.click();
        System.out.println("Subcategoría '" + actualSubcategory + "' validada correctamente.");
    }


    public void selectFirstProduct() {
        driver.findElement(ProductPage.firstProduct).click();
    }

    public void setQuantity(int quantity) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement quantityElement = wait.until(ExpectedConditions.elementToBeClickable(ProductPage.quantityInput));

        quantityElement.click();
        quantityElement.sendKeys(Keys.CONTROL + "a");
        quantityElement.sendKeys(Keys.BACK_SPACE);
        quantityElement.sendKeys(String.valueOf(quantity));
    }

    public void addToCart() {
        driver.findElement(ProductPage.addToCartButton).click();
    }
}
