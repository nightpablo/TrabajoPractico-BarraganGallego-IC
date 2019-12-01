package Models;

import Utils.UygulamaParametre;

/**
 * Created by Furkan on 23.07.2017.
 */
public class Cromosoma {

    private String cromosoma;
    private double uygunlukDegeri;
    private double kumilatifDegeri;

    public String getCromosoma() {
        return cromosoma;
    }

    public void setCromosoma(String cromosoma) {
        this.cromosoma = cromosoma;
    }

    public double getUygunlukDegeri() {
        return uygunlukDegeri;
    }

    public void setUygunlukDegeri(double uygunlukDegeri) {
        this.uygunlukDegeri = uygunlukDegeri;
    }

    public double getKumilatifDegeri() {
        return kumilatifDegeri;
    }

    public void setKumilatifDegeri(double kumilatifDegeri) {
        this.kumilatifDegeri = kumilatifDegeri;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Learning Rate " + (binaryToInt(this.getCromosoma().substring(0, UygulamaParametre.GENOM1_LONGITUD - 1))) / (double) 100).append("\n");
        stringBuilder.append("Momentum Rate " + (binaryToInt(this.getCromosoma().substring(UygulamaParametre.GENOM1_LONGITUD, UygulamaParametre.GENOM1_LONGITUD + UygulamaParametre.GENOM2_LONGITUD - 1))) / (double) 100).append("\n");
        stringBuilder.append("Hidden Layer " + (binaryToInt(this.getCromosoma().substring(UygulamaParametre.GENOM1_LONGITUD + UygulamaParametre.GENOM2_LONGITUD, UygulamaParametre.GENOM_TOTAL_LONGITUD - 1)))).append("\n");

        return stringBuilder.toString();
    }

    private int binaryToInt(String substring) {

        return Integer.parseInt(substring, 2);
    }
}
