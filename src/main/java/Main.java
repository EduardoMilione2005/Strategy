import java.time.LocalTime;

/**
 * Demonstração do padrão Strategy aplicado ao Controle Remoto.
 * Execute com:  javac *.java && java Main
 */
public class Main {

    public static void main(String[] args) {

        // ── 1. Estratégia física ──────────────────────────────────────────────
        sep("1. Estratégia Física (padrão)");
        ControleRemoto controle = new ControleRemoto(new EstrategiaFisica());
        System.out.println(controle.estrategiaAtual());
        System.out.println(controle.ligar());
        System.out.println(controle.mudarCanal(5));
        System.out.println(controle.ajustarVolume(20));
        System.out.println(controle.desligar());

        // ── 2. Trocar para voz em runtime ─────────────────────────────────────
        sep("2. Trocando para Estratégia de Voz (runtime)");
        controle.setEstrategia(new EstrategiaVoz(0.92));
        System.out.println(controle.estrategiaAtual());
        System.out.println(controle.ligar());
        System.out.println(controle.mudarCanal(12));

        // ── 3. Trocar para App ────────────────────────────────────────────────
        sep("3. Trocando para Estratégia App");
        controle.setEstrategia(new EstrategiaApp("iPad"));
        System.out.println(controle.estrategiaAtual());
        System.out.println(controle.ligar());
        System.out.println(controle.ajustarVolume(35));

        // ── 4. Trocar para Gestos ─────────────────────────────────────────────
        sep("4. Trocando para Estratégia de Gestos");
        controle.setEstrategia(new EstrategiaGestos(EstrategiaGestos.Sensibilidade.ALTA));
        System.out.println(controle.estrategiaAtual());
        System.out.println(controle.ligar());
        System.out.println(controle.mudarCanal(8));

        // ── 5. Estratégia agendada ────────────────────────────────────────────
        sep("5. Trocando para Estratégia Agendada");
        controle.setEstrategia(new EstrategiaAgendada(LocalTime.of(7, 0), LocalTime.of(23, 30)));
        System.out.println(controle.estrategiaAtual());
        System.out.println(controle.ligar());
        System.out.println(controle.desligar());

        // ── 6. Histórico de comandos ──────────────────────────────────────────
        sep("6. Histórico de comandos executados");
        controle.getHistorico().forEach(h -> System.out.println("  • " + h));
    }

    private static void sep(String titulo) {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  " + titulo);
        System.out.println("══════════════════════════════════════════════════");
    }
}
