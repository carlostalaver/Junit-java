package com.wiredbraincoffee.reward.clip08;

/*
 * me llevé los  metodos anotados con @test a un interfaz llamada TestHelper,
 *  tambien puedo pasar metodos anotados con 
 * @BeforeEach, @AfterEach, y @BeforeAll,  @AfterAll pero con la salvedad de que los 
 * metodos anotados con estos dos ultimos decoradores deben ser static
 * */
import com.wiredbraincoffee.reward.RewardByConversionService;
import com.wiredbraincoffee.reward.RewardInformation;
import com.wiredbraincoffee.reward.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class RewardByConversionServiceTest implements TestHelper {
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
    @DisplayName("Testeando lo de la intefez")
    void correct() {
        assertEquals(10, 10);
        System.out.println(getEmptyOrder());
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
        assertEquals(0, info.getPointsRedeemed());
    }

    @Override
    public RewardService getRewardService() {
        return reward;
    }
}
