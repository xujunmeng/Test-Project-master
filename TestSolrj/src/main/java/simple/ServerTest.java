package simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author junmeng.xu
 * @date 2016年4月27日上午9:32:51
 */
public class ServerTest {

	private SolrServer server;
	private CommonsHttpSolrServer httpSolrServer;
	private static final String URL = "http://192.168.1.43:18282/solr/collection";

	@Before
	public void init() {
		try {
			server = new CommonsHttpSolrServer(URL);
			httpSolrServer = new CommonsHttpSolrServer(URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void destory() {
		server = null;
		httpSolrServer = null;
		System.runFinalization();
		System.gc();
	}

	public final void fail(Object o) {
		System.out.println(o);
	}

	// 测试是否创建server对象成功
	@Test
	public void server() {
		fail(server);
		fail(httpSolrServer);
	}

	// 根据query参数查询索引
	public void query(String query) {
		SolrParams params = new SolrQuery(query);
		try {
			QueryResponse response = server.query(params);

			SolrDocumentList list = response.getResults();
			for (int i = 0; i < list.size(); i++) {
				fail(list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 利用SolrJ完成Index Document的添加操作
	@Test
	public void addDoc() {
		// 创建doc文档
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", 100);
		doc.addField("name", "Solr Input Document");
		doc.addField("manu", "this is SolrInputDocument content");

		try {
			// 添加一个doc文档
			UpdateResponse response = server.add(doc);
			// commit后才保存到索引库
			fail(server.commit());
			fail(response);
			fail("query time : " + response.getQTime());
			fail("Elapsed Time : " + response.getElapsedTime());
			fail("status : " + response.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查找
	@Test
	public void query1() {
		query("name:solr");
	}

	// 利用SolrJ添加多个Document,即添加文档集合
	@Test
	public void addDocs() {
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", 2);
		doc.addField("name", "Solr Input Documents 1");
		doc.addField("manu", "this is SolrInputDocuments 1 content");
		docs.add(doc);
		doc = new SolrInputDocument();
		doc.addField("id", 3);
		doc.addField("name", "Solr Input Documents 2");
		doc.addField("manu", "this is SolrInputDocuments 3 content");
		docs.add(doc);
		try {
			// add docs
			UpdateResponse response = server.add(docs);
			// commit后才保存到索引库
			fail(server.commit());
			fail(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加bean完成doc添加操作
	@Test
	public void addBean() {
		Index index = new Index();
		index.setId("4");
		index.setName("add bean index");
		index.setManu("index bean manu");
		index.setCat(new String[] { "a1", "b2" });
		try {
			// 添加Index Bean到索引库
			UpdateResponse response = server.addBean(index);
			fail(server.commit());// commit后才保存到索引库
			fail(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查找
	@Test
	public void query2() {
		query("name:index");
	}

	// 添加entity bean 集合到索引库
	@Test
	public void addBeans() {
		Index index = new Index();
		index.setId("6");
		index.setName("add beans index 1");
		index.setManu("index beans manu 1");
		index.setCat(new String[] { "a", "b" });

		List<Index> indexs = new ArrayList<Index>();
		indexs.add(index);

		index = new Index();
		index.setId("5");
		index.setName("add beans index 2");
		index.setManu("index beans manu 2");
		index.setCat(new String[] { "aaa", "bbbb" });
		indexs.add(index);
		try {
			// 添加索引库
			UpdateResponse response = server.addBeans(indexs);
			fail(server.commit());// commit后才保存到索引库
			fail(response);
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询所有索引信息
	@Test
	public void queryAll() {
		ModifiableSolrParams params = new ModifiableSolrParams();
		// 查询关键字 *:* 代表所有属性，所有值，即所有index
		params.set("q", "*:*");
		// 分页，start=0就是从0开始， row=5当前返回5条记录， 第二页就是变化start这个值为5就可以了
		params.set("start", 0);
		params.set("rows", Integer.MAX_VALUE);
		// 排序，，如果按照id排序，，那么将score desc 改成 id desc(or asc)
		params.set("sort", "score desc");
		// 返回信息 * 为全部 这里是全部加上score ， 如果不加下面就不能使用score
		params.set("fl", "name");  //这样的话就是只返回name
		try {
			QueryResponse response = server.query(params);
			SolrDocumentList list = response.getResults();
			for (int i = 0; i < list.size(); i++) {
				fail(list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	// 查找所有
	@Test
	public void testqueryAll() {
		queryAll();
	}

	// 文档查询 基本用法测试
	@Test
	public void queryCase() {
		// AND 并且
		SolrQuery params = new SolrQuery("name:crxy");
		params.setHighlight(true);
		// 标记，高亮关键字前缀
		params.setHighlightSimplePre("<font color='red'>");
		// 后缀
		params.setHighlightSimplePost("</font>");
		params.setHighlightSnippets(1);// 结果分片数，默认为1
		params.setHighlightFragsize(1000);// 每个分片的最大长度，默认为100
		try {
			QueryResponse response = server.query(params);
			//输出查询结果集
	        SolrDocumentList list = response.getResults();
	        fail("query result nums: " + list.getNumFound());
	        for (int i = 0; i < list.size(); i++) {
	            fail(list.get(i));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
