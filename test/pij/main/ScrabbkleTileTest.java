package pij.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScrabbkleTileTest {
    ScrabbkleTile tile;

    @BeforeEach
    void buildUp(){
        tile = new ScrabbkleTile('A');
    }

    @Test
    void shouldReturnIntValueOf1() {
        int expected = 1;
        int actual = tile.getValue();
        assertEquals(expected,actual);
    }

    @Test
    void shouldReturnPrintOnBoardLabelOfA1() {
        String expected = "A1";
        String actual = tile.getPrintOnBoardFormat();
        assertEquals(expected,actual);
    }

    @Test
    void shouldReturnPrintOnTileRackLabel() {
        String expected = "[A1]";
        String actual = tile.getPrintInTileRackFormat();
        assertEquals(expected,actual);
    }


}