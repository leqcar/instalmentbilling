package com.leqcar.instalmentbilling.domain.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.model.policy.PolicyRepository;
import com.leqcar.instalmentbilling.domain.model.product.Product;
import com.leqcar.instalmentbilling.domain.model.product.ProductId;
import com.leqcar.instalmentbilling.domain.model.product.ProductRepository;
import com.leqcar.instalmentbilling.domain.model.quote.QuoteId;

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {PolicyRetrievalService.class})*/
public class PolicyRetrievalServiceTest {

	private PolicyRepository policyRepository;

	private ProductRepository productRepository;

	@Before
	public void setUp() {
		policyRepository = mock(PolicyRepository.class);
		productRepository = mock(ProductRepository.class);
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testPolicyRetrievalReturnAPolicy() {
		
		Policy aPolicy = new Policy(LocalDate.now()
				, LocalDate.now()
				, LocalDate.now() 
				, new QuoteId("1234", "1")
				, new Product(new ProductId("XYZ")
						, LocalDate.now()
						, new QuoteId("1234", "1")));	
		PolicyRetrievalService service = new PolicyRetrievalService(
				new ProductRetrievalService(productRepository)
				, policyRepository);
		
		assertNotNull(service.retrievePolicyInformation(aPolicy));
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void testPolicyRetrievalReturnNull() {
		
		Policy aPolicy = new Policy(LocalDate.now()
				, LocalDate.now()
				, LocalDate.now() 
				, new QuoteId("1234", "1")
				, new Product(new ProductId("XYZ")
						, LocalDate.now()
						, new QuoteId("1234", "1")));		
		PolicyRetrievalService service = new PolicyRetrievalService(
				new ProductRetrievalService(productRepository)
				, policyRepository);
		
		service.retrievePolicyInformation(aPolicy);
		
	}
	
}
