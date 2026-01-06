package com.benevenuto.transacao_api.controller;

import com.benevenuto.transacao_api.business.service.TransactionService;
import com.benevenuto.transacao_api.dtos.in.TransactionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @Operation(description = "Endpoint responsável por criar transação")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Transação gravada com sucesso"),
    @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
    @ApiResponse(responseCode = "400", description = "Erro de requisição"),
    @ApiResponse(responseCode = "500", description = "Erro interno")})
    public ResponseEntity<Void> criarTransacao(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        transactionService.addTransactions(transactionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsável por deletar todas as transações")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Transação deletada com sucesso"),
    @ApiResponse(responseCode = "400", description = "Erro de requisição"),
    @ApiResponse(responseCode = "500", description = "Erro interno")})
    public ResponseEntity<Void> deletarTransacoes() {
        transactionService.clearTransactions();
        return ResponseEntity.ok().build();
    }
}
