package com.nttdata.steps;

import com.nttdata.page.CarPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CarSteps {
    private WebDriver driver;

    public CarSteps(WebDriver driver) {
        this.driver = driver;
    }

    public String getCartTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(CarPage.carTitle));
        return titleElement.getText().trim();
    }

    public double getUnitPriceCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(CarPage.carUnitPrice));

        String priceText = priceElement.getText().replace("S/ ", "").replace(",", "").trim();
        return Double.parseDouble(priceText);
    }

    public int getQuantityFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(CarPage.carQuantity));

        String quantityText = quantityElement.getAttribute("value").trim(); // Obtener el valor del input
        return Integer.parseInt(quantityText);
    }

    public String getCartTotalAmount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(CarPage.carTotalAmount));
        return totalElement.getText();
    }

    public double getTotalAmountCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(CarPage.carTotalAmount));

        String totalText = totalElement.getText().replace("S/ ", "").replace(",", "").trim();
        return Double.parseDouble(totalText);
    }

    public void validateCartTotal() {
        int quantityCart = getQuantityFromCart();
        double unitPrice = getUnitPriceCart();
        double expectedTotal = unitPrice * quantityCart;
        double actualTotal = getTotalAmountCart();

        BigDecimal expectedRounded = BigDecimal.valueOf(expectedTotal).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualRounded = BigDecimal.valueOf(actualTotal).setScale(2, RoundingMode.HALF_UP);

        if (expectedRounded.compareTo(actualRounded) == 0) {
            System.out.println("El total en el carrito es correcto.");
        } else {
            throw new AssertionError("Error: El total en el carrito no coincide con la cantidad seleccionada. " +
                    "Esperado: S/ " + expectedRounded + " - Obtenido: S/ " + actualRounded +
                    " (Cantidad en carrito: " + quantityCart + ")");
        }
    }
    public void finalizePurchase() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finalizeButton = wait.until(ExpectedConditions.elementToBeClickable(CarPage.finalizeCarPurchase));
        finalizeButton.click();
    }
}
