package cargoObserver;

public interface Subjekt {
    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
    void notifyObserver();
}
