/**
 * 
 */
package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
/**
 * @author Oded Nissan
 *
 */
@Path("/products")
public class ProductRestService {
	
	
	@GET
	@Path("/findAll")
	@Produces ("application/json" )
	public Product[] findAll()
	{
		Product list[] = new Product[5];
		for(int i=0; i < 5; ++i) {
			list[i] = new Product(i,"Product-" + i);
		}
		return(list);
	}
	
	@GET
	@Path("/findAllXml")
	@Produces  ("application/xml" )
	public Product[] findAllXml()
	{
		Product list[] = new Product[5];
		for(int i=0; i < 5; ++i) {
			list[i] = new Product(i,"Product-" + i);
		}
		return(list);
	}
	
	
	@GET
	@Path("/test")
	public String test()
	{
		return("test string");
	}
	

}
