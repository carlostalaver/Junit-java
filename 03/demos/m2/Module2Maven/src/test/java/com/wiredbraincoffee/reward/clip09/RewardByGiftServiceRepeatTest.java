package com.wiredbraincoffee.reward.clip09;

import com.wiredbraincoffee.reward.RewardByGiftService;
import com.wiredbraincoffee.reward.RewardInformation;
import com.wiredbraincoffee.reward.RewardService;
import org.junit.jupiter.api.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
/*
 * usando el decorador @@RepeatedTest
 * 
 * */
public class RewardByGiftServiceRepeatTest implements TestHelper {
    private RewardByGiftService reward = null;

    @BeforeEach
    void setUp() {
        reward = new RewardByGiftService();
        reward.setNeededPoints(100);
        System.out.println("setUp called");
    }

    @DisplayName("When gift product is not in order, reward should not be applied")
    //@RepeatedTest(5)
     //@RepeatedTest(value = 5, name =  "-{displayName} -> {currentRepetition}/{totalRepetitions}") // el - antes de {displayName} es requerido para q pueda mostrar el msj 
    @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
    void giftProductNotInOrder(RepetitionInfo repetitionInfo) { //repetitionInfo inyectado para trackear las repeticiones 
    	
        //reward.setGiftProductId(getRandomProductIdNotInOrder());
        long productId = repetitionInfo.getCurrentRepetition() + 1000;
        System.out.println(productId);
        reward.setGiftProductId(productId);

        RewardInformation info = reward.applyReward(getSampleOrder(), 200);

        assertEquals(0, info.getDiscount());
    }

    private long getRandomProductIdNotInOrder() {
        Random r = new Random();
        return r.longs(1000, 2000).findFirst().getAsLong();
    }

    @Override
    public RewardService getRewardService() {
        return reward;
    }
}
