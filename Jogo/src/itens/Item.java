package itens;
import personagens.Personagem;

public class Item {
    private String nome;
    private String tipo;
    private String efeito;

    // Construtor
    public Item(String nome, String tipo, String efeito) {
        this.nome = nome;
        this.tipo = tipo;
        this.efeito = efeito;
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

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    // Método para usar o item
    public void usar(Personagem alvo) {
        // Falta implementar método 
    }
}

