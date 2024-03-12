package locadora.repository;

import locadora.model.Aluguel;
import locadora.model.Cliente;
import locadora.model.Veiculo;
import java.util.List;

public interface VeiculoRepository {
    void cadastrarVeiculo(Veiculo veiculo);
    void atualizarVeiculo(Veiculo veiculo);
    Veiculo buscarVeiculoPorPlaca(String placa);
    List<Veiculo> buscarVeiculosDisponiveis();
}

