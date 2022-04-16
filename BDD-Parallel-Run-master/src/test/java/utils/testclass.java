package utils;

import java.util.List;

import org.junit.Test;

public class testclass {

	@Test
	public void givenPythonScript_whenPythonProcessInvoked_thenSuccess() throws Exception {
	    ProcessBuilder processBuilder = new ProcessBuilder("python", resolvePythonScriptPath("hello.py"));
	    processBuilder.redirectErrorStream(true);

	    Process process = processBuilder.start();
	    List<String> results = readProcessOutput(process.getInputStream());

	    assertThat("Results should not be empty", results, is(not(empty())));
	    assertThat("Results should contain output of script: ", results, hasItem(
	      containsString("Hello Baeldung Readers!!")));

	    int exitCode = process.waitFor();
	    assertEquals("No errors should be detected", 0, exitCode);
	}
}
