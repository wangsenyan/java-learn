package com.wsy.netty.proto;

import lombok.Data;

@Data
public class MsgProto {
    private int len;
    private byte[] content;
}
