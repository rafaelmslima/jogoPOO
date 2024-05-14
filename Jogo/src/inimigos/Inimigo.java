package inimigos;
import personagens.Personagem;

public class Inimigo {
    private String nome;
    private String tipo;
    private int nivel;
    private int saude;
    private int forca;
    private int defesa;

    // Construtor
    public Inimigo(String nome, String tipo, int nivel) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
        this.saude = 100; 
    }

    // Métodos getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getSaude() {
        return saude;
    }

    public void setSaude(int saude) {
        if (saude >= 0) {
            this.saude = saude;
        }
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    // Métodos adicionais
    public void atacar(Personagem alvo) {
        // Implementar método
    }

    public void receberDano(int dano) {
        // Implementar método
    }
}
