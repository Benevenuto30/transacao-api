package com.benevenuto.transacao_api.dtos.out;

public record StatisticsResponseDTO(Long count,
                                    Double sum,
                                    Double avg,
                                    Double min,
                                    Double max) {

}
