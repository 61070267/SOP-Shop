package SOP_LAMP.EShop;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableSwagger2
public class Store 
{
	ArrayList<My_address> my_adrress = new ArrayList<My_address>();
	ArrayList<Shop> shop = new ArrayList<Shop>();
	ArrayList<Sales> sales = new ArrayList<Sales>();
	public Store() {
		
	}
	public static void main(String[] args) {
		SpringApplication.run(Store.class, args);

	}
	
	//add
	@RequestMapping(value="/adress/add",method=RequestMethod.POST)
	public ResponseEntity<My_address> regisAdress(@RequestBody My_address my_adrres) {
		this.my_adrress.add(my_adrres);
				return new ResponseEntity<My_address>(my_adrres, HttpStatus.OK);
	}
	@RequestMapping(value="/shop/add",method=RequestMethod.POST)
	public ResponseEntity<Shop> regisShop(@RequestBody Shop shop) {
		this.shop.add(shop);
				return new ResponseEntity<Shop>(shop, HttpStatus.OK);
	}
	@RequestMapping(value="/sales/add",method=RequestMethod.POST)
	public ResponseEntity<Sales> regisSales(@RequestBody Sales sales) {
		
		if (this.sales.size() > 0  ) {
			Sales last = this.sales.get(this.sales.size()-1);
			sales.setId(last.getId()+1);
			
		}
		else {
			sales.setId(0);
		}
		
		this.sales.add(sales);
				return new ResponseEntity<Sales>(sales, HttpStatus.OK);
	}
	
	
	//all
	@RequestMapping(value="/shop/getAll", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Shop>> shop(){
		return new ResponseEntity<ArrayList<Shop>>(shop, HttpStatus.OK);
	}
	@RequestMapping(value="/adress/getAll", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<My_address>> my_adrress(){
		return new ResponseEntity<ArrayList<My_address>>(my_adrress, HttpStatus.OK);
	}
	@RequestMapping(value="/sales/getAll", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<Sales>> sales(){
		return new ResponseEntity<ArrayList<Sales>>(sales, HttpStatus.OK);
	}
	
	//get by id
	@RequestMapping(value = "/shop/get/{id}",method=RequestMethod.GET)
	public ResponseEntity<Shop> getShop(@PathVariable("id") int id) {
		Shop store = shop.get(id-1);
		return new ResponseEntity<Shop>(store, HttpStatus.OK);
	}
	@RequestMapping(value = "/sales/get/{id}",method=RequestMethod.GET)
	public ResponseEntity<Sales> getSales(@PathVariable("id") int id) {
		Sales s = sales.get(id-1);
		return new ResponseEntity<Sales>(s, HttpStatus.OK);
	}
	@RequestMapping(value = "/adress/get/{id}",method=RequestMethod.GET)
	public ResponseEntity<My_address> getMy_address(@PathVariable("id") int id) {
		My_address m = my_adrress.get(id-1);
		return new ResponseEntity<My_address>( m, HttpStatus.OK);
		
	}
	
	//delete
	@RequestMapping(value = "/sales/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Sales> deletesales(@PathVariable("id") int id) {
		Sales s = sales.get(id);
		for(int i=0; i< this.my_adrress.size(); i++) {
			if (this.my_adrress.get(i).getSALES_id() == s.getId()) {
				this.my_adrress.remove(i);
			}
		}
		for(int i=0; i< this.shop.size(); i++) {
			if (this.shop.get(i).getSALES_id() == s.getId()) {
				this.shop.remove(i);
			}
		}
		
		this.sales.remove(id);
		
		return new ResponseEntity<Sales>(s, HttpStatus.OK);
	}
	@RequestMapping(value = "/adress/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<My_address> deleteAdress(@PathVariable("id") int id) {
		My_address a = my_adrress.get(id-1);
		this.my_adrress.remove(id-1);
		return new ResponseEntity<My_address>(a, HttpStatus.OK);
	}
	@RequestMapping(value = "/Shop/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Shop> deleteShop(@PathVariable("id") int id) {
		Shop store = shop.remove(id-1);
		this.shop.remove(id-1);
		return new ResponseEntity<Shop>(store, HttpStatus.OK);
		
	}
	
	
	
	
	
}
