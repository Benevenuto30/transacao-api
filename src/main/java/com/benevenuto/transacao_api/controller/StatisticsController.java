package com.benevenuto.transacao_api.controller;

import com.benevenuto.transacao_api.business.service.StatisticsService;
import com.benevenuto.transacao_api.dtos.out.StatisticsResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatistica")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    @Operation(description = "Endpoint responsável por buscar todas as estatisticas de transações")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Busca feita com sucesso"),
    @ApiResponse(responseCode = "400", description = "Erro de requisição"),
    @ApiResponse(responseCode = "500", description = "Erro interno")})
    public ResponseEntity<StatisticsResponseDTO> getEstatisticas(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60")Integer intervaloBusca) {

        return ResponseEntity.ok(statisticsService.calcularEstatisticasTransacoes(intervaloBusca));
    }
}
