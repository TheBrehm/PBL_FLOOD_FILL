public class No {
    private Pixel dado;
    private No proximo;

    public No(Pixel dado) {
        this.dado = dado;
        this.proximo = null;
    }

    public Pixel getDado() {
        return dado;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}
