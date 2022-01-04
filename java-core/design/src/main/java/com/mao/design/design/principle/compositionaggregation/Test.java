package com.mao.design.design.principle.compositionaggregation;

/**
 * @author maojiawei
 */
public class Test {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        productDao.setDbConnection(new PostgreSQLConnection());
        productDao.addProduct();
    }
}
