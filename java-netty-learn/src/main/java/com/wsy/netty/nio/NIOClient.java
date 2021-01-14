package com.wsy.netty.nio;

/**
 * NIO基本介绍
 * 1. 同步非阻塞的
 * 2. java.nio
 * 3. 核心
 *  - Channel(通道)
 *  - Buffer(缓冲区)
 *  - Selector(选择器)
 * 4. 面向缓冲区,即数据块通信
 * 5. 编解码
 *   - netty自身提供了codec(编解码器)
 *   - netty提供的编码器
 *     - StringEncoder 对字符串数据进行编码
 *     - ObjectEncoder 对java对象进行编码
 *   - netty提供的解码器
 *     - StringDecoder 对字符串数据进行解码
 *     - ObjectDecoder 对java对象进行解码
 *   - 缺点
 *     - 使用java底层序列化,对POJO对象或各种业务对象的编码和解码
 *       - 无法跨语言
 *       - 序列化的体积太大,是二进制编码的5倍多
 *       - 序列化性能太低
 *   - 解决方法 google protobuf（由http+json -> tcp + protobuf ）
 *     - protobuf 以message的方式来管理数据的
 *     - 支持跨平台跨语言
 *     - 高性能、高可靠性
 *     - protobuf编译器能自动生成代码,protobuf是将类的定义使用.proto文件描述
 *      - a.proto -> protoc.exe ->Xx.java -> ProtobufEncoder
 *        -> 二进制字节码
 *        -> ProtobufDecoder -> 业务数据对象 -> 使用
 *
 * 零拷贝
 */
public class NIOClient {
}
