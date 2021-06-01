package os.pagereplacement.algorithm;

import java.util.Arrays;

/**La implementación de todos los algoritmos tiene una base común: el acceso a los marcos de memoria y la solicitud de una página que no genera
*Fallo de página. La implementación de este tipo de solicitud es simple: cuando una página ya está en un marco, no hay falla de página, en caso de de lo contrario, 
*si hay un marco libre, la página solicitada se puede insertar sin necesidad de elegir una página para reemplazarla.

*/
public abstract class ReplacementAlgorithm {

	public static final int EMPTY_FRAME_VALUE = -1;

	private final String name;
	protected int pageFaultCount;
	protected int pageFrameSize;
	protected int[] frames;


	public ReplacementAlgorithm(int pageFrameSize, String name) {
		this.name = name;
		if (pageFrameSize <= 0) {
			throw new IllegalArgumentException();
		}
		this.pageFrameSize = pageFrameSize;
		this.pageFaultCount = 0;

		frames = new int[pageFrameSize];
		Arrays.fill(frames, -1);
	}

	public int getPageFaultCount() {
		return pageFaultCount;
	}

	public int[] getFrames() {
		return frames;
	}

	public abstract int insert(int pageNumber);

	/**
	*Si la página aún no está en un marco, aumenta la cantidad de páginas
	*fallas e intente insertar la página en un marco vacío, si lo hubiera
	*/
	
	protected int tryBasicInsert(int pageNumber) {
		int pageFrameIndex = getPageFrameIndex(pageNumber);
		if (pageFrameIndex != -1) {
			return pageFrameIndex;
		}
		pageFaultCount++;
		int freeFrameIndex = tryInsertFreeFrame(pageNumber);
		if (freeFrameIndex == -1) {
			return -1;
		} else {
			return freeFrameIndex;
		}
	}

	
	/**
	*Obtiene el índice de una página en el vector de marco, si está allí.
	*
	 */
	public int getPageFrameIndex(int pageNumber) {
		for (int i = 0; i < frames.length; i++) {
			if (pageNumber == frames[i]) {
				return i;
			}
		}
		return -1;
	}

	
	/**
	* Busca un marco libre
	*índice de retorno de un fotograma libre o -1 (si no hay un fotograma libre)
	**/
	
	protected int findFreeFrameIndex() {
		for (int i = 0; i < frames.length; i++) {
			if (frames[i] == -1) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	*Inserta la página en un marco libre, si lo hubiera
	*/

	protected int tryInsertFreeFrame(int pageNumber) {
		int freeFrameIndex = findFreeFrameIndex();
		if (freeFrameIndex != -1) {
			frames[freeFrameIndex] = pageNumber;
			return freeFrameIndex;
		}
		return -1;
	}

	public String getName() {
		return name;
	}
}
