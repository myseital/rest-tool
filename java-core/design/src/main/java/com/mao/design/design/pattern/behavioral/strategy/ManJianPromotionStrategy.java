package com.mao.design.design.pattern.behavioral.strategy;

/**
 * @author maojiawei
 */
public class ManJianPromotionStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("满减促销,满200-20元");
    }
}
