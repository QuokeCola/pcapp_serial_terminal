import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.knowm.xchart.style.colors.SeriesColors;
import org.knowm.xchart.style.lines.XChartSeriesLines;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.XChartSeriesMarkers;
import org.knowm.xchart.style.theme.AbstractBaseTheme;

/**
 * @author Chen Qian
 */
public class PlotDarculaTheme extends AbstractBaseTheme {

    // Chart Style ///////////////////////////////

    @Override
    public Font getBaseFont() {

        return new Font("Jetbrains Mono", Font.PLAIN, 10);
    }

    @Override
    public Color getChartBackgroundColor() {

        return new DarculaColorScheme().TRANSPARENT;
    }

    @Override
    public Color getPlotBorderColor() {
        return new DarculaColorScheme().ANSIDarkGray;
    }

    @Override
    public Color getChartFontColor() {

        return new DarculaColorScheme().UIBlue;
    }

    @Override
    public int getChartPadding() {

        return 12;
    }

    @Override
    public Color[] getSeriesColors() {

        return new DarculaColorScheme().getSeriesColors();
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
    public boolean isChartTitleBoxVisible() {
        return false;
    }

    @Override
    public Color getChartTitleBoxBackgroundColor() {

        return new DarculaColorScheme().TRANSPARENT;
    }

    @Override
    public Color getChartTitleBoxBorderColor() {
        return new DarculaColorScheme().TRANSPARENT;
    }
    @Override
    public Color getPlotBackgroundColor() {
        return new DarculaColorScheme().TRANSPARENT;
    }

    // Chart Legend ///////////////////////////////

    // Chart Axes ///////////////////////////////
    @Override
    public Color getAxisTickLabelsColor() {
        return new DarculaColorScheme().ANSIGray;
    }

    @Override
    public Color getAxisTickMarksColor() {
        return new DarculaColorScheme().ANSIDarkGray;
    }
    @Override
    public BasicStroke getAxisTickMarksStroke() {

        return new BasicStroke(
                1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10.0f, new float[] {3.0f, 0.0f}, 0.0f);
    }

    // Chart Plot Area ///////////////////////////////

    @Override
    public Color getPlotGridLinesColor() {
        return new DarculaColorScheme().ANSIGray;
    }

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
    static class DarculaColorScheme implements SeriesColors {
        public final Color ANSIGreen = Color.decode("0xA8C023");
        public final Color ANSIRed = Color.decode("0xFF6B68");
        public final Color ANSIYellow = Color.decode("0xD6BF55");
        public final Color ANSIBlue = Color.decode("0x5394EC");
        public final Color ANSIMagenta = Color.decode("0xAE8ABE");
        public final Color ANSICyan = Color.decode("0x299999");
        public final Color ANSIGray = Color.decode("0x999999");
        public final Color ANSIDarkGray = Color.decode("0x555555");

        public final Color cursorLine = Color.decode("#323232");
        public final Color cursor = Color.decode("#BBBBBB");
        public final Color UIBlue = Color.decode("#3592C4");
        public final Color UIGreen = Color.decode("#499C54");
        public final Color UIRed = Color.decode("#C75450");
        public final Color UIBrown = Color.decode("#93896C");
        public final Color UIOrange = Color.decode("#CC7832");
        public final Color TRANSPARENT = new Color(0,0,0,.0f);
        private final Color[] seriesColors;

        /** Constructor */
        public DarculaColorScheme() {
            seriesColors = new Color[] {ANSIBlue, UIOrange, UIGreen, UIRed, ANSIYellow, ANSIMagenta, ANSICyan, ANSIGray, ANSIDarkGray};
        }

        @Override
        public Color[] getSeriesColors() {
            return seriesColors;
        }
    }
}