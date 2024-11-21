package com.masterdev.demo.worker.consumer;

import com.masterdev.demo.worker.producer.NotificarPraticanteErroProdutor;
import com.masterdev.demo.worker.producer.NotificarPraticanteSucessoProdutor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NotificarPraticanteRequestConsumidor {

    @Autowired 
    private NotificarPraticanteErroProdutor erroProdutor;
    
    @Autowired 
    private NotificarPraticanteSucessoProdutor sucessoProdutor;

    @RabbitListener(queues = { "notificar-request-queue"})
    public void receberMensagem (@Payload Message message) {
        System.out.println(message);
        if (new Random().nextBoolean()) {
            sucessoProdutor.gerarResposta("Mensagem de sucesso Notificação " + message);
        } else {
            erroProdutor.gerarResposta("ERRO na notificação " + message);
        }
    }
}
