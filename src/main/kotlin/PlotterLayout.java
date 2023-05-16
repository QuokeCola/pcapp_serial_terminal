import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class PlotterLayout{
    public static JPanel main_panel = new JPanel();
    double[][] initdata = getSineData(1.0f);

    ArrayList<XYChart> charts = new ArrayList<XYChart>();
    PlotterLayout() throws InterruptedException, InvocationTargetException {
        XYChart chart = new XYChartBuilder().build();
        chart.getStyler().setTheme(new plot_theme_dark());
        chart.getStyler();
        main_panel.add(new XChartPanel<>(chart));
    }

    private static double[][] getSineData(double phase) {

        double[] xData = new double[100];
        double[] yData = new double[100];
        for (int i = 0; i < xData.length; i++) {
            double radians = phase + (2 * Math.PI / xData.length * i);
            xData[i] = radians;
            yData[i] = Math.sin(radians);
        }
        return new double[][] { xData, yData };
    }
}
