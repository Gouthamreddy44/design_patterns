package com.iluwatar.collectingparameter;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class PrinterQueue {


    static PrinterQueue currentInstance = null;
    public static Queue<PrinterItem> printerItemQueue;

    /**
     * This class is a singleton. The getInstance method will ensure that only one instance exists at a time.
     */
    public static PrinterQueue getInstance() {
        if (Objects.isNull(currentInstance)) {
            currentInstance = new PrinterQueue();
        }
        return currentInstance;
    }

    /**
     * Private constructor prevents instantiation, unless using the getInstance() method.
     */
    private PrinterQueue() {
        printerItemQueue = new LinkedList<>();
    }

    /**
     * Adds a single print job to the queue
     *
     * @param printerItem: The printing job to be added to the queue
     */
    public void addPrinterItem (PrinterItem printerItem) {
        printerItemQueue.add(printerItem);
    }


    public static class PrinterItem {
        PaperSizes paperSize;
        int pageCount;
        boolean isDoubleSided;
        boolean isColour;

        PrinterItem (PaperSizes paperSize, int pageCount, boolean isDoubleSided, boolean isColour) {

            if (!Objects.isNull(paperSize)) {
                this.paperSize = paperSize;
            } else {
                throw new IllegalArgumentException();
            }

            if (pageCount > 0) {
                this.pageCount = pageCount;
            } else {
                throw new IllegalArgumentException();
            }

            this.isColour = isColour;
            this.isDoubleSided = isDoubleSided;
        }

    }

}