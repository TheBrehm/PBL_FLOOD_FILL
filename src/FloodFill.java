import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;

public class FloodFill {

    private BufferedImage imagem;
    private int controle;
    private int cor;

    public FloodFill(BufferedImage imagem) {
        this.imagem = imagem;
        Color novaCor = new Color(71, 148, 230);
        this.controle = novaCor.getRGB();
    }

    // Flood Fill usando Pilha
    public void preencherPilha(int startX, int startY, String pastaAnimacao) {
        cor = imagem.getRGB(startX, startY);

        Pilha pilha = new Pilha();
        Pixel pixel = new Pixel(startX, startY);
        pilha.empilhar(pixel);

        int largura = imagem.getWidth();
        int altura = imagem.getHeight();
        int contador = 0;

        while (!pilha.estaVazia()) {
            pixel = pilha.desempilhar();
            int x = pixel.getX();
            int y = pixel.getY();

            // verifica se esta dentro dos limites da imagem
            if (x < 0 || x >= largura || y < 0 || y >= altura) {
                continue;
            }

            // verifica se a cor do pixel eh igual a cor original
            if (imagem.getRGB(x, y) != cor) {
                continue;
            }

            // pinta o pixel com a nova cor
            imagem.setRGB(x, y, controle);
            contador++;

            // salva frame da animacao a cada 100 pixels
            if (contador % 100 == 0) {
                salvarFrame(pastaAnimacao, contador);
            }

            // empilha os 4 vizinhos laterais
            pilha.empilhar(new Pixel(x + 1, y));
            pilha.empilhar(new Pixel(x - 1, y));
            pilha.empilhar(new Pixel(x, y + 1));
            pilha.empilhar(new Pixel(x, y - 1));
        }

        // salva o ultimo frame
        salvarFrame(pastaAnimacao, contador);
        System.out.println("Pilha: " + contador + " pixels preenchidos.");
    }

    // Flood Fill usando Fila
    public void preencherFila(int startX, int startY, String pastaAnimacao) {
        cor = imagem.getRGB(startX, startY);

        if (cor == controle) {
            return;
        }

        Fila fila = new Fila();
        Pixel pixel = new Pixel(startX, startY);
        fila.enfila(pixel);

        int largura = imagem.getWidth();
        int altura = imagem.getHeight();
        int contador = 0;

        while (!fila.estaVazia()) {
            pixel = fila.desenfila();
            int x = pixel.getX();
            int y = pixel.getY();

            // verifica se esta dentro dos limites da imagem
            if (x < 0 || x >= largura || y < 0 || y >= altura) {
                continue;
            }

            if (imagem.getRGB(x, y) != cor) {
                continue;
            }

            // pinta o pixel
            imagem.setRGB(x, y, controle);
            contador++;

            // salva frame da animacao a cada 100 pixels
            if (contador % 100 == 0) {
                salvarFrame(pastaAnimacao, contador);
            }

            // enfileira os 4 vizinhos laterais
            fila.enfila(new Pixel(x + 1, y));
            fila.enfila(new Pixel(x - 1, y));
            fila.enfila(new Pixel(x, y + 1));
            fila.enfila(new Pixel(x, y - 1));
        }

        // salva o ultimo frame
        salvarFrame(pastaAnimacao, contador);
        System.out.println("Fila: " + contador + " pixels preenchidos.");
    }

    private void salvarFrame(String pasta, int numero) {
        try {
            File diretorio = new File(pasta);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }
            String nomeArquivo = pasta + "/frame_" + String.format("%06d", numero) + ".png";
            ImageIO.write(imagem, "png", new File(nomeArquivo));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public BufferedImage getImagem() {
        return imagem;
    }
}
