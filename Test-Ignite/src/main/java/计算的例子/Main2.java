package 计算的例子;

import java.util.List;
import java.util.Map;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeJobResultPolicy;
import org.apache.ignite.compute.ComputeTask;
import org.apache.ignite.lang.IgniteCallable;

/**
 * @author junmeng.xu
 * @date 2016年6月3日下午6:11:22
 * 单任务
 */
public class Main2 implements ComputeTask<String, String> {
	private static final long serialVersionUID = -3280311183196749826L;

	public static void main(String[] args) {

		try (Ignite ignite = Ignition
				.start("D:/javaWorkLianxi/Test-Ignite/src/main/java/example-ignite.xml")) {
			IgniteCompute igniteCompute = ignite.compute();
			Integer call = igniteCompute.call(new IgniteCallable<Integer>() {
				private static final long serialVersionUID = -892083995787650457L;
				@Override
				public Integer call() throws Exception {
					Integer sum = null;
					for (int i = 1; i < 99999; i++) {
						sum = i * (i + 1);
					}
					return sum;
				}
			});
			System.out.println("============" + call);
		}

	}

	@Override
	public Map<? extends ComputeJob, ClusterNode> map(
			List<ClusterNode> subgrid, String arg) throws IgniteException {
		return null;
	}

	@Override
	public ComputeJobResultPolicy result(ComputeJobResult res,
			List<ComputeJobResult> rcvd) throws IgniteException {
		return null;
	}

	@Override
	public String reduce(List<ComputeJobResult> results) throws IgniteException {
		return null;
	}

}
