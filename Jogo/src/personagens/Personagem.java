package personagens;
import java.util.ArrayList;
import itens.Item;


public class Personagem {
    private String nome;
    private String classe;
    private int nivel;
    private int saude;
    private int mana;
    private int forca;
    private int magia;
    private int defesa;
    private ArrayList<Item> inventario;

    // Construtor
    public Personagem(String nome, String classe) {
        this.nome = nome;
        this.classe = classe;
        this.nivel = 1;
        this.saude = 100;
        this.mana = 100;
        this.forca = 10;
        this.magia = 10; 
        this.defesa = 5;
        this.inventario = new ArrayList<>();
    }

    // Métodos getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
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

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana >= 0) {
            this.mana = mana;
        }
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        if (forca >= 0) {
            this.forca = forca;
        }
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        if (magia >= 0) {
            this.magia = magia;
        }
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        if (defesa >= 0) {
            this.defesa = defesa;
        }
    }

    // Métodos adicionais
    public void adicionarItem(Item item) {
        inventario.add(item);
    }

    public void removerItem(Item item) {
        inventario.remove(item);
    }
}
