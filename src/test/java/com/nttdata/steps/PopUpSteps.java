package com.nttdata.steps;

import com.nttdata.page.PopUpPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

public class PopUpSteps {
    private WebDriver driver;

    public PopUpSteps(WebDriver driver) {
        this.driver = driver;
    }

    public double getUnitPricePopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PopUpPage.unitPricePopup));

        String priceText = priceElement.getText().replace("S/ ", "").replace(",", "").trim();
        return Double.parseDouble(priceText);
    }

    public double getTotalAmountPopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PopUpPage.totalAmountPopup));

        String totalText = totalElement.getText().replace("S/ ", "").replace(",", "").trim();
        return Double.parseDouble(totalText);
    }

    public int getQuantityFromPopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PopUpPage.quantityPopup));

        String quantityText = quantityElement.getText().trim();
        quantityText = quantityText.replaceAll("[^0-9]", "");
        return Integer.parseInt(quantityText);
    }

    public void validatePopupTotal() {
        int quantityPopup = getQuantityFromPopup();
        double unitPrice = getUnitPricePopup();
        double expectedTotal = unitPrice * quantityPopup;
        double actualTotal = getTotalAmountPopup();
        BigDecimal expectedRounded = BigDecimal.valueOf(expectedTotal).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualRounded = BigDecimal.valueOf(actualTotal).setScale(2, RoundingMode.HALF_UP);

        if (expectedRounded.compareTo(actualRounded) == 0) {
            System.out.println("Validación exitosa: El total en el popup es correcto.");
        } else {
            throw new AssertionError("Error: El total en el popup no coincide con la cantidad seleccionada. " +
                    "Esperado: S/ " + expectedRounded + " - Obtenido: S/ " + actualRounded +
                    " (Cantidad en popup: " + quantityPopup + ")");
        }
    }


    public void finalizePurchase() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finalizeButton = wait.until(ExpectedConditions.elementToBeClickable(PopUpPage.finalizePurchasePopup));
        finalizeButton.click();
    }
}
