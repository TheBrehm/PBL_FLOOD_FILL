import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedImage imagem = null;
            String pathImagem = "imagem_entrada.png";
            File arquivoEntrada = new File(pathImagem);

            if (arquivoEntrada.exists()) {
                imagem = ImageIO.read(arquivoEntrada);
                System.out.println("Imagem carregada: " + pathImagem);
            } else {
                System.out.println("Imagem nao encontrada");
            }

            // coordenada flood fill
            int x = 5;
            int y = 100;

            // Execucao com Pilha
            BufferedImage imagemPilha = copiarImagem(imagem);
            FloodFill floodFillPilha = new FloodFill(imagemPilha);
            floodFillPilha.preencherPilha(x, y, "animacao_pilha");
            ImageIO.write(floodFillPilha.getImagem(), "png", new File("resultado_pilha.png"));
            System.out.println("Salvando: resultado_pilha.png");
            ExibirTela exibirTela = new ExibirTela("animacao_pilha");
            exibirTela.exibir();

//            // Execucao com Fila
//            BufferedImage imagemFila = copiarImagem(imagem);
//            FloodFill floodFillFila = new FloodFill(imagemFila);
//            floodFillFila.preencherFila(x, y, "animacao_fila");
//            ImageIO.write(floodFillFila.getImagem(), "png", new File("resultado_fila.png"));
//            System.out.println("SALVANDO: resultado_fila.png");
//            ExibirTela exibirTela = new ExibirTela("animacao_pilha");
//            exibirTela.exibir();


            System.out.println("\nFINALIZANDO");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // cria uma copia
    private static BufferedImage copiarImagem(BufferedImage original) {
        BufferedImage copia = new BufferedImage(
            original.getWidth(), original.getHeight(), original.getType()
        );
        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                copia.setRGB(x, y, original.getRGB(x, y));
            }
        }
        return copia;
    }
}
