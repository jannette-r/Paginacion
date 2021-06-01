package os.pagereplacement;


/**
 * Esta clase genera referencias de pagina que van desde 0 a 9
*Uso: PageGenerator gen = new PageGenerator () int [] ref = gen.getReferenceString ();
 *
 */

public class PageGenerator {
	public static int DEFAULT_SIZE = 1000;
	public static final int RANGE = 999;

	int[] referenceString;

	public PageGenerator() {
		this(DEFAULT_SIZE);
	}

	public PageGenerator(int count) {
		this(count, RANGE);
	}
	
	public PageGenerator(int count, int range) {
		if (count < 0)
			throw new IllegalArgumentException();

		java.util.Random generator = new java.util.Random();
		referenceString = new int[count];

		for (int i = 0; i < count; i++)
			referenceString[i] = generator.nextInt(range);
	}

	public int[] getReferenceString() {
		return referenceString;
	}
}
