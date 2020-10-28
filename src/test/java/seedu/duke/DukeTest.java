package seedu.duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DukeTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    private Ui ui;

    private Storage storage;

    private UserData data;

    private Parser currentParse;
    private InputStream stdin = System.in;
    private ByteArrayInputStream inStream;



    public void setupComponents(String inputString) {


        inStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inStream);
        Scanner scan = new Scanner(System.in);

        ui = new Ui();
        storage = new Storage("mainTest", ui);
        data = new UserData();
        currentParse = new Parser();

    }

    @Test
    public void multiParser_multipleValidCommands_allCommandsRun() {

        System.setOut(new PrintStream(outputStreamCaptor));
        String inputString = "add personal birthday; 03/01/2001; 0000; | list personal | delete personal 1\r\n";

        try {
            setupComponents(inputString);
            String userInput = ui.receiveCommand();
            ArrayList<String> allCommandInputs= currentParse.multiParse(userInput);
            for (String commInputs : allCommandInputs) {
                ui.printDividerLine();
                Command c = currentParse.parse(commInputs);
                c.execute(data, ui, storage);
            }

            assertEquals("_________________________________" + System.lineSeparator()
                            + "You have successfully added this event to your list!" + System.lineSeparator()
                            + "[P][✕] birthday on 2001-01-03, 00:00" + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "Here is a list of your Personal events:" + System.lineSeparator()
                            + "1. [P][✕] birthday on 2001-01-03, 00:00" + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "You have successfully deleted this event!" + System.lineSeparator()
                            + "[P][✕] birthday on 2001-01-03, 00:00" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        } catch (DukeException e) {
            fail("Should not have any errors in executing commands");
        } finally {
            System.setIn(stdin);
        }

    }

    @Test
    public void multiParser_singleValidCommand_singleCommandsRun() {

        System.setOut(new PrintStream(outputStreamCaptor));
        String inputString = "add personal birthday; 03/01/2001; 0000;\r\n";

        try {
            setupComponents(inputString);
            String userInput = ui.receiveCommand();
            ArrayList<String> allCommandInputs= currentParse.multiParse(userInput);
            for (String commInputs : allCommandInputs) {
                ui.printDividerLine();
                Command c = currentParse.parse(commInputs);
                c.execute(data, ui, storage);
            }

            assertEquals("_________________________________" + System.lineSeparator()
                            + "You have successfully added this event to your list!" + System.lineSeparator()
                            + "[P][✕] birthday on 2001-01-03, 00:00" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        } catch (DukeException e) {
            fail("Should not have any errors in executing commands");
        } finally {
            System.setIn(stdin);
        }

    }

    @Test
    public void multiParser_singleErrorCommand_singleCommandsRunWithException() {

        System.setOut(new PrintStream(outputStreamCaptor));
        String inputString = "add personal birthday; 03/01/2001; 0000; | delete 1\r\n";

        try {
            setupComponents(inputString);
            String userInput = ui.receiveCommand();
            ArrayList<String> allCommandInputs= currentParse.multiParse(userInput);
            for (String commInputs : allCommandInputs) {
                ui.printDividerLine();
                Command c = currentParse.parse(commInputs);
                c.execute(data, ui, storage);
            }


        } catch (DukeException e) {
            assertTrue(true);
        } finally {
            System.setIn(stdin);
            assertEquals("_________________________________" + System.lineSeparator()
                            + "You have successfully added this event to your list!" + System.lineSeparator()
                            + "[P][✕] birthday on 2001-01-03, 00:00" + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        }

    }
    @Test
    public void sampleTest() {
        assertTrue(true);
    }
}
