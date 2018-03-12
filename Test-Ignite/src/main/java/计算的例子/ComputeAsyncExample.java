package 计算的例子;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.lang.IgniteFuture;
import org.apache.ignite.lang.IgniteRunnable;

/**
 * @author junmeng.xu
 * @date 2016年6月12日下午4:42:30
 */
/**
 * Demonstrates a simple use of {@link IgniteRunnable}.
 * <p>
 * Remote nodes should always be started with special configuration file which
 * enables P2P class loading: {@code 'ignite.{sh|bat} examples/config/example-ignite.xml'}.
 * <p>
 * Alternatively you can run {@link ExampleNodeStartup} in another JVM which will start node
 * with {@code examples/config/example-ignite.xml} configuration.
 */
public class ComputeAsyncExample {

	/**
	 * Executes example.
	 *
	 * @param args
	 *            Command line arguments, none required.
	 * @throws IgniteException
	 *             If example execution failed.
	 */
	public static void main(String[] args) throws IgniteException {
		try (Ignite ignite = Ignition
				.start("D:/javaWorkLianxi/Test-Ignite/src/main/java/example-ignite.xml")) {
			System.out.println();
			System.out.println("Compute asynchronous example started.");

			// Enable asynchronous mode.
			IgniteCompute compute = ignite.compute().withAsync();

			Collection<IgniteFuture<?>> futs = new ArrayList<>();

			// Iterate through all words in the sentence and create runnable
			// jobs.
			String str = "";
			for (int i = 0; i < 1000; i++) {
				str = str + i + "gukitukfa";
			}
			String[] split = str.split("a");
			
			for (final String word : split) {
				// Execute runnable on some node.
				compute.run(new IgniteRunnable() {
					@Override
					public void run() {
						System.out.println();
						System.out.println(">>> Printing '" + word
								+ "' on this node from ignite job.");
					}
				});

				futs.add(compute.future());
			}

			// Wait for completion of all futures.
			for (IgniteFuture<?> fut : futs){
				fut.get();
			}

			System.out.println();
			System.out
					.println(">>> Finished printing words using runnable execution.");
			System.out
					.println(">>> Check all nodes for output (this node is also part of the cluster).");
		}
	}

}
