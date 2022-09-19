package br.com.rabbitMQ.rabbitMQ.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDTO implements Serializable {
    private String codProduct;
    private String quantity;

}
