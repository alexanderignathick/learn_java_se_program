package com.ignathick.hotel2.model.Order;

import com.ignathick.hotel2.DateUtil;
import com.ignathick.hotel2.IFileUtil;
import com.ignathick.hotel2.model.Guest.Guest;
import com.ignathick.hotel2.model.Room.Room;
import com.senla.training.FileWorker;
import com.senla.training.TextFileWorker;

import java.util.ArrayList;
import java.util.List;


public class OrderIFileUtil implements IFileUtil<Order> {

    /**
     * The file worker.
     */
    private final FileWorker fileWorker;

    /**
     * Instantiates a new room file util.
     */
    public OrderIFileUtil(String DATA_FILE) {
        fileWorker = new TextFileWorker(DATA_FILE);
    }

    /* (non-Javadoc)
     * @see com.senla.training.example.FileUtil#readFromFile()
     */
    @Override
    public List<Order> readFromFile() {

        final String[] lines = fileWorker.readFromFile();

        if (lines == null || lines.length == 0) {
            throw new IllegalArgumentException();
        }

        final List<Order> result = new ArrayList<Order>();

        for (int i = 0; i < lines.length; i++) {
            result.add(fromLine(lines[i]));
        }

        return result;

    }

    /* (non-Javadoc)
     * @see com.senla.training.example.FileUtil#writeToFile(java.lang.Object[])
     */
    @Override
    public void writeToFile(final List<Order> values) {
        if (values == null || values.size() == 0) {
            throw new IllegalArgumentException();
        }
        final String[] lines = new String[values.size()];
        int i=0;
        for (Order order:
             values) {
            lines[i] = toLine(order);
            i++;
        }
        fileWorker.writeToFile(lines);
    }

    /* (non-Javadoc)
     * @see com.senla.training.example.FileUtil#toLine(java.lang.Object)
     */
    @Override
    public String toLine(final Order order) {

        if (order == null) {
            throw new IllegalArgumentException();
        }

        final String[] array = new String[]{
                String.valueOf(order.getId()),
                String.valueOf(order.getGuest()),
                String.valueOf(order.getRoom()),
                DateUtil.getDateAsString(order.getDateFrom()),
                DateUtil.getDateAsString(order.getDateTo()),
                String.valueOf(order.getOrderPrice())
        };

        return String.join(";", array);
    }

    @Override
    public Order fromLine(String line) {
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final String[] parts = line.split(";");
        final Order result = new Order(
                Long.valueOf(parts[0]),
                new Guest(),
                new Room(),
                String.valueOf(parts[3]),
                String.valueOf(parts[4]),
                Double.valueOf( parts[5])
        );
        return result;
    }
}
