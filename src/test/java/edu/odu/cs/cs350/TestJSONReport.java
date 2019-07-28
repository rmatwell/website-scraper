package edu.odu.cs.cs350;


public class TestJSONReport {
	
	JSONReport defaultConstruct;
	JSONReport testConstructor;
	
	private String analysisTime = "20190725-103257-summary.json";
	
	@Before
	public void setUp() {
		
		defaultConstruct = new JSONReport();
		testConstructor = new JSONReport();
		
	}
	
	@Test
	public void testJSONReport() {
		
		assertThat(defaultConstruct.getFileName(), is(""));
		//still working on, will continue tonight
		
	}
	
}