package tech.ada.banco.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import tech.ada.banco.model.Conta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SaqueControllerTest extends BaseContaTest {

    private final String baseUri = "/saque";

    @Test
    void testSaqueSemSaldo() throws Exception {
        Conta contaBase = criarConta(BigDecimal.ZERO);

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
        Conta contaBase = criarConta(BigDecimal.TEN);

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .param("valor", "10")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals("0.00", response);
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), contaBase.getSaldo());
    }

    @Test
    void testSaqueSaldoInsuficiente() throws Exception {
        Conta contaBase = criarConta(BigDecimal.ONE);

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .param("valor", "10")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andReturn().getResponse().getErrorMessage();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals("Limite acima do saldo disponível!", response);
        assertEquals(BigDecimal.ONE, contaBase.getSaldo());

    }

    @Test
    void testSaqueNegativo() throws Exception {
        Conta contaBase = criarConta(BigDecimal.ONE);

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .param("valor", "-10")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andReturn().getResponse().getErrorMessage();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals("Valor informado está inválido.", response);
        assertEquals(BigDecimal.ONE, contaBase.getSaldo());

    }

    @Test
    void testSaqueParcial() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .param("valor", "3")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals("7.00", response);
        assertEquals(BigDecimal.valueOf(7).setScale(2, RoundingMode.HALF_UP), contaBase.getSaldo());
    }

    @Test
    void testSaqueParcialQuebrado() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .param("valor", "3.7")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals("6.30", response);
        assertEquals(BigDecimal.valueOf(6.3).setScale(2, RoundingMode.HALF_UP), contaBase.getSaldo());
    }

    @Test
    void testSaqueContaInvalida() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);
        Optional<Conta> contaInexistente = repository.findContaByNumeroConta(9999);
        assertTrue(contaInexistente.isEmpty());

        String response =
                mvc.perform(post(baseUri + "/9999")
                                .param("valor", "3.7")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isNotFound())
                        .andReturn().getResponse().getContentAsString();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals(BigDecimal.valueOf(10), contaBase.getSaldo());
    }

}