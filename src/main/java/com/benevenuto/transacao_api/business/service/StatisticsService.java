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

    public StatisticsResponseDTO calculateStatisticsTransactions(Integer rangeSearch) {
        log.info("Iniciando calculo de estatisticas das transações");
        List<TransactionRequestDTO> transactions = transactionService.getTransactions(rangeSearch);

        log.info("Verificando se a lista de transações está vazia para o período de "+ rangeSearch);
        if(transactions.isEmpty()) {
            log.info("Não existem transações para o periodo informado "+ rangeSearch);
            return new StatisticsResponseDTO(0L,0.0,0.0,0.0,0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transactions.stream()
                .mapToDouble(TransactionRequestDTO::valor)
                .summaryStatistics();
        log.info("Exibindo estatisticas de transações para o periodo informado "+ rangeSearch);
        return new StatisticsResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
