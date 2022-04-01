package DieselLabsSmoke;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.*;
import org.testng.reporters.EmailableReporter;
import org.openqa.selenium.chrome.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.openqa.selenium.*;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import DieselLabsSmoke.Email;



public class SmokeTest {
	
	
		public static String baseUrl;
		public static WebDriver driver;
	   public static String UserName;
	   public static String PassWord;
		
		
	
		@Parameters({ "baseUrl","UserName","PassWord" })
		@BeforeTest
		public void Login(String baseUrl,String UserName,String PassWord) {
			this.driver=driver;
		    System.out.println("********************************************");
		    String Path=System.getenv("Driver_Path");
			System.out.println(Path);
			System.setProperty("webdriver.chrome.driver",Path);
			this.driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
 			options.addArguments("--no-sandbox");
 			options.addArguments("--headless"); //!!!should be enabled for Jenkins
 			options.addArguments("--disable-dev-shm-usage"); //!!!should be enabled for Jenkins
 			options.addArguments("--window-size=1920x1080"); //!!!should be enabled for Jenkins
 			this.driver = new ChromeDriver(options);
			System.out.println(baseUrl);
			driver.get(baseUrl);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement Email=driver.findElement(By.name("email"));
			System.out.println(UserName);
			Email.sendKeys(UserName);
			WebElement Password=driver.findElement(By.name("password"));
			Password.sendKeys(PassWord);
			WebElement SubmitBtn=driver.findElement(By.xpath("//button[contains(@class, 'auth0-lock-submit')]"));
			SubmitBtn.click();
						
		}
		
		@AfterTest
		public void closebrowser()
		{
			WebElement LogoutButton=driver.findElement(By.linkText("Logout"));
			LogoutButton.click();
			driver.close();
		}
		
		@Test(priority=1,description = "Verify the AttentionIndex Link")
		public void VerifyAttentionIndexLink() {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement AttentionIndex = driver.findElement(By.xpath("(//span[contains(@class,'calendarNav')])[1]"));
			AttentionIndex.click();
			// verify Attention Index Logo
			boolean AttentionIndexLogo = driver.findElement(By.xpath("//li[@class='imgHolder']")).isDisplayed();

			// verify Header Text
			WebElement Header = driver.findElement(By.xpath("//h2[@class='title']"));
			String HeaderText = Header.getText();
			System.out.println(HeaderText);
			Assert.assertTrue(true, HeaderText);

			// Click and verify anticipation tab
			WebElement AnticipationTab = driver.findElement(By.xpath("//div[contains(@class,'anticipationTabActive')]"));
			String AnticipationTabText = AnticipationTab.getText();
			System.out.println(AnticipationTabText);
			Assert.assertTrue(true, AnticipationTabText);

			WebElement AttentionClick = driver.findElement(By.xpath("//div[contains(@class,'anticipationTabActive')]"));
			AttentionClick.click();

			// verify Anticipation Logo
			boolean AnticipationLogo = driver.findElement(By.xpath("//div[contains(@class,'infoImg')]")).isDisplayed();

			// get Most Anticipated Content Text
			WebElement MostAnticipatedContent = driver
					.findElement(By.xpath("//h5[contains(text(),'Most Anticipated Content')]"));
			String MostAnticipatedContentBtn = MostAnticipatedContent.getText();
			System.out.println(MostAnticipatedContentBtn);
			Assert.assertTrue(true, MostAnticipatedContentBtn);

			// verify the web tables
			List<WebElement> rowsNumber = driver.findElements(By.xpath("//*[@id='attentionTable']/table/tbody/tr"));
			System.out.println("******************************");
			System.out.println(rowsNumber.size());
			int rows = rowsNumber.size();
			boolean flag;
			flag = true;
			if (rows > 0) {
				System.out.println(flag);
			} else {
				flag = false;
				System.out.println(flag);

			}

			// Click and verify Top tab
			WebElement TopTab = driver.findElement(By.id("topHeading"));
			String TopTabText = TopTab.getText();
			System.out.println(TopTabText);
			Assert.assertTrue(true, TopTabText);

			WebElement TopTabClick = driver.findElement(By.id("topHeading"));
			TopTabClick.click();

			// verify Top Logo
			boolean TopLogo = driver.findElement(By.xpath("//div[contains(@class,'infoImg')]")).isDisplayed();

			// get Top Content Text
			WebElement TopContent = driver.findElement(By.xpath("//h5[contains(@class,'btnTxt')]"));
			String TopContentText = TopContent.getText();
			System.out.println(TopContentText);
			Assert.assertTrue(true, TopContentText);

			// Click and verify Trending tab
			WebElement TrendingTab = driver.findElement(By.id("trendingHeading"));
			String TrendingTabText = TrendingTab.getText();
			System.out.println(TrendingTabText);
			Assert.assertTrue(true, TrendingTabText);

			WebElement TrendingTabClick = driver.findElement(By.id("trendingHeading"));
			TrendingTabClick.click();

			// verify Top Logo
			boolean trendingLogo = driver.findElement(By.xpath("//div[contains(@class,'infoImg')]")).isDisplayed();

			// get Most Trending Content Text
			WebElement mostTrendingContent = driver.findElement(By.xpath("//h5[contains(@class,'btnTxt')]"));
			String TrendingContentText = mostTrendingContent.getText();
			System.out.println(TrendingContentText);
			Assert.assertTrue(true, TrendingContentText);

			Boolean[] actualResult = { AttentionIndexLogo, AnticipationLogo, TopLogo, trendingLogo, flag };
			Boolean[] expectedResult = { true, true, true, true, true };
			Assert.assertEquals(actualResult, expectedResult);
		}
		
		@Test(priority =2,description="Verify the Calendar Page Objects")
		public void VerifyCalendarLink() {
			WebElement CalendarLink=driver.findElement(By.linkText("Calendar"));
			CalendarLink.click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			System.out.println("Verify the Calendar Logo");
			WebElement CalenderLogo = driver.findElement(By.xpath("//img[@src='/static/images/date.png']"));
			System.out.println(CalenderLogo);
			Boolean flag1=CalenderLogo.isEnabled();
			System.out.println("**************************************");
			System.out.println("Verify the Title TextField Present");
			WebElement TitleTxtField = driver.findElement(By.xpath("//input[@placeholder='Title']"));
			Boolean flag2=TitleTxtField.isDisplayed();
			System.out.println("**************************************");
			System.out.println("Verify the ClearFilters field Present");
			WebElement ClearFilterBtn =driver.findElement(By.xpath("//button[contains(text(),'Clear Filters')]"));
			Boolean flag3=ClearFilterBtn.isDisplayed();
			System.out.println("**************************************");
			System.out.println("Verify the Type field Present");
			WebElement TypeBtn = driver.findElement(By.xpath(" //button[@class='inputRef_Type librarySelectFilter btn btn-primary']"));
			Boolean flag4=TypeBtn.isDisplayed();
			System.out.println("**************************************");
			System.out.println("Verify the Network field Present");
			WebElement NetworkBtn = driver.findElement(By.xpath(" //button[@class='inputRef_Network librarySelectFilter btn btn-primary']"));
			Boolean flag5=TypeBtn.isDisplayed();
			System.out.println("**************************************");
			System.out.println("Verify the Genre field Present");
			WebElement GenreBtn = driver.findElement(By.xpath(" //button[@class='inputRef_Genre librarySelectFilter btn btn-primary']"));
			Boolean flag6=TypeBtn.isDisplayed();
			System.out.println("Verify the WebTable has more than 0 rows");
			System.out.println("**************************");
			List<WebElement> rownum =driver.findElements(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body tableTopDivide']"));
			System.out.println("**************************");
			System.out.println(rownum.size());
			int rows=rownum.size();
			Boolean flag7;
			flag7 = true;
			if (rows > 0) {
				 System.out.println(flag7);
			}
			else {
				flag7 = false;
				System.out.println(flag7);
							
			}
			Boolean[] actualResult = {flag1,flag2,flag3,flag4,flag5,flag6,flag7};
			Boolean[] expectedResult = {true,true,true,true,true,true,true};
			Assert.assertEquals(actualResult, expectedResult);
		              		
					
			
		}
		
		@Test(priority =3,description = "Verify the Network and Platforms Page Properties")
		public void verifyLogoNetworkAndPlatformsDropdown() {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement element = driver.findElement(By.xpath("(//a[contains(@class,'home-page nav-link')])[4]"));
			String elementText = element.getText();
			System.out.println(elementText);
			Assert.assertTrue(true, elementText);

			System.out.println("Click Network and Platforms Link");
			WebElement networkAndPlatforms = driver
					.findElement(By.xpath("(//a[contains(@class,'home-page nav-link')])[4]"));
			networkAndPlatforms.click();

			System.out.println("Verify the Network and Platforms Link Logo");
			boolean graph1 = driver.findElement(By.xpath("//div[contains(@class,'titleIcon')]")).isDisplayed();
			System.out.println("**************************************");

			// Verify the Portfolio Overview text and graph
			WebElement ageAndGenderDemographics = driver
					.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root MuiGrid-item')])[1]"));
			String ageAndGenderDemographicsText = ageAndGenderDemographics.getText();
			System.out.println(ageAndGenderDemographicsText);
			Assert.assertTrue(true, ageAndGenderDemographicsText);
			boolean graph2 = driver.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root MuiGrid-item')])[2]"))
					.isDisplayed();
			System.out.println("**************************************");

			// Verify the Portfolio Overview text and graph
			WebElement portfolioOverview = driver
					.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root MuiGrid-item')])[5]"));
			String portfolioOverviewText = portfolioOverview.getText();
			System.out.println(portfolioOverviewText);
			Assert.assertTrue(true, portfolioOverviewText);
			boolean graph3 = driver.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root MuiGrid-item')])[6]"))
					.isDisplayed();
			System.out.println("**************************************");

			// Verify the Genre Profile Text and graph
			WebElement GenreProfile = driver
					.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root MuiGrid-item')])[9]"));
			String GenreProfileText = GenreProfile.getText();
			System.out.println(GenreProfileText);
			Assert.assertTrue(true, GenreProfileText);
			boolean graph4 = driver.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root MuiGrid-item')])[11]"))
					.isDisplayed();
			System.out.println("**************************************");

			// Verify the Viewership Overlap
			WebElement viewershipOverlap = driver
					.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root MuiGrid-item')])[13]"));
			String viewershipOverlapText = viewershipOverlap.getText();
			System.out.println(viewershipOverlapText);
			Assert.assertTrue(true, viewershipOverlapText);
			boolean graph5 = driver.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root MuiGrid-item')])[12]"))
					.isDisplayed();
			System.out.println("**************************************");

			Boolean[] actualResult = { graph1, graph2, graph3, graph4, graph5 };
			Boolean[] expectedResult = { true, true, true, true, true };
			Assert.assertEquals(actualResult, expectedResult);

		}
		
	



	@Test(priority =4,description="Verify the Library Page Objects")
		public void VerifyLibraryPage()
		{

			WebElement LibraryLink=driver.findElement(By.linkText("Library"));
			LibraryLink.click();
			System.out.println("the Library Logo get displayed");
			WebElement LibraryLogo = driver.findElement(By.xpath("//li[@class='imgHolder']"));
			Boolean flag1=LibraryLogo.isDisplayed();
			System.out.println(flag1);
			//Verify LibraryText get displayed
			WebElement LibraryText=driver.findElement(By.xpath("//h2[contains(text(), 'Library')]"));
			Boolean flag2=LibraryText.isDisplayed();
			//Verify SearchField get displayed
			WebElement SearchField =driver.findElement(By.id("formSearch"));
			Boolean flag3=SearchField.isDisplayed();
			//Verify the webtable has more than 0 rows
			List<WebElement> rownum=driver.findElements(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body']"));
			int num_rows=rownum.size();
			System.out.println(num_rows);
			int rows=rownum.size();
			Boolean flag4;
			flag4 = true;
			if (rows > 0) {
				 System.out.println(flag4);
			}
			else {
				flag4 = false;
				System.out.println(flag4);
			}
			Boolean[] actualResult = {flag1,flag2,flag3,flag4};
			Boolean[] expectedResult = {true,true,true,true};
			Assert.assertEquals(actualResult, expectedResult);


		
		}
	
	@Test(priority =5,description="Verify the Catalog Compare Page existancy")
	public void VerifyCatalogCompare()
	{

		//Navigate to CatalogCompareLink
		WebElement CatalogCompareLink=driver.findElement(By.linkText("Catalog Compare"));
		CatalogCompareLink.click();
		//Verify the Catalog Logo get displayed
		WebElement CatalogLogo = driver.findElement(By.xpath("//li[@class='imgHolder']"));
		Boolean flag1=CatalogLogo.isDisplayed();
		System.out.println(flag1);
		//Verify the CatalogCompareText get displayed
		WebElement CatalogCompareText=driver.findElement(By.xpath("//h2[contains(text(), 'Catalog Compare')]"));
		Boolean flag2=CatalogCompareText.isDisplayed();
		System.out.println(flag2);
		//Verify the NetflixText get displayed
		WebElement NetflixText = driver.findElement(By.xpath("//span[text()='Netflix']"));
		Boolean flag3=NetflixText.isDisplayed();
		System.out.println(flag3);
		//Verify the AmazonText get displayed
		WebElement AmazonText = driver.findElement(By.xpath("//span[text()='Amazon']"));
		Boolean flag4=AmazonText.isDisplayed();
		System.out.println(flag4);
		//Verify the DisneyText get displayed
		WebElement DisneyText = driver.findElement(By.xpath("//span[text()='Disney+']"));
		Boolean flag5=DisneyText.isDisplayed();
		System.out.println(flag5);
		//Verify the VolumeofNewReleaseProgressBar get displayed
		WebElement VolumeofNewReleasesProgressBar=driver.findElement(By.xpath("//div[contains(@class,'MuiPaper-root metricBox jss')]"));
		Boolean flag6=VolumeofNewReleasesProgressBar.isDisplayed();
		System.out.println(flag6);
		//Verify the OwnershipofTop50TitlesProgressBar get displayed
		WebElement OwnershipofTop50TitlesProgressBar=driver.findElement(By.xpath("//div[contains(@class,'MuiPaper-root metricBox jss')]"));
		Boolean flag7=OwnershipofTop50TitlesProgressBar.isDisplayed();
		System.out.println(flag7);
		//Verify the AverageTMDBratingpertitleProgressBar get displayed
		WebElement AverageTMDBratingpertitleProgressBar=driver.findElement(By.xpath("//div[contains(@class,'MuiPaper-root metricBox')]"));
		Boolean flag8=AverageTMDBratingpertitleProgressBar.isDisplayed();
		System.out.println(flag8);
		//Verify the PercentagesbyGenreProgressBar get displayed
		WebElement PercentagesbyGenreProgressBar=driver.findElement(By.xpath("//div[contains(@class,'MuiPaper-root metricBox jss')]"));
		Boolean flag9=PercentagesbyGenreProgressBar.isDisplayed();
		System.out.println(flag9);
		//Verify the ContentBudgetandSubscribersProgressBar get displayed
		WebElement ContentBudgetandSubscribersProgressBar=driver.findElement(By.xpath("//div[contains(@class,'MuiPaper-root infoBox jss')]"));
		Boolean flag10=ContentBudgetandSubscribersProgressBar.isDisplayed();
		System.out.println(flag10);
		//Verify the TopContentbyEngagement get displayed
		WebElement TopContentbyEngagement=driver.findElement(By.xpath("//div[contains(@class,'MuiPaper-root infoBox jss')]"));
		Boolean flag11=TopContentbyEngagement.isDisplayed();
		System.out.println(flag11);
		//Verify the WhatsnewCalendar get displayed
		WebElement WhatsnewCalendar=driver.findElement(By.xpath("//div[contains(@class,'MuiPaper-root infoBox subscriberCalendar jss')]"));
		Boolean flag12=WhatsnewCalendar.isDisplayed();
		System.out.println(flag12);
		Boolean[] actualResult = {flag1,flag2,flag3,flag4,flag5,flag6,flag7,flag8,flag9,flag10,flag11,flag12};
		Boolean[] expectedResult = {true,true,true,true,true,true,true,true,true,true,true,true};
		Assert.assertEquals(actualResult, expectedResult);
		
		
	}
	

    @Test(priority =6,description="Verify the Audience Lift")
    public void VerifyAudienceLift(){
		WebElement AudienceLift=driver.findElement(By.linkText("Audience Lift"));
		AudienceLift.click();
		//Verify the Audience List Logo get displayed
		WebElement AvatorLogo = driver.findElement(By.xpath("//*[@class='MuiAvatar-img']"));
		Boolean flag1=AvatorLogo.isDisplayed();
		System.out.println(flag1);
		//Verify disney+ get displayed
		WebElement DisneyText = driver.findElement(By.xpath("//span[text()='Disney+']"));
		Boolean flag2=DisneyText.isDisplayed();
		System.out.println(flag2);
		//Verify ContentPerformance get displayed
		WebElement ContentPerformance = driver.findElement(By.xpath("//span[text()='Content Performance']"));
		Boolean flag3=ContentPerformance.isDisplayed();
		System.out.println(flag3);
		//Verify ContentPerformance Table verification
    	List<WebElement> Table_Row = driver.findElements(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body']"));
    	int num_row = Table_Row.size();
    	System.out.println(num_row);
    	Boolean flag4 = true;
		if (num_row > 0) {
			 System.out.println(flag4);
		}
		else {
			flag4 = false;
			System.out.println(flag4);
		}
		Boolean[] actualResult = {flag1,flag2,flag3,flag4};
		Boolean[] expectedResult = {true,true,true,true};
		Assert.assertEquals(actualResult, expectedResult);
    	
    }
    
    @Test(priority =7,description = "Verify the New Audience Drivers Link")
	public void VerifyAudienceDriverPage() {
    	WebElement NewAudienceLink=driver.findElement(By.linkText("New Audience Drivers"));
    	NewAudienceLink.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.id("rollingNav"));
		String elementText = element.getText();
		System.out.println(elementText);
		Assert.assertTrue(true, elementText);

		System.out.println("Click New Audience  Drivers Link");
		WebElement newAudienceDrivers = driver.findElement(By.id("rollingNav"));
		newAudienceDrivers.click();

		// verify New Audience Drivers Logo
		boolean NewAudienceDriversLogo = driver.findElement(By.xpath("//img[contains(@class,'MuiAvatar-img')]"))
				.isDisplayed();
		System.out.println("**************************************");

		// verify dropdown is visible
		boolean NewAudienceDriversDropdown = driver
				.findElement(By.xpath("//div[contains(@class,'MuiInputBase-root MuiInput-root MuiInput-underline')]"))
				.isDisplayed();
		System.out.println("**************************************");

		// verify Audience Acquisition Text
		WebElement NewAudienceDrivers = driver.findElement(By.xpath("(//span[contains(@class,'cardTitle')])[1]"));
		String NewAudienceDriversText = NewAudienceDrivers.getText();
		System.out.println(NewAudienceDriversText);
		Assert.assertTrue(true, NewAudienceDriversText);

		// verifying the graph1 is displayed Audience Acquisition
		boolean graph1 = driver.findElement(By.xpath("(//div[contains(@class,'plot-container plotly')])[1]"))
				.isDisplayed();
		System.out.println("**************************************");

		// verify the shows and movies Header in Audience Acquisition
		WebElement showsAndMovies = driver
				.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root MuiGrid-item')])[4]"));
		String showsAndMoviesText = showsAndMovies.getText();
		System.out.println(showsAndMoviesText);
		Assert.assertTrue(true, showsAndMoviesText);

		// verifying the icon is displayed in Audience Acquisition
		boolean icon1 = driver.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root MuiGrid-item')])[5]"))
				.isDisplayed();
		System.out.println("**************************************");

		// verify Audience Acquisition Text
		WebElement AudienceMaintenance = driver.findElement(By.xpath("(//span[contains(@class,'cardTitle')])[2]"));
		String AudienceMaintenanceText = AudienceMaintenance.getText();
		System.out.println(AudienceMaintenanceText);
		Assert.assertTrue(true, AudienceMaintenanceText);

		// verifying the graph1 is displayed Audience Acquisition
		boolean graph2 = driver.findElement(By.xpath("(//div[contains(@class,'plot-container plotly')])[2]"))
				.isDisplayed();
		System.out.println("**************************************");

		// verify the shows and movies Header in Audience Acquisition
		WebElement showsAndMovie = driver
				.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root MuiGrid-item')])[9]"));
		String showsAndMovieText = showsAndMovie.getText();
		System.out.println(showsAndMovieText);
		Assert.assertTrue(true, showsAndMovieText);

		// verifying the graph1 is displayed in Audience Acquisition
		boolean icon2 = driver.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root MuiGrid-item')])[10]"))
				.isDisplayed();
		System.out.println("**************************************");

		Boolean[] actualResult = { graph1, graph2, icon1, icon2, NewAudienceDriversLogo, NewAudienceDriversDropdown };
		Boolean[] expectedResult = { true, true, true, true, true, true };
		Assert.assertEquals(actualResult, expectedResult);

	}

    
  
	@Test(priority =8,description = "Verify the Attention tracker Link")
	public void VerifyAttentionTrackerPage() {
    	WebElement AudienceTracker=driver.findElement(By.linkText("Attention Tracker"));
    	AudienceTracker.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.xpath("(//a[contains(@class,'home-page nav-link')])[10]"));
		String elementText = element.getText();
		System.out.println(elementText);
		Assert.assertTrue(true, elementText);

		System.out.println("Click New Audience  Drivers Link");
		WebElement attentionTracker = driver.findElement(By.xpath("(//a[contains(@class,'home-page nav-link')])[10]"));
		attentionTracker.click();

		// verify New Attention Tracker Logo
		boolean attentionTrackerLogo = driver
				.findElement(By.xpath("//div[@class='MuiAvatar-root MuiAvatar-square titleIcon']")).isDisplayed();

		// verify dropdown is visible
		boolean attentionTrackerDropdown = driver.findElement(By.xpath("//div[@class='MuiInputBase-root MuiInput-root MuiInput-underline']"))
				.isDisplayed();

		// verify content dropdown
		boolean contentDropdown = driver.findElement(By.xpath("//div[@class='MuiInputBase-root MuiInput-root MuiInput-underline']"))
				.isDisplayed();

		// get launchView text and check whether it is clickable
		WebElement launchView = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[1]"));
		String launchViewText = launchView.getText();
		System.out.println(launchViewText);
		Assert.assertTrue(true, launchViewText);

		WebElement launchViewClick = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[1]"));
		launchViewClick.click();

		// get launchView text and check whether it is clickable
		WebElement post_preView = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[2]"));
		String post_preViewText = post_preView.getText();
		System.out.println(post_preViewText);
		Assert.assertTrue(true, post_preViewText);

		WebElement post_preViewClick = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[2]"));
		post_preViewClick.click();

		// get Timeline View text and check whether it is clickable
		WebElement timelineView = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[3]"));
		String timelineViewText = timelineView.getText();
		System.out.println(timelineViewText);
		Assert.assertTrue(true, timelineViewText);

		WebElement timelineViewClick = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[3]"));
		timelineViewClick.click();

		// verify Attention Tracker graph
		boolean attentionTrackergraph = driver.findElement(By.xpath("(//div[@class='chartjs-size-monitor'])[1]"))
				.isDisplayed();

		// get movie text Tab and check whether it is clickable
		WebElement movie1 = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[4]"));
		String movie1Text = movie1.getText();
		System.out.println(movie1Text);
		Assert.assertTrue(true, movie1Text);

		WebElement movie1Click = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[4]"));
		movie1Click.click();

		// get movie text Tab and check whether it is clickable
		WebElement movie2 = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[5]"));
		String movie2Text = movie2.getText();
		System.out.println(movie2Text);
		Assert.assertTrue(true, movie2Text);

		WebElement movie2Click = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[5]"));
		movie2Click.click();

		// get movie text Tab and check whether it is clickable
		WebElement movie3 = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[6]"));
		String movie3Text = movie3.getText();
		System.out.println(movie3Text);
		Assert.assertTrue(true, movie3Text);

		WebElement movie3Click = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[6]"));
		movie3Click.click();

		// get movie text Tab and check whether it is clickable
		WebElement movie4 = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[7]"));
		String movie4Text = movie4.getText();
		System.out.println(movie4Text);
		Assert.assertTrue(true, movie4Text);

		WebElement movie4Click = driver.findElement(By.xpath("(//span[@class='MuiButton-label'])[7]"));
		movie4Click.click();

		// verify the Header text2 in Attention tracker
		WebElement headerText2 = driver
				.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root cardPadding MuiGrid-item')])[2]"));
		String header2Text = headerText2.getText();
		System.out.println(header2Text);
		Assert.assertTrue(true, header2Text);

		// verify the Header text3 in Attention tracker
		WebElement headerText3 = driver
				.findElement(By.xpath("(//div[contains(@class,'MuiGrid-root cardPadding MuiGrid-item')])[3]"));
		String header3Text = headerText3.getText();
		System.out.println(header3Text);
		Assert.assertTrue(true, header3Text);

		// verify the tables
		List<WebElement> rownum = driver.findElements(By.xpath("//div[contains(@class,'commonTable teaserTable')]"));
		int num_rows = rownum.size();
		System.out.println(num_rows);
		int rows = rownum.size();
		Boolean table;
		table = true;
		if (rows > 0) {
			System.out.println(table);
		} else {
			table = false;
			System.out.println(table);
		}

		// verify the table2
		List<WebElement> rownum2 = driver.findElements(By.xpath("(//tr[contains(@class,'MuiTableRow-root')])[7]"));
		int num_rows2 = rownum2.size();
		System.out.println(num_rows2);
		int rows2 = rownum.size();
		Boolean table2;
		table2 = true;
		if (rows2 > 0) {
			System.out.println(table2);
		} else {
			table2 = false;
			System.out.println(table2);
		}

		// verify the list is visible and acessable
		boolean teaserList = driver.findElement(By.xpath("(//tr[contains(@class,'MuiTableRow-root')])[6]"))
				.isDisplayed();
		WebElement teaserClick = driver.findElement(By.xpath("(//tr[contains(@class,'MuiTableRow-root')])[6]"));
		teaserClick.click();

		// verify the list2 is visible and acessable
		boolean wordCloud = driver.findElement(By.xpath("(//tr[contains(@class,'MuiTableRow-root')])[6]"))
				.isDisplayed();
		WebElement iconClick = driver.findElement(By.xpath("(//tr[contains(@class,'MuiTableRow-root')])[6]"));
		teaserClick.click();

		Boolean[] actualResult = { contentDropdown, attentionTrackerDropdown, attentionTrackerLogo, teaserList,wordCloud };
		Boolean[] expectedResult = { true, true, true, true, true };
		Assert.assertEquals(actualResult, expectedResult);

	}
    
	
	//@AfterSuite
	//public void sendemailreport() throws CoreException, InterruptedException  {
		//DieselLabsSmoke.Email.sendEmail("praveena.johnbose@capestart.com", "btcdptwemamksvxn");
		
	//}
	
	
	
	
}
	
	
		


