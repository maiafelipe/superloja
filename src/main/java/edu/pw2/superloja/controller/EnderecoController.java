package edu.pw2.superloja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import edu.pw2.superloja.model.endereco.CepData;
import edu.pw2.superloja.model.endereco.Endereco;

@Controller
@RequestMapping("/enderecos")
public class EnderecoController {

    private String apiUrl = "https://viacep.com.br/ws";
    private String dataFormat = "json";

    @GetMapping("/consulta")
    @ResponseBody
    public String consultarCep(String cep){
        RestTemplate rt = new RestTemplate();
        String url = apiUrl + "/" + cep + "/" + dataFormat;
        CepData data = rt.getForObject(url, CepData.class);
        Endereco e = new Endereco(data);
        String msg = "Consultando cep: " + cep + ".\n";
        msg += e;
        return msg;
    }

    // Implementar consulta por endereço:
    // https://viacep.com.br/ws/CE/Fortaleza/Duque/json/
}
