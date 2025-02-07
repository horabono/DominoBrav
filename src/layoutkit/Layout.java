package layoutkit;

import java.awt.Component;

/**
 *
 * @author Horacio Bono
 */
public final class Layout {
    public static final int SINGLE_MARGIN = 16;
    public static final int DOUBLE_MARGIN = SINGLE_MARGIN * 2;
    public static final int HALF_MARGIN = SINGLE_MARGIN / 2;

    public static int rightOf(Component c) {
		return c.getX() + c.getWidth();
	}

	public static int bottomOf(Component c) {
		return c.getY() + c.getHeight();
	}
	
	public static void setWidthToRight(Component c, int right) {
		setWidth(c, right - c.getX());
	}
	
	public static void setWidthToLeft(Component c, int left) {
		setWidth(c, c.getWidth() + c.getX() - left);
	}
	
	public static void setHeightToBottom(Component c, int bottom) {
		setHeight(c, bottom - c.getY());
	}
	
	public static void setHeightToTop(Component c, int top) {
		setHeight(c, c.getHeight() + c.getY() - top);
	}
	
	public static void setWidth(Component c, int width) {
		c.setSize(width, c.getHeight());
	}
	
	public static void setHeight(Component c, int height) {
		c.setSize(c.getWidth(), height);
	}

}
