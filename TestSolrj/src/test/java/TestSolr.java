import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.junit.Test;

/**
@author junmeng.xu
@date  2016年3月14日下午1:44:14
 */
public class TestSolr {

	//这样的话就可以把collection1这个索引库里面的数据打印出来了，因为查询条件是*：*
	@Test
	public void test1() throws Exception{
		
		String baseURL = "http://192.168.1.43:18282/solr/collection";
		
		CommonsHttpSolrServer solrServer = new CommonsHttpSolrServer(baseURL);
		
		SolrQuery params = new SolrQuery();
		params.set("q", "*:*");
		QueryResponse query = solrServer.query(params);
		SolrDocumentList results = query.getResults();
		long numFound = results.getNumFound();
		System.out.println("总数 : " + numFound);
		for (SolrDocument solrDocument : results) {
			Collection<String> fieldNames = solrDocument.getFieldNames();
			for (String field : fieldNames) {
				System.out.println(field + ":" + solrDocument.get(field));
			}
			System.out.println("===========================");
		}
	}
	
	@Test
	public void test2() throws Exception, Exception{
		String baseURL = "http://192.168.1.43:18282/solr/collection";
		CommonsHttpSolrServer solrServer = new CommonsHttpSolrServer(baseURL);
		
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", "7");
		doc.setField("name", "junmeng.xu7");
		solrServer.add(doc);
		solrServer.commit();
	}
	
	@Test
	public void test4() throws Exception, Exception{
		String baseURL = "http://192.168.0.123:18282/solr/collection";
		CommonsHttpSolrServer solrServer = new CommonsHttpSolrServer(baseURL);
		Person person = new Person();
		person.setId("111");
		person.setName("crxy111");
		solrServer.addBean(person);
		solrServer.commit();
	}
	//删除索引delete
	@Test
	public void test5() throws SolrServerException, IOException{
		String baseURL = "http://192.168.0.123:18282/solr/collection";
		CommonsHttpSolrServer solrServer = new CommonsHttpSolrServer(baseURL);
		solrServer.deleteById("1");
		solrServer.commit();
	}
	
	
	//查询索引
	@Test
	public void queryAll(){
		ModifiableSolrParams params = new ModifiableSolrParams();
		//查询关键词， *:* 代表所有属性，所有值,即所有index
		params.set("q", "*");
		params.set("start", 0);
		params.set("rows", Integer.MAX_VALUE);
		params.set("sort", "id desc");
		params.set("fl", "*,id");
		try {
			String baseURL = "http://192.168.1.43:18282/solr/collection";
			
			CommonsHttpSolrServer solrServer = new CommonsHttpSolrServer(baseURL);
			QueryResponse response = solrServer.query(params);
			SolrDocumentList results = response.getResults();
			for (SolrDocument solrDocument : results) {
				Collection<String> fieldNames = solrDocument.getFieldNames();
				for (String field : fieldNames) {
					System.out.println(field + ":" + solrDocument.get(field));
				}
				System.out.println("===========================");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//查询某个索引
	@Test
	public void findOne(){
		ModifiableSolrParams params = new ModifiableSolrParams();
		//查询关键词， *:* 代表所有属性，所有值,即所有index
		params.set("q", "junmeng.xu7");
		try {
			String baseURL = "http://192.168.1.43:18282/solr/collection";
			
			CommonsHttpSolrServer solrServer = new CommonsHttpSolrServer(baseURL);
			QueryResponse response = solrServer.query(params);
			SolrDocumentList results = response.getResults();
			for (SolrDocument solrDocument : results) {
				Collection<String> fieldNames = solrDocument.getFieldNames();
				for (String field : fieldNames) {
					System.out.println(field + ":" + solrDocument.get(field));
				}
				System.out.println("===========================");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//查询例子
	@Test
	public void findTest() throws Exception{
		String baseURL = "http://192.168.1.43:18282/solr/collection";
		CommonsHttpSolrServer solrServer = new CommonsHttpSolrServer(baseURL);
		Collection<SolrInputDocument> docList = new ArrayList<SolrInputDocument>();
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", "15221450286");
		doc.setField("name", "徐军猛");
		doc.setField("sex", "男");
		doc.setField("xueli", "本科");
		doc.setField("aihao", "羽毛球");
		SolrInputDocument doc2 = new SolrInputDocument();
		doc2.setField("id", "13816605436");
		doc2.setField("name", "夏菲菲");
		doc2.setField("xueli", "本科");
		doc2.setField("aihao", "肚皮舞");
		docList.add(doc);
		docList.add(doc2);
		solrServer.add(docList);
		solrServer.commit();
	}
	
}
