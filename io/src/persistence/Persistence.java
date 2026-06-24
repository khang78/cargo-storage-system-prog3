package persistence;

import geschaeftslogik.Manager;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class Persistence {
    public void saveJOS(Manager file) {

        try (
            FileOutputStream fos = new FileOutputStream("file.jos");
            ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(file);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public Manager loadJOS() {
        Manager file = null;

        try (
            FileInputStream fis = new FileInputStream("file.jos");
            ObjectInputStream ois = new ObjectInputStream(fis);) {
            file = (Manager) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return file;
    }

    public void saveJBP(Manager manager) {
        try(
                XMLEncoder encoder=new XMLEncoder(new BufferedOutputStream(
                        new FileOutputStream("file.xml")));){
            encoder.writeObject(manager);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Manager loadJBP() {
        try (
                XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                        new FileInputStream("file.xml")));
        ) {
            return (Manager) decoder.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}