import java.awt.image.BufferedImage;

public class Ferramentas {
    public static BufferedImage copiaImagem (BufferedImage img)
    {
        int largura = img.getWidth();
        int altura = img.getHeight();
        BufferedImage copia = new BufferedImage(largura,altura,BufferedImage.TYPE_INT_RGB);
        for(int linha=0; linha < altura; linha++)
        {
            for(int coluna=0; coluna <largura; coluna++)
            {
                int rgb = img.getRGB(coluna, linha);
                copia.setRGB(coluna, linha, rgb);
            }
        }
        return copia;
    }
    public static int corrigeCor (int cor)
    {
        if (cor < 0) {
            cor=0;
        } else if (cor > 255) {
            cor = 255;
        }
        return cor;
    }
}
