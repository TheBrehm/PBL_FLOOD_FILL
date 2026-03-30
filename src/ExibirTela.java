import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ExibirTela {

    private String pasta;

    public ExibirTela(String pasta) {
        this.pasta = pasta;
    }

    public void exibir() {
        int qtdade = 0;
        try {
            File dir = new File(pasta);
            String[] nomesArquivos = dir.list((d, nome) -> nome.endsWith(".png"));

            if (nomesArquivos == null || nomesArquivos.length == 0) {
                System.out.println("Nenhuma imagem " + pasta);
                return;
            }

            // cria a janela
            JFrame frame = new JFrame("TELA" + pasta);
            JLabel label = new JLabel();
            frame.add(label);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            for (int i = 0; i < nomesArquivos.length; i++) {
                    File f = new File(pasta + "/" + nomesArquivos[i]);
                    BufferedImage img = ImageIO.read(f);
                    label.setIcon(new ImageIcon(img));
                    qtdade = nomesArquivos.length + 1;
                }


        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
}