import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinUser;

public class MoveMouse {
	public int xDirection;
	public int yDirection;
	public int ZEROX;
	public int ZEROY;
	public int xAimPix;
	public int yAimPix;
	public int aimOffsetX;
	public int aimOffsetY;
	public int rootX;
	public int rootY;
	public int moveX;
	public int moveY;

	public MoveMouse() {
		// Crosshair location
		// X zeroing is screen width // 2, Y zeroing is screen height // 2. For a 1080 * 1920  monitor, X zeroing is 960,  and Y zeroing is 540
		ZEROX = 960;
		ZEROY = 540;
	}

	public void moveHere(int xAimPixel, int yAimPixel) {
		// What pixel to aim at
		xAimPix = xAimPixel - ZEROX;
		yAimPix = yAimPixel - ZEROY;

		// What direction the mouse should be moving
		xDirection = -1;
		yDirection = -1;
		if (xAimPix > 0) {
			xDirection = 1;
		}
		if (yAimPix > 0) {
			yDirection = 1;
		}
		aimOffsetX = xAimPix * xDirection;
		aimOffsetY = yAimPix * yDirection;

		rootX = (int) Math.ceil((float) Math.pow((float) aimOffsetX, (float) 0.88));
		rootY = (int) Math.ceil((float) Math.pow((float) aimOffsetY, (float) 0.88));

		moveX = rootX * xDirection;
		moveY = rootY * yDirection;

		WinUser.INPUT input = new WinUser.INPUT();
		input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_MOUSE);
		input.input.setType("mi");
		// The speed the aimbot should move in the X direction
		input.input.mi.dx = new WinDef.LONG((long) (moveX * 3));
		// The speed the aimbot should move in the Y direction
		input.input.mi.dy = new WinDef.LONG(moveY);
		input.input.mi.dwFlags = new DWORD(0x0001);
		User32.INSTANCE.SendInput(new DWORD(1), (WinUser.INPUT[]) input.toArray(2), input.size());
	}
}
