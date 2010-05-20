import java.util.concurrent.Future;

import org.jetel.graph.Result;
import org.jetel.graph.TransformationGraph;
import org.jetel.graph.TransformationGraphXMLReaderWriter;
import org.jetel.graph.runtime.EngineInitializer;
import org.jetel.graph.runtime.GraphRuntimeContext;
import org.jetel.main.runGraph;

public class CloverTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		EngineInitializer.initEngine((String)null, null, null);

		TransformationGraphXMLReaderWriter graphReader = new TransformationGraphXMLReaderWriter(
				null);

		TransformationGraph graph = graphReader.read(CloverTest.class
				.getResource("ca_org_t_dafis.grf.xml").openStream());

		//graph.dumpGraphConfiguration();

		GraphRuntimeContext runtimeContext = new GraphRuntimeContext();
		runtimeContext.setUseJMX(false);
		
		Future<Result> result;

		try {

			result = runGraph.executeGraph(graph, runtimeContext);

			while (result.isDone()) {
				Thread.sleep( 250 );
			}

			if (!result.get().equals(Result.FINISHED_OK)) {

				System.out.println("Failed graph execution!\n");

				return;

			}

		} catch (Exception e) {

			System.out.println("Failed graph execution!\n" + e.getMessage());

			return;

		}

	}

}
