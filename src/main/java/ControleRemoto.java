import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControleRemoto {

    private ControleStrategy estrategia;
    private final List<String> historico = new ArrayList<>();

    public ControleRemoto(ControleStrategy estrategia) {
        if (estrategia == null) throw new IllegalArgumentException("Estratégia não pode ser nula.");
        this.estrategia = estrategia;
    }

    public void setEstrategia(ControleStrategy novaEstrategia) {
        if (novaEstrategia == null) throw new IllegalArgumentException("Estratégia não pode ser nula.");
        this.estrategia = novaEstrategia;
    }

    public ControleStrategy getEstrategia() {
        return estrategia;
    }

    public String ligar() {
        String resultado = estrategia.ligar();
        historico.add(resultado);
        return resultado;
    }

    public String desligar() {
        String resultado = estrategia.desligar();
        historico.add(resultado);
        return resultado;
    }

    public String mudarCanal(int canal) {
        String resultado = estrategia.mudarCanal(canal);
        historico.add(resultado);
        return resultado;
    }

    public String ajustarVolume(int nivel) {
        String resultado = estrategia.ajustarVolume(nivel);
        historico.add(resultado);
        return resultado;
    }

    public String estrategiaAtual() {
        return "Estratégia ativa: " + estrategia.nome();
    }

    public List<String> getHistorico() {
        return Collections.unmodifiableList(historico);
    }

    public void limparHistorico() {
        historico.clear();
    }
}
