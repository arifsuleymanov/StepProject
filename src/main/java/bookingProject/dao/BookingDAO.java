package bookingProject.dao;

import bookingProject.entity.Booking;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookingDAO implements DAO<Booking> {

    private File file;

    public BookingDAO(String filename) {
        this.file = new File(filename);
    }

    @Override
    public Collection<Booking> getAll() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            Object readed = ois.readObject();
            @SuppressWarnings("unchecked")
            List<Booking> as = (ArrayList<Booking>) readed;
            return as;
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Deserialization error. Didn't you forget to include 'serialVersionUID field' in your entity?", ex);
        } catch (FileNotFoundException ex) {
            return new ArrayList<>();
        } catch (IOException ex) {
            throw new RuntimeException("Something went wrong", ex);
        }
    }

    @Override
    public Collection<Booking> getAllBy(Predicate<Booking> p) {
        return getAll().stream().filter(p).collect(Collectors.toList());
    }

    @Override
    public Optional<Booking> get(int id) {
        return getAll().stream().filter(s -> s.getId() == id).findFirst();
    }

    @Override
    public void create(Booking data) {
        Collection<Booking> as = getAll();
        as.add(data);
        write(as);
    }

    @Override
    public void delete(long id) {
        Collection<Booking> as = getAllBy(s -> s.getId() != id);
        write(as);
    }

    @Override
    public void update(Collection<Booking> bookings) {
        write(bookings);
    }

    private void write(Collection<Booking> as) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            oos.writeObject(as);
        } catch (IOException ex) {
            throw new RuntimeException("DAO:write:IOException", ex);
        }
    }
}
