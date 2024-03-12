package locadora.service;

import locadora.model.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorVeiculos {

    private final List<Veiculo> veiculos;

    public GerenciadorVeiculos() {
        veiculos = new ArrayList<>();
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        if (!veiculoJaCadastrado(veiculo.getPlaca())) {
            veiculos.add(veiculo);
        } else {
            System.out.println("Veículo com placa " + veiculo.getPlaca() + " já cadastrado.");
        }
    }

    public void alterarVeiculo(String placa, Veiculo novoVeiculo) {
        Veiculo veiculoExistente = buscarVeiculoPorPlaca(placa);
        if (veiculoExistente != null) {
            veiculoExistente.setNome(novoVeiculo.getNome());
            veiculoExistente.setTipo(novoVeiculo.getTipo());
        } else {
            System.out.println("Veículo com placa " + placa + " não encontrado.");
        }
    }

    public Veiculo buscarVeiculoPorNome(String parteNome) {
        List<Veiculo> veiculosEncontrados = veiculos.stream()
                .filter(veiculo -> veiculo.getNome().contains(parteNome))
                .collect(Collectors.toList());

        if (!veiculosEncontrados.isEmpty()) {
            return veiculosEncontrados.get(0);
        } else {
            return null;
        }
    }

    private boolean veiculoJaCadastrado(String placa) {
        return veiculos.stream().anyMatch(veiculo -> veiculo.getPlaca().equals(placa));
    }

    private Veiculo buscarVeiculoPorPlaca(String placa) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getPlaca().equals(placa))
                .findFirst()
                .orElse(null);
    }
}

