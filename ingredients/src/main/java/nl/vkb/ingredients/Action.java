package nl.vkb.ingredients;

public class Action {
	public static final ActionInterface SET=new SETImpl();
	public static final ActionInterface ADD=new ADDImpl();

	public interface ActionInterface {
		int action(int old, int action);
	}
	private static class ADDImpl implements ActionInterface {
		public int action(int old,int action) {
			return old+action;
		}
	}
	private static class SETImpl implements ActionInterface {
		public int action(int old,int action) {
			return action;
		}
	}
}
