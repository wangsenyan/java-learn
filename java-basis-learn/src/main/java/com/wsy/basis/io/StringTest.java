package com.wsy.basis.io;

import java.io.*;

/**
 * InputStream
 * - 字节数组
 * - String对象
 * - 文件
 * - "管道"
 * - 其他种类的流序列
 * - 其他数据源,如internet
 * 每一种数据源都有相应的InputStream子类,
 *    另外,FilterInputStream也属于一种InputStream
 *
 */
public class StringTest {
    byte[] a = new byte[200];
    OutputStream out;
    InputStream it;
}
