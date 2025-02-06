package com.nttdata.stepsdefinitions;

import com.nttdata.steps.PopUpSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;

public class PopUpStepsDef {
    private WebDriver driver;
    private PopUpSteps popUpSteps;

    public PopUpStepsDef() {
        this.driver = getDriver();
        this.popUpSteps = new PopUpSteps(driver);
    }
    @Entonces("valido en el popup la confirmación del producto agregado")
    public void valido_en_el_popup_la_confirmacion_del_producto_agregado() {
        double total = popUpSteps.getTotalAmountPopup();
        Assertions.assertNotNull(total, "El total en el popup no debería ser nulo.");
        Assertions.assertTrue(total > 0, "El total en el popup debe ser mayor a 0.");
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void valido_en_el_popup_que_el_monto_total_sea_calculado_correctamente() {
        try {
            popUpSteps.validatePopupTotal();
        } catch (AssertionError e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Cuando("finalizo la compra")
    public void finalizo_la_compra() {
        popUpSteps.finalizePurchase();
    }
}
