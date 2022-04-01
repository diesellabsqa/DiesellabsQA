package DieselLabsSmoke;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;
import java.io.File;
import java.util.List;

//import org.rationale.emotions.testng.ListenerDemo
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;



	
	


	@Listeners({ DieselLabsSmoke.Postsetup.class })
	
	    public class Postsetup implements IReporter {
			
	        

			@Override
	        public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, final String outputDirectory) {
	            Runtime.getRuntime().addShutdownHook(new Thread() {
	                private File file = new File(System.getenv("Email_Report"));

	                @Override
	                public synchronized void start() {
	                    boolean flag = false;
	                    for (int i = 0; i < 10; i++) {
	                        if (file.exists()) {
	                            // Include your code here
	                            System.out.println("Found the file @ " + file.getAbsolutePath());
	                            String filename=file.getAbsolutePath();
	                            DieselLabsSmoke.Email.sendEmail("praveena.johnbose@capestart.com", "btcdptwemamksvxn",filename);
	                            flag = true;
	                            break;
	                        }
	                        try {
	                            Thread.sleep(1 * 1000 /* Sleep for a second */);
	                            System.out.println("File is still not found.Slept for a second!");
	                        } catch (InterruptedException e) {

	                        }
	                    };
	            if (! flag) {
	                        System.out.println("Didn't find the file yet");
	                    }

	                }
	            });
	        }
	}
