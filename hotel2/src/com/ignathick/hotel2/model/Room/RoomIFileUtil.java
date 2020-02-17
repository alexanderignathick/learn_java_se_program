package com.ignathick.hotel2.model.Room;

import com.ignathick.hotel2.DateUtil;
import com.ignathick.hotel2.IFileUtil;
import com.senla.training.FileWorker;
import com.senla.training.TextFileWorker;

import java.util.ArrayList;
import java.util.List;


public class RoomIFileUtil implements IFileUtil<Room> {

    /**
     * The file worker.
     */
    private final FileWorker fileWorker;

    /**
     * Instantiates a new room file util.
     */
    public RoomIFileUtil(String DATA_FILE) {
        fileWorker = new TextFileWorker(DATA_FILE);
    }

    /* (non-Javadoc)
     * @see com.senla.training.example.FileUtil#readFromFile()
     */
    @Override
    public List<Room> readFromFile() {

        final String[] lines = fileWorker.readFromFile();

        if (lines == null || lines.length == 0) {
            throw new IllegalArgumentException();
        }

        final List<Room> result = new ArrayList<Room>();

        for (int i = 0; i < lines.length; i++) {
            result.add(fromLine(lines[i]));
        }

        return result;

    }

    /* (non-Javadoc)
     * @see com.senla.training.example.FileUtil#writeToFile(java.lang.Object[])
     */
    @Override
    public void writeToFile(final List<Room> values) {
        if (values == null || values.size() == 0) {
            throw new IllegalArgumentException();
        }
        final String[] lines = new String[values.size()];
        int i=0;
        for (Room room:
             values) {
            lines[i] = toLine(room);
            i++;
        }
        fileWorker.writeToFile(lines);
    }

    /* (non-Javadoc)
     * @see com.senla.training.example.FileUtil#toLine(java.lang.Object)
     */
    @Override
    public String toLine(final Room room) {

        if (room == null) {
            throw new IllegalArgumentException();
        }

        final String[] array = new String[]{
                String.valueOf(room.getId()),
                String.valueOf(room.getNumber()),
                String.valueOf(room.getCapacity()),
                String.valueOf(room.getPrice()),
                String.valueOf(room.getStar()),
                String.valueOf(room.getStatus()),
                DateUtil.getDateAsString(room.getBusyFrom()),
                DateUtil.getDateAsString(room.getBusyTo())
        };

        return String.join(";", array);
    }

    @Override
    public Room fromLine(String line) {
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final String[] parts = line.split(";");
        final Room result = new Room(
                Long.valueOf(parts[0]),
                Integer.valueOf(parts[1]),
                Integer.valueOf(parts[2]),
                Double.valueOf(parts[3]),
                Star.valueOf(parts[4]),
                Status.valueOf( parts[5]),
                String.valueOf( parts[6]),
                String.valueOf( parts[7])
        );
        return result;
    }
}
