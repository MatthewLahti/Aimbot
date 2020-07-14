import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Aimbot {
	public void sleep(int n){try{TimeUnit.MILLISECONDS.sleep(n);}catch (InterruptedException e){}}
	public MouseListener myMouseListener;
	public Aimbot() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.exit(1);
		}
		myMouseListener = new MouseListener();
		GlobalScreen.addNativeMouseListener(myMouseListener);
		GlobalScreen.addNativeMouseMotionListener(myMouseListener);
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.WARNING);
		logger.setUseParentHandlers(false);
	}
	public static void main(String[] args){
		Aimbot cheat = new Aimbot();
		FrameGrab scanner = new FrameGrab();

		while (true){
			System.out.print("hi");
			while(cheat.myMouseListener.getPress()){
				scanner.whereShoot();
			}
			cheat.sleep(2);
		}
	}
}
