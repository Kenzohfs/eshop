package br.senai.sc.eshop.model.dto;

import lombok.Data;

@Data
public class EnderecoDTO {
    private Long id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
