package locadora.service;

import locadora.model.Aluguel;
import locadora.model.Cliente;
import locadora.model.Veiculo;
import locadora.repository.AluguelRepository;
import locadora.repository.VeiculoRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class GerenciadorAlugueis {

    private VeiculoRepository veiculoRepository;
    private AluguelRepository aluguelRepository;

    public GerenciadorAlugueis(VeiculoRepository veiculoRepository, AluguelRepository aluguelRepository) {
        this.veiculoRepository = veiculoRepository;
        this.aluguelRepository = aluguelRepository;
    }

    public void registrarAluguel(Veiculo veiculo, Cliente cliente, LocalDateTime dataInicio) {
        Aluguel aluguel = new Aluguel(veiculo, cliente, dataInicio);
        aluguelRepository.registrarAluguel(aluguel);
        veiculo.setDisponivel(false);
    }

    public void registrarDevolucao(Veiculo veiculo, LocalDateTime dataFim) {
        Aluguel aluguel = aluguelRepository.buscarAluguelPorVeiculo(veiculo);
        aluguel.setDataFim(dataFim);

        // Calcular valor do aluguel
        double valorAluguel = calcularValorAluguel(aluguel);

        // Realizar cobrança ou aplicar desconto conforme as regras de negócio de devolução
        if (aluguel.getCliente().isPessoaJuridica() && calcularDiasAluguel(aluguel) > 3) {
            valorAluguel *= 0.90; // Aplica desconto de 10% para cliente pessoa jurídica com mais de 3 dias de aluguel
        } else if (!aluguel.getCliente().isPessoaJuridica() && calcularDiasAluguel(aluguel) > 5) {
            valorAluguel *= 0.95; // Aplica desconto de 5% para cliente pessoa física com mais de 5 dias de aluguel
        }

        // Registrar devolução com o valor calculado
        aluguel.setValor(valorAluguel);
        aluguelRepository.registrarDevolucao(aluguel);
        veiculo.setDisponivel(true); // Define o veículo como disponível após devolvê-lo
    }

    private int calcularDiasAluguel(Aluguel aluguel) {
        LocalDateTime dataInicio = (LocalDateTime) aluguel.getDataInicio();
        LocalDateTime dataFim = (LocalDateTime) aluguel.getDataFim();
        int dias = (int) dataInicio.until(dataFim, ChronoUnit.DAYS);
        if (dataInicio.getHour() > dataFim.getHour() || (dataInicio.getHour() == dataFim.getHour() && dataInicio.getMinute() > dataFim.getMinute())) {
            dias++; // Considera o aluguel de um dia extra se a devolução for após o mesmo horário da retirada
        }
        return dias;
    }

    private double calcularValorAluguel(Aluguel aluguel) {
        int dias = calcularDiasAluguel(aluguel);
        double valorDiaria = 0.0;

        switch (aluguel.getVeiculo().getTipo()) {
            case PEQUENO:
                valorDiaria = 100.0;
                break;
            case MEDIO:
                valorDiaria = 150.0;
                break;
            case SUV:
                valorDiaria = 200.0;
                break;
        }

        return dias * valorDiaria;
    }
}

