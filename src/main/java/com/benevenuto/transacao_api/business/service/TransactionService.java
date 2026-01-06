package com.benevenuto.transacao_api.business.service;

import com.benevenuto.transacao_api.dtos.in.TransactionRequestDTO;
import com.benevenuto.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final List <TransactionRequestDTO> transactionsList = new ArrayList();

    public void addTransactions(TransactionRequestDTO transactionRequestDTO) {
        log.info("Iniciado processamento de gravar transações");

        if(transactionRequestDTO.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data ou horário maior do que o atual");
            throw new UnprocessableEntity("Data ou horário maior do que o atual");
        }
        if(transactionRequestDTO.valor() < 0){
            log.error("Valor não pode ser negativo");
            throw new UnprocessableEntity("Valor negativo");
        }
        transactionsList.add(transactionRequestDTO);
    }

    public void clearTransactions(){
        log.info("Deletando todos os transacoes");
        transactionsList.clear();
    }

    public List <TransactionRequestDTO> getTransactions(Integer rangeSearch){
        log.info("Buscando todos os transacoes");
        OffsetDateTime dateTimeSearch = OffsetDateTime.now().minusSeconds(rangeSearch);
        return transactionsList.stream()
                .filter(transacoes -> transacoes.dataHora()
                        .isAfter(dateTimeSearch))
                        .toList();
    }
}
