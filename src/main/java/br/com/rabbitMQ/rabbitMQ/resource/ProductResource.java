package br.com.rabbitMQ.rabbitMQ.resource;

import br.com.rabbitMQ.rabbitMQ.contants.RabbitMQConstants;
import br.com.rabbitMQ.rabbitMQ.dto.ProductDTO;
import br.com.rabbitMQ.rabbitMQ.service.RabbitServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private RabbitServices services;

    @PutMapping
    private ResponseEntity updateProduct(@RequestBody final ProductDTO product) {
        services.sendMessage(RabbitMQConstants.QUEUE_STOCK, product);
        return new ResponseEntity(HttpStatus.OK);

    }

}
