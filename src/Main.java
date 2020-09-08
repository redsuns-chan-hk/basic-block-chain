import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		Block firstBlock = new Block("testing");
		Block second = new Block(firstBlock, "This is the second block");
		Block third = new Block(second, "third");
		
		System.out.println("Is it able to validate the parent of third? " + second.validate(third));
		
		Block fakeSecond = new Block(new Block("test"), "This is the second block");
		
		System.out.println("Is the fake valid: " + second.validate(fakeSecond));
	}
}
