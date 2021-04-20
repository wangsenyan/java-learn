package com.wsy.basis.base;

/**
 *  * 重写:(两同两小一大)
 *  *    - 方法名相同，参数类型相同
 *  *    - 子类返回的类型小于等于父类方法返回类型(子类)
 *  *    - 子类抛出异常小于等于父类方法抛出异常
 *  *    - 子类访问权限大于等于父类方法访问权限
 */
public class MOverride {
    public MOverride test() throws Exception {
        return new MOverride();
    }
}

class SubOverride extends MOverride{
    @Override
    public MOverride test() throws RuntimeException {
        return new SubOverride();
    }
}
