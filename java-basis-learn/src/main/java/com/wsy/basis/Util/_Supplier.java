package com.wsy.basis.Util;

import java.util.Random;
import java.util.function.Supplier;

/**
 * 供给型接口,容器,存储数据
 */
public class _Supplier {
    public void testSupplier(){

        Supplier<Integer> supplier = new Supplier<Integer>(){
            @Override
            public Integer get() {
                return new Random().nextInt();
            }
        };
        System.out.println(supplier.get());
    }

    public static void main(String[] args) {
        _Supplier supplier = new _Supplier();
        supplier.testSupplier();
    }
}
