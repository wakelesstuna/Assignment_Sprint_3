import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        GameBoard runGame = new GameBoard("Patrik");
    }

}
/*
 Utilklass med
    - bakgrundsmusik
    - clicka på ruta
    - tidåtgång
    - antal klick
    - stoppa tiden
    - Scoreboard
    - hitta "win condition" foreach genom alla ID getGrid (0,0)
        - När alla ligger rätt bricka grön
        - annars röd

 Gameboard klass
    - 16 gridlayout
    - knapp med nytt spel
        - scramblebuttons
    - knapp med "fusk" lösning
        -allt hamnar på rättplats
    - meddelande när man vunnit
 Brickklass med brickobjekt
    - knapp
    - knappid
 */