package br.com.ada.estacionamento.carros;

import br.com.ada.estacionamento.vagas.Vaga;
import br.com.ada.estacionamento.vagas.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {


    public CarroRepository carroRepository;
    public VagaRepository vagaRepository;


    public void cadastrarCarro(Carro carro){
        carroRepository.save(carro);
    }

    public void estacionar(Carro carro){
        List<Vaga> vagaList = (List<Vaga>) vagaRepository.findAll();
        int contador = 0;
        
        for (int i =0; i < vagaList.size(); i++) {

            if (vagaList.get(i).isOcupada()) {
                contador++;
                System.out.println("Vaga Ocupada");
            }

            if (contador == 10) {
                System.out.println("Estacionamento todo ocupado");
            } else {
                if (carro.getVaga().isOcupada() == false) {
                    carro.getVaga().setOcupada(true);
                    carroRepository.save(carro);
                    System.out.println("Carro estacionado");
                    break;
                } else {
                    System.out.println("Vaga já esta ocupada");
                }
            }
        }
    }
}
