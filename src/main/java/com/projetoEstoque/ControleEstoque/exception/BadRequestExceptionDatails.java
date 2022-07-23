package com.projetoEstoque.ControleEstoque.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDatails {
    private String title;
    private int status;
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;

}
