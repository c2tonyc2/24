import java.util.Arrays;

public class TestDeck {
	public static void main(String[] args) {
		Deck proxy = new Deck("no royals");
		System.out.println(Arrays.toString(proxy.deal(4)));
		System.out.println(Arrays.toString(proxy.deal(4)));
		System.out.println(Arrays.toString(proxy.deal(4)));
		System.out.println(Arrays.toString(proxy.deal(4)));
		System.out.println(Arrays.toString(proxy.deal(4)));
		System.out.println(Arrays.toString(proxy.deal(4)));
		System.out.println(Arrays.toString(proxy.deal(4)));
		System.out.println(Arrays.toString(proxy.deal(4)));
		System.out.println(Arrays.toString(proxy.deal(4)));
		System.out.println(Arrays.toString(proxy.deal(4)));
		System.out.println(Arrays.toString(proxy.deal(4)));
		System.out.println(Arrays.toString(proxy.deal(4)));
	}
}