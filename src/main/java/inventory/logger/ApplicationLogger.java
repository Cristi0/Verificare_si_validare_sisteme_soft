package inventory.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationLogger
{
    private static final Logger logger = Logger.getLogger("Inventory");

    private ApplicationLogger()
    { }

    public static void log(Level level, String message)
    {
        logger.log(level, message);
    }
}