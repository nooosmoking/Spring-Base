package edu.school21.printer;

import edu.school21.render.*;

public class PrinterWithPrefixImpl implements Printer {
    Renderer renderer;
    String prefix = "";

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String str) {
        renderer.print(prefix + " " + str);
    }
}
