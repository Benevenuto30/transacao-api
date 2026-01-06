package com.benevenuto.transacao_api.business.service;

import com.benevenuto.transacao_api.dtos.out.StatisticsResponseDTO;
import com.benevenuto.transacao_api.dtos.in.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    public final TransactionService transactionService;

    public StatisticsResponseDTO calcularEstatisticasTransacoes(Integer intervaloDeBusca) {
        log.info("Iniciando calculo de estatisticas das transações");
        List<TransactionRequestDTO> transacoes = transactionService.getTransactions(intervaloDeBusca);

        log.info("Verificando se a lista de transações está vazia para o período de "+ intervaloDeBusca);
        if(transacoes.isEmpty()) {
            log.info("Não existem transações para o periodo informado "+ intervaloDeBusca);
            return new StatisticsResponseDTO(0L,0.0,0.0,0.0,0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransactionRequestDTO::valor)
                .summaryStatistics();
        log.info("Exibindo estatisticas de transações para o periodo informado "+ intervaloDeBusca);
        return new StatisticsResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
