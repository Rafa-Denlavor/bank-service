package denlavor.project.utilities;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Utils {
    static NumberFormat realFormat = new DecimalFormat("R$ #,##0.00");

    public static String doubleToString(Double value)  {
        return realFormat.format(value);
    }
}
