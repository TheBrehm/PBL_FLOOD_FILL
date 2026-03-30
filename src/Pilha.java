public class Pilha {
    private No topo;
    private int tamanho;

    public Pilha() {
        this.topo = null;
        this.tamanho = 0;
    }

    public void empilhar(Pixel pixel) {
        No novoNo = new No(pixel);
        novoNo.setProximo(topo);
        topo = novoNo;
        tamanho++;
    }

    public Pixel desempilhar() {
        if (estaVazia()) {
            return null;
        }
        Pixel dado = topo.getDado();
        topo = topo.getProximo();
        tamanho--;
        return dado;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}
