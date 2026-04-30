import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EstrategiaAgendada implements ControleStrategy {

    private final LocalTime horarioLigar;
    private final LocalTime horarioDesligar;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm");

    public EstrategiaAgendada(LocalTime horarioLigar, LocalTime horarioDesligar) {
        this.horarioLigar    = horarioLigar;
        this.horarioDesligar = horarioDesligar;
    }

    @Override
    public String ligar() {
        return "⏰ [AGENDADO] Programado para ligar às " + horarioLigar.format(FMT) + " → TV ligada.";
    }

    @Override
    public String desligar() {
        return "⏰ [AGENDADO] Programado para desligar às " + horarioDesligar.format(FMT) + " → TV desligada.";
    }

    @Override
    public String mudarCanal(int canal) {
        return "⏰ [AGENDADO] Canal " + canal + " programado → Canal " + canal + " selecionado.";
    }

    @Override
    public String ajustarVolume(int nivel) {
        return "⏰ [AGENDADO] Volume " + nivel + " programado → Volume em " + nivel + ".";
    }

    @Override
    public String nome() {
        return "Controle Agendado (liga=" + horarioLigar.format(FMT)
             + ", desliga=" + horarioDesligar.format(FMT) + ")";
    }

    public LocalTime getHorarioLigar()    { return horarioLigar; }
    public LocalTime getHorarioDesligar() { return horarioDesligar; }
}
