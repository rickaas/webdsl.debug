package util;

import org.junit.Test;
import org.strategoxt.HybridInterpreter;

public class WebdslVersionTest {

	@Test
	public void testWebdslVersion() {
		HybridInterpreter i = HybridInterpreterHelper.createHybridInterpreter();
		HybridInterpreterHelper.safeInvoke(i, "get-webdsl-version");
		System.out.println(i.current().toString());
		i.uninit();
		i.shutdown();
	}
}
