package best.wecode.shared;

import java.io.IOException;

public class Shared {
    public void clear() throws IOException {
        if ((System.getProperty("os.name").contains("Windows"))) {
            Runtime.getRuntime().exec("cls");
        } else {
            Runtime.getRuntime().exec("clear");
        }
    }
}
