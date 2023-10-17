package br.edu.umfg.API.controller;

import br.edu.umfg.API.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClienteController clienteController;

    @Test
    public void testCriarClienteWhenValidClienteThenReturnCliente() throws Exception {
        Cliente cliente = new Cliente(1, "João", "Silva", "123456789");
        given(clienteController.criarCliente(cliente)).willReturn(cliente);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cliente)));
    }

    @Test
    public void testListarClientesWhenClientesExistThenReturnClientes() throws Exception {
        Cliente cliente1 = new Cliente(1, "João", "Silva", "123456789");
        Cliente cliente2 = new Cliente(2, "Maria", "Santos", "987654321");
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
        given(clienteController.listarClientes()).willReturn(clientes);

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(clientes)));
    }

    @Test
    public void testObterClientePorIdWhenValidIdThenReturnCliente() throws Exception {
        Cliente cliente = new Cliente(1, "João", "Silva", "123456789");
        given(clienteController.obterClientePorId(1)).willReturn(cliente);

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cliente)));
    }
}
