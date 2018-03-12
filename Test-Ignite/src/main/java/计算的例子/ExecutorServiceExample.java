package 计算的例子;

import java.util.concurrent.ExecutorService;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.lang.IgniteRunnable;

/**
 * @author junmeng.xu
 * @date 2016年6月12日下午4:53:45
 */
public class ExecutorServiceExample {

	@SuppressWarnings("serial")
	public static void main(String[] args) {

		try (Ignite ignite = Ignition
				.start("D:/javaWorkLianxi/Test-Ignite/src/main/java/example-ignite.xml")) {
			System.out.println();

			// Get cluster-enabled executor service.
			ExecutorService exec = ignite.executorService();
			// Iterate through all words in the sentence and create jobs.
			String[] split = "Print words using runnable".split(" ");
			for (final String word : split) {
				// Execute runnable on some node.
				exec.submit(new IgniteRunnable() {
					@Override
					public void run() {
						System.out.println(">>> Printing '" + word
								+ "' on this node from grid job.");
					}
				});

			}

		}
	}
}
