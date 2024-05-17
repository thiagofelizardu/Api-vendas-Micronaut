package com.phoebus.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@Controller("/pedido")
@ExecuteOn(TaskExecutors.IO)
public class PedidoController {
}
