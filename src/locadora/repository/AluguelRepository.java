package locadora.repository;

import locadora.model.Aluguel;
import locadora.model.Cliente;
import locadora.model.Veiculo;

import java.util.List;

public interface AluguelRepository {
    void registrarAluguel(Aluguel aluguel);
    void registrarDevolucao(Aluguel aluguel);
    List<Aluguel> buscarAlugueisPorCliente(Cliente cliente);

    Aluguel buscarAluguelPorVeiculo(Veiculo veiculo);
}
