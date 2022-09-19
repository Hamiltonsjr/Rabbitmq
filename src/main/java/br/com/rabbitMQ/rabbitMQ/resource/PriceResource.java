package br.com.rabbitMQ.rabbitMQ.resource;

import br.com.rabbitMQ.rabbitMQ.contants.RabbitMQConstants;
import br.com.rabbitMQ.rabbitMQ.dto.PriceDTO;
import br.com.rabbitMQ.rabbitMQ.service.RabbitServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/price")
public class PriceResource {

    @Autowired
    private RabbitServices services;

    @PutMapping
    private ResponseEntity updatePrice(@RequestBody final PriceDTO price) {
        log.info("price={}", price);
        this.services.sendMessage(RabbitMQConstants.QUEUE_PRICE, price);
        return new ResponseEntity(HttpStatus.OK);

    }

}
