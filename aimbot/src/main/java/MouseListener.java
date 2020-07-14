import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class MouseListener implements NativeMouseInputListener {
	private boolean isPressed;
	public void nativeMouseClicked(NativeMouseEvent e) {}
	public void nativeMousePressed(NativeMouseEvent e) {
		if (e.getButton() == 2){
			isPressed = true;
		}
	}
	public void nativeMouseReleased(NativeMouseEvent e) {
		if (e.getButton() == 2){
			isPressed = false;
		}
	}
	public void nativeMouseMoved(NativeMouseEvent e) {}
	public void nativeMouseDragged(NativeMouseEvent e) {}
	public boolean getPress(){
		return isPressed;
	}
}
