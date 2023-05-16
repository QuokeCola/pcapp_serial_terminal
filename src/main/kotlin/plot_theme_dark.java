import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import org.knowm.xchart.style.colors.ChartColor;
import org.knowm.xchart.style.colors.SeriesColors;
import org.knowm.xchart.style.lines.XChartSeriesLines;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.XChartSeriesMarkers;
import org.knowm.xchart.style.theme.AbstractBaseTheme;

/**
 * @author timmolter
 */
public class plot_theme_dark extends AbstractBaseTheme {

    // Chart Style ///////////////////////////////

    @Override
    public Font getBaseFont() {

        return new Font("Jetbrains Mono", Font.PLAIN, 10);
    }

    @Override
    public Color getChartBackgroundColor() {

        return new Color(.0f,.0f,.0f,.0f);
    }

    @Override
    public Color getChartFontColor() {

        return new Color(.5f,.5f,.9f,1.0f);
    }

    @Override
    public int getChartPadding() {

        return 12;
    }

    @Override
    public Color[] getSeriesColors() {

        return new MyCustomSeriesColors().getSeriesColors();
    }

    @Override
    public Marker[] getSeriesMarkers() {

        return new XChartSeriesMarkers().getSeriesMarkers();
    }

    @Override
    public BasicStroke[] getSeriesLines() {

        return new XChartSeriesLines().getSeriesLines();
    }

    // Chart Title ///////////////////////////////

    @Override
    public Font getChartTitleFont() {
        return getBaseFont().deriveFont(Font.BOLD).deriveFont(18f);
    }

    @Override
    public Color getAxisTickLabelsColor() {
        return new Color(.8f,.8f,.8f,1.0f);
    }

    @Override
    public boolean isChartTitleBoxVisible() {

        return false;
    }

    @Override
    public Color getChartTitleBoxBackgroundColor() {

        return new Color(.0f,.0f,.0f,.0f);
    }

    @Override
    public Color getChartTitleBoxBorderColor() {

        return new Color(.0f,.0f,.0f,.0f);
    }
    @Override
    public Color getPlotBackgroundColor() {
        return new Color(.0f,.0f,.0f,.0f);
    }

    // Chart Legend ///////////////////////////////

    // Chart Axes ///////////////////////////////

    @Override
    public BasicStroke getAxisTickMarksStroke() {

        return new BasicStroke(
                1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10.0f, new float[] {3.0f, 0.0f}, 0.0f);
    }

    // Chart Plot Area ///////////////////////////////

    @Override
    public boolean isPlotTicksMarksVisible() {

        return false;
    }

    @Override
    public BasicStroke getPlotGridLinesStroke() {

        return new BasicStroke(
                0.25f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10.0f, new float[] {3.0f, 3.0f}, 0.0f);
    }

    // Category Charts ///////////////////////////////

    // Pie Charts ///////////////////////////////

    // Line, Scatter, Area Charts ///////////////////////////////

    @Override
    public int getMarkerSize() {

        return 16;
    }

    // Error Bars ///////////////////////////////

    // Annotations ///////////////////////////////
    class MyCustomSeriesColors implements SeriesColors {

        public final Color GREEN = new Color(0, 205, 0, 180);
        public final Color RED = new Color(205, 0, 0, 180);
        public final Color BLACK = new Color(0, 0, 0, 180);

        private final Color[] seriesColors;

        /** Constructor */
        public MyCustomSeriesColors() {

            seriesColors = new Color[] {GREEN, RED, BLACK};
        }

        @Override
        public Color[] getSeriesColors() {

            return seriesColors;
        }
    }
}