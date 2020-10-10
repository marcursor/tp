package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.event.Repeat;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Command to repeat task.
 */
public class RepeatCommand extends Command {
    private static final String COMMANDTYPE_LIST = "list";
    private static final String COMMANDTYPE_ADD = "add";
    private String commandType;

    /**
     * Constructor for the repeat command.
     *
     * @param command user input with the format eventIndex; eventType; timeInterval; NumberofIterations
     */
    public RepeatCommand(String command, String commandType) {
        this.isExit = false;
        this.command = command;
        this.commandType = commandType;
    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) {
        switch (commandType) {
        case COMMANDTYPE_ADD:
            executeAdd(data, ui, storage);
            break;
        case COMMANDTYPE_LIST:
            executeList(data, ui);
            break;
        default:
            //do nothing
        }
    }

    public static Command parse(String input) {
        String[] words = input.split(" ");
        switch (words.length) {
        case 2:
            words[0] = formatListName(words[0]);
            isValidNumber(words[1]);
            input = String.join(" ", words);
            return new RepeatCommand(input, COMMANDTYPE_LIST);
        case 4:
            words[0] = formatListName(words[0]);
            isValidNumber(words[1]);
            words[2] = words[2].toUpperCase();
            isValidNumber(words[3]);
            input = String.join(" ", words);
            return new RepeatCommand(input, COMMANDTYPE_ADD);
        default:
            System.out.println("Invalid repeat command");
            return null; //to change to throw exception or print help for repeat
        }
    }

    private static String formatListName(String name) {
        name = name.toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private static void isValidNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            //to throw exception
            System.out.println("Not a number");
        }
    }

    private void executeList(UserData data, Ui ui) {
        String[] words = command.split(" ");
        EventList eventList = data.getEventList(words[0]);
        int index = Integer.parseInt(words[1]) - 1;
        Event repeatEvent = eventList.getEventByIndex(index);
        ui.printRepeatList(repeatEvent);
    }

    private void executeAdd(UserData data, Ui ui, Storage storage) {
        String[] words = command.split(" ");
        EventList eventList = data.getEventList(words[0]);
        int index = Integer.parseInt(words[1]) - 1;
        Event eventToRepeat = eventList.getEventByIndex(index);
        LocalDate startDate = eventToRepeat.getDate();
        if (startDate == null) {
            System.out.println("Unable to repeat an event with no date.");
            //to change to throw exception
        }
        LocalTime startTime = eventToRepeat.getTime();
        int count = Integer.parseInt(words[3]);
        Repeat repeat = new Repeat(startDate, startTime, words[2], count);
        eventToRepeat.setRepeat(repeat);
        ui.printRepeatAdd(eventToRepeat);
    }
}
