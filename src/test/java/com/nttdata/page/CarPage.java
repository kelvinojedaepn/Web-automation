package com.nttdata.page;

import org.openqa.selenium.By;

public class CarPage {
    public static By carTitle = By.tagName("h1");
    public static By carUnitPrice = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[2]/div[2]/div[2]/span"); // Precio unitario
    public static By carQuantity = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[1]/div/input"); // Cantidad
    public static By carTotalAmount = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[2]/span"); // Total
    public static By finalizeCarPurchase = By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a");
}
