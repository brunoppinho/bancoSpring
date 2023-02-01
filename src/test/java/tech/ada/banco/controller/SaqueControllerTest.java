package tech.ada.banco.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.ada.banco.model.Conta;
import tech.ada.banco.model.ModalidadeConta;
import tech.ada.banco.repository.ContaRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SaqueControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ContaRepository repository;

    private final String baseUri = "/saque";

    @Test
    void testSaqueSemSaldo() throws Exception {
        Conta contaBase = repository.save(new Conta(ModalidadeConta.CC, null));

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .param("valor", "10")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andReturn().getResponse().getErrorMessage();

        assertEquals("Limite acima do saldo disponível!", response);
    }

    @Test
    void testSaqueSaldoTotal() throws Exception {
        Conta contaBase = repository.save(new Conta(ModalidadeConta.CC, null));
        contaBase.deposito(BigDecimal.TEN);

        assertEquals(BigDecimal.TEN, contaBase.getSaldo());

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .param("valor", "10")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        assertEquals("0", response);
        assertEquals(BigDecimal.ZERO, contaBase.getSaldo());
    }

    @Test
    void testSaqueSaldoInsuficiente() throws Exception {
        Conta contaBase = repository.save(new Conta(ModalidadeConta.CC, null));
        contaBase.deposito(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, contaBase.getSaldo());

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .param("valor", "10")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andReturn().getResponse().getErrorMessage();

        assertEquals("Limite acima do saldo disponível!", response);
        assertEquals(BigDecimal.ONE, contaBase.getSaldo());

    }

    @Test
    void testSaqueNegativo() throws Exception {
        Conta contaBase = new Conta(ModalidadeConta.CC, null);
        contaBase.deposito(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, contaBase.getSaldo());

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .param("valor", "-10")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andReturn().getResponse().getErrorMessage();

        assertEquals("Valor informado está inválido.", response);
        assertEquals(BigDecimal.ONE, contaBase.getSaldo());

    }
}