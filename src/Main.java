import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        GameBoard runGame = new GameBoard("Patrik");
    }

}
/*
 Utilklass med
   X - bakgrundsmusik
   X - clicka på ruta
   X - tidåtgång
   X - antal klick
   X - stoppa tiden
    - Scoreboard
   X - hitta "win condition" foreach genom alla ID getGrid (0,0)
         X - När alla ligger rätt bricka grön
        - annars röd

 Gameboard klass
    X - 16 gridlayout
    X - knapp med nytt spel
       X - scramblebuttons
    X- knapp med "fusk" lösning
        -allt hamnar på rättplats
    - meddelande när man vunnit
 X - Brickklass med brickobjekt
        X - knapp
        X - knappid
 */