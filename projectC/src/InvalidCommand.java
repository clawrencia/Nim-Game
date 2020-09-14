
/**
 * An exception class for an invalid command passed to the system
* @author  Clarisca Lawrencia
* @idnumber 1152594
* @username clawrencia
*/

public class InvalidCommand extends Exception
{
	public InvalidCommand(String InvalidCommandMessage)
	{
		super(InvalidCommandMessage);
	}
}
