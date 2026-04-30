import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Controle Remoto — Padrão Strategy")
class ControleRemotoTest {


    @Nested
    @DisplayName("Hierarquia de tipos")
    class HierarquiaTest {

        @Test @DisplayName("EstrategiaFisica implementa ControleStrategy")
        void fisicaImplementa() { assertInstanceOf(ControleStrategy.class, new EstrategiaFisica()); }

        @Test @DisplayName("EstrategiaVoz implementa ControleStrategy")
        void vozImplementa() { assertInstanceOf(ControleStrategy.class, new EstrategiaVoz()); }

        @Test @DisplayName("EstrategiaApp implementa ControleStrategy")
        void appImplementa() { assertInstanceOf(ControleStrategy.class, new EstrategiaApp()); }

        @Test @DisplayName("EstrategiaGestos implementa ControleStrategy")
        void gestosImplementa() { assertInstanceOf(ControleStrategy.class, new EstrategiaGestos()); }

        @Test @DisplayName("EstrategiaAgendada implementa ControleStrategy")
        void agendadaImplementa() {
            assertInstanceOf(ControleStrategy.class,
                new EstrategiaAgendada(LocalTime.of(7,0), LocalTime.of(23,0)));
        }
    }


    @Nested
    @DisplayName("EstrategiaFisica")
    class EstrategiaFisicaTest {

        private EstrategiaFisica e;

        @BeforeEach void setUp() { e = new EstrategiaFisica(); }

        @Test void ligar()   { assertTrue(e.ligar().contains("TV ligada")); }
        @Test void desligar(){ assertTrue(e.desligar().contains("TV desligada")); }

        @ParameterizedTest @ValueSource(ints = {1, 5, 13, 50})
        void mudarCanal(int canal) { assertTrue(e.mudarCanal(canal).contains(String.valueOf(canal))); }

        @ParameterizedTest @ValueSource(ints = {0, 10, 50, 100})
        void ajustarVolume(int vol) { assertTrue(e.ajustarVolume(vol).contains(String.valueOf(vol))); }

        @Test void nome() { assertTrue(e.nome().toLowerCase().contains("físico") || e.nome().toLowerCase().contains("fisico")); }
        @Test void prefixoFisico() { assertTrue(e.ligar().contains("FÍSICO") || e.ligar().contains("FISICO")); }
    }


    @Nested
    @DisplayName("EstrategiaVoz")
    class EstrategiaVozTest {

        @Test void ligar()    { assertTrue(new EstrategiaVoz().ligar().contains("TV ligada")); }
        @Test void desligar() { assertTrue(new EstrategiaVoz().desligar().contains("TV desligada")); }
        @Test void prefixoVoz() { assertTrue(new EstrategiaVoz().ligar().contains("VOZ")); }

        @Test void confiancaNoNome() {
            EstrategiaVoz e = new EstrategiaVoz(0.88);
            assertTrue(e.nome().contains("88"));
        }

        @Test void confiancaGetterOk() {
            assertEquals(0.75, new EstrategiaVoz(0.75).getConfianca(), 0.001);
        }

        @Test void confiancaInvalidaLancaExcecao() {
            assertThrows(IllegalArgumentException.class, () -> new EstrategiaVoz(1.5));
            assertThrows(IllegalArgumentException.class, () -> new EstrategiaVoz(-0.1));
        }

        @Test void mudarCanalContemNumero() { assertTrue(new EstrategiaVoz().mudarCanal(9).contains("9")); }
    }

    @Nested
    @DisplayName("EstrategiaApp")
    class EstrategiaAppTest {

        @Test void ligar()    { assertTrue(new EstrategiaApp().ligar().contains("TV ligada")); }
        @Test void desligar() { assertTrue(new EstrategiaApp().desligar().contains("TV desligada")); }
        @Test void prefixoApp() { assertTrue(new EstrategiaApp().ligar().contains("APP")); }

        @Test void dispositivoNoNome() {
            assertTrue(new EstrategiaApp("Tablet").nome().contains("Tablet"));
        }

        @Test void dispositivoGetter() {
            assertEquals("iPad", new EstrategiaApp("iPad").getDispositivo());
        }

        @Test void dispositivoPadraoSmartphone() {
            assertEquals("Smartphone", new EstrategiaApp().getDispositivo());
        }

        @Test void swipeNaMudancaDeCanal() {
            assertTrue(new EstrategiaApp().mudarCanal(3).toLowerCase().contains("swipe") ||
                       new EstrategiaApp().mudarCanal(3).contains("3"));
        }
    }


    @Nested
    @DisplayName("EstrategiaGestos")
    class EstrategiaGestosTest {

        @Test void ligar()    { assertTrue(new EstrategiaGestos().ligar().contains("TV ligada")); }
        @Test void desligar() { assertTrue(new EstrategiaGestos().desligar().contains("TV desligada")); }
        @Test void prefixoGesto() { assertTrue(new EstrategiaGestos().ligar().contains("GESTO")); }

        @Test void sensibilidadeAlta() {
            EstrategiaGestos e = new EstrategiaGestos(EstrategiaGestos.Sensibilidade.ALTA);
            assertTrue(e.nome().contains("ALTA"));
            assertEquals(EstrategiaGestos.Sensibilidade.ALTA, e.getSensibilidade());
        }

        @Test void sensibilidadePadraoMedia() {
            assertEquals(EstrategiaGestos.Sensibilidade.MEDIA, new EstrategiaGestos().getSensibilidade());
        }

        @Test void mudarCanalContemNumero() {
            assertTrue(new EstrategiaGestos().mudarCanal(7).contains("7"));
        }
    }

    @Nested
    @DisplayName("EstrategiaAgendada")
    class EstrategiaAgendadaTest {

        private EstrategiaAgendada e;

        @BeforeEach
        void setUp() { e = new EstrategiaAgendada(LocalTime.of(7, 30), LocalTime.of(22, 0)); }

        @Test void ligar()   { assertTrue(e.ligar().contains("TV ligada")); }
        @Test void desligar(){ assertTrue(e.desligar().contains("TV desligada")); }
        @Test void prefixoAgendado() { assertTrue(e.ligar().contains("AGENDADO")); }

        @Test void horarioLigarNoResultado() {
            assertTrue(e.ligar().contains("07:30"));
        }

        @Test void horarioDesligarNoResultado() {
            assertTrue(e.desligar().contains("22:00"));
        }

        @Test void gettersOk() {
            assertEquals(LocalTime.of(7, 30),  e.getHorarioLigar());
            assertEquals(LocalTime.of(22, 0),  e.getHorarioDesligar());
        }

        @Test void nomeContemHorarios() {
            assertTrue(e.nome().contains("07:30"));
            assertTrue(e.nome().contains("22:00"));
        }
    }


    @Nested
    @DisplayName("ControleRemoto (Context)")
    class ControleRemotoContextTest {

        private ControleRemoto ctrl;

        @BeforeEach
        void setUp() { ctrl = new ControleRemoto(new EstrategiaFisica()); }

        @Test
        @DisplayName("Construtor com null lança exceção")
        void ctorNullLancaExcecao() {
            assertThrows(IllegalArgumentException.class, () -> new ControleRemoto(null));
        }

        @Test
        @DisplayName("setEstrategia com null lança exceção")
        void setNullLancaExcecao() {
            assertThrows(IllegalArgumentException.class, () -> ctrl.setEstrategia(null));
        }

        @Test
        @DisplayName("getEstrategia retorna estratégia ativa")
        void getEstrategiaOk() {
            assertInstanceOf(EstrategiaFisica.class, ctrl.getEstrategia());
        }

        @Test
        @DisplayName("Troca de estratégia em runtime funciona")
        void trocaEstrategia() {
            ctrl.setEstrategia(new EstrategiaVoz());
            assertInstanceOf(EstrategiaVoz.class, ctrl.getEstrategia());

            String r = ctrl.ligar();
            assertTrue(r.contains("VOZ"));
        }

        @Test
        @DisplayName("ligar delega à estratégia e registra no histórico")
        void ligarRegistraHistorico() {
            ctrl.ligar();
            assertEquals(1, ctrl.getHistorico().size());
        }

        @Test
        @DisplayName("Múltiplos comandos acumulam no histórico")
        void historicoAcumula() {
            ctrl.ligar();
            ctrl.mudarCanal(5);
            ctrl.ajustarVolume(10);
            ctrl.desligar();
            assertEquals(4, ctrl.getHistorico().size());
        }

        @Test
        @DisplayName("limparHistorico zera a lista")
        void limparHistorico() {
            ctrl.ligar();
            ctrl.desligar();
            ctrl.limparHistorico();
            assertTrue(ctrl.getHistorico().isEmpty());
        }

        @Test
        @DisplayName("Histórico é imutável externamente")
        void historicoImutavel() {
            ctrl.ligar();
            assertThrows(UnsupportedOperationException.class,
                () -> ctrl.getHistorico().add("invasão"));
        }

        @Test
        @DisplayName("estrategiaAtual retorna nome da estratégia ativa")
        void estrategiaAtualOk() {
            String info = ctrl.estrategiaAtual();
            assertTrue(info.contains("Físico") || info.contains("fisico") || info.contains("FISICO"));
        }

        @Test
        @DisplayName("Troca de estratégia muda o comportamento do canal")
        void trocaMudaComportamento() {
            String r1 = ctrl.mudarCanal(3);
            assertTrue(r1.contains("FÍSICO") || r1.contains("FISICO"));

            ctrl.setEstrategia(new EstrategiaApp("TV"));
            String r2 = ctrl.mudarCanal(3);
            assertTrue(r2.contains("APP"));
        }

        @Test
        @DisplayName("Múltiplas trocas de estratégia funcionam")
        void multiplasTrocas() {
            ctrl.setEstrategia(new EstrategiaVoz());
            ctrl.setEstrategia(new EstrategiaGestos());
            ctrl.setEstrategia(new EstrategiaFisica());
            assertInstanceOf(EstrategiaFisica.class, ctrl.getEstrategia());
        }
    }
}
