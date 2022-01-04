package com.mao.design.design.pattern.creational.prototype.abstractprototype;

/**
 * @author maojiawei
 */
public abstract class A implements Cloneable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
