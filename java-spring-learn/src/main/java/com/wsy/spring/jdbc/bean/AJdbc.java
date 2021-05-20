package com.wsy.spring.jdbc.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class AJdbc {
    BigInteger id;
    BigDecimal amount;
}
