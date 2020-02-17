package com.ignathick.hotel2.model.Guest;

import com.ignathick.hotel2.DateUtil;
import com.ignathick.hotel2.IFileUtil;
import com.senla.training.FileWorker;
import com.senla.training.TextFileWorker;

import java.util.ArrayList;
import java.util.List;

public class GuestIFileUtil implements IFileUtil<Guest> {

    /**
     * The file worker.
     */
    private final FileWorker fileWorker;

    /**
     * Instantiates a new room file util.
     */
    public GuestIFileUtil(String DATA_FILE) {
        fileWorker = new TextFileWorker(DATA_FILE);
    }

    @Override
    public List<Guest> readFromFile() {
        final String[] lines = fileWorker.readFromFile();

        if (lines == null || lines.length == 0) {
            throw new IllegalArgumentException();
        }

        //final Guest[] result = new Guest[lines.length];
        final List<Guest> result = new ArrayList<Guest>();

        for (int i = 0; i < lines.length; i++) {
            //result[i] = fromLine(lines[i]);
            result.add(fromLine(lines[i]));
        }

        return result;
    }

    @Override
    public void writeToFile(List<Guest> values) {

        if (values == null || values.size() == 0) {
            throw new IllegalArgumentException();
        }
        final String[] lines = new String[values.size()];
//        for (int i = 0; i < values.length; i++) {
//            lines[i] = toLine(values[i]);
//        }
        int i=0;
        for (Guest guest:
                values) {
            lines[i] = toLine(guest);
            i++;
        }

        fileWorker.writeToFile(lines);

    }

    @Override
    public String toLine(final Guest guest) {
        if (guest == null) {
            throw new IllegalArgumentException();
        }

        final String[] array = new String[]{
                String.valueOf(guest.getId()),
                String.valueOf(guest.getFirstName()),
                String.valueOf(guest.getLastName()),
                DateUtil.getDateAsString(guest.getDateOfBirth()),
                String.valueOf(guest.getPassport())
        };

        return String.join(";", array);
    }

    @Override
    public Guest fromLine(String line) {
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final String[] parts = line.split(";");

        final Guest result = new Guest(
                Long.valueOf(parts[0]),
                String.valueOf(parts[1]),
                String.valueOf(parts[2]),
                String.valueOf(parts[3]),
                String.valueOf(parts[4])
        );
        return result;
    }
}
