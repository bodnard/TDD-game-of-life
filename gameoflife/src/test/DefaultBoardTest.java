import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import com.epam.exercises.gameoflife.Coordinate;
import com.epam.exercises.gameoflife.DefaultBoard;

public class DefaultBoardTest {


    private DefaultBoard subject = new DefaultBoard();

    @Test
    public void newBoardIsPopulatedWithEmptyCells() {
        List<Coordinate> coordinateList = List.of(
        new Coordinate(0,0),
        new Coordinate(1,0),
        new Coordinate(2,0),
        new Coordinate(3,0),
        new Coordinate(4,0),
        new Coordinate(5,0));

        for (Coordinate coordinate : coordinateList) {
            //GIVEN
            //WHEN
            boolean result = subject.isAlive(coordinate);
            //THEN
            Assert.assertFalse(result);
        }
    }

}