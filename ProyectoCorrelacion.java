import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProyectoCorrelacion {
    public static void main(String[] args) {
        try {
            FileReader archivoF = new FileReader("C:\\Ejemplo\\de\\ruta\\startup-profit.csv");
            BufferedReader archivoL = new BufferedReader(archivoF);

            String registro;
            double sProfit = 0.0, sRD = 0.0, sMarketing = 0.0;
            double sProfitRD = 0.0, sProfitMarketing = 0.0;
            double sProfitCuadrado = 0.0, sRDCuadrado = 0.0, sMarketingCuadrado = 0.0;
            int contador = 0;

            archivoL.readLine();

            while ((registro = archivoL.readLine()) != null) {
                String[] campos = registro.split(",");

                double rdSpend = Double.parseDouble(campos[0]);
                double marketingSpend = Double.parseDouble(campos[2]);
                double profit = Double.parseDouble(campos[4]);

                sProfit += profit;
                sRD += rdSpend;
                sMarketing += marketingSpend;

                sProfitRD += profit * rdSpend;
                sProfitMarketing += profit * marketingSpend;

                sProfitCuadrado += profit * profit;
                sRDCuadrado += rdSpend * rdSpend;
                sMarketingCuadrado += marketingSpend * marketingSpend;

                contador++;
            }

            archivoL.close();
            archivoF.close();

            double correlacionProfitRD = calculoCorrelacion(contador, sProfit, sRD, sProfitRD, sProfitCuadrado, sRDCuadrado);
            double correlacionProfitMarketing = calculoCorrelacion(contador, sProfit, sMarketing, sProfitMarketing, sProfitCuadrado, sMarketingCuadrado);

            System.out.println("El coeficiente de correlacion entre Profit y R&D Spend: " + correlacionProfitRD);
            System.out.println("El coeficiente de correlacion entre Profit y Marketing Spend: " + correlacionProfitMarketing);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static double calculoCorrelacion(int n, double sumX, double sumY, double sumXY, double sumXCuadrado, double sumYCuadrado) {
        double numerador = n * sumXY - sumX * sumY;
        double denominador = Math.sqrt((n * sumXCuadrado - sumX * sumX) * (n * sumYCuadrado - sumY * sumY));
        return numerador / denominador;
    }
}