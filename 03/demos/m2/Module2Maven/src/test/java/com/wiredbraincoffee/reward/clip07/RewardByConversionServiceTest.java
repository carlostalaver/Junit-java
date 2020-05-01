package com.wiredbraincoffee.reward.clip07;

import com.wiredbraincoffee.product.Product;
import com.wiredbraincoffee.reward.RewardByConversionService;
import com.wiredbraincoffee.reward.RewardInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/*Se explica como usar 
 * assumeTrue
 * assumeFalse
 * assumeThat
 * 
 * Al contrario de las assert fallidas las supocisiones fallidas no dan como resultado una falla del test
 * simplemente aborta el tests
 * */
public class RewardByConversionServiceTest {
    private RewardByConversionService reward = null;

    @BeforeEach
    void setUp() {
        reward = new RewardByConversionService();
        reward.setAmount(10);
        reward.setNeededPoints(100);
    }

    @Test
    @DisplayName("Correct amount is set")
    void correctAmount() {
        assertEquals(10, reward.getAmount());
    }

    @Test
    @DisplayName("Correct points are set")
    void correctPoint() {
        assertEquals(100, reward.getNeededPoints());
    }

    @Test
    @DisplayName("When empty order and zero points no rewards should be applied")
    void emptyOrderZeroPoints() {
        RewardInformation info = reward.applyReward(getEmptyOrder(), 0);

        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getPointsRedeemed());
    }

    @Test
    @DisplayName("When not enough points no rewards should be applied")
    void notEnoughPoints() {
        RewardInformation info = reward.applyReward(getSampleOrder(), 10);

        assertEquals(0, info.getDiscount());
        assertEquals(0, info.getPointsRedeemed());
    }

    @Test
    @DisplayName("When empty order and enough points no rewards should be applied")
    void emptyOrderEnoughPoints() {
        RewardInformation info = reward.applyReward(getEmptyOrder(), 200);

        assertEquals(0, info.getDiscount());

         /* notar que no extiste una variable de entorno llamada TEST_POINTS por lo tanto  assertEquals(0, info.getPointsRedeemed()); NO SE EJECUTAR�*/ 
         assumeTrue("1".equals(System.getenv("TEST_POINTS")));
        
        /*
         * assumingThat: solo ejecuta la expresion lambda si la suposicion es valida
         * 
         * assumingThat("1".equals(System.getenv("TEST_POINTS")),
                () -> {
                    // Execute this assertion only if assumption is valid
                    assertEquals(10, info.getPointsRedeemed());
                });*/
        assertEquals(0, info.getPointsRedeemed());
    }

    private List<Product> getEmptyOrder() {
        return Arrays.asList();
    }

    private List<Product> getSampleOrder() {
        Product bigDecaf = new Product(2, "Big Decaf", 2.49);
        Product bigLatte = new Product(3, "Big Latte", 2.99);
        Product bigTea = new Product(4, "Big Tea", 2.99);
        Product espresso = new Product(5, "Espresso", 2.99);
        return Arrays.asList(
                bigDecaf, bigLatte, bigTea, espresso);
    }
}
