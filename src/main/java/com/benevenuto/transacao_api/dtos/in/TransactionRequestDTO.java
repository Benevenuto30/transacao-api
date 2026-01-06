package com.benevenuto.transacao_api.dtos.in;

import java.time.OffsetDateTime;

public record TransactionRequestDTO(Double valor, OffsetDateTime dataHora) {
}
