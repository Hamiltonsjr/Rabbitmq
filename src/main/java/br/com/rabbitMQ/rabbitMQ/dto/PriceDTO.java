package br.com.rabbitMQ.rabbitMQ.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PriceDTO implements Serializable {
    private String codProduct;
    private Double price;

}
