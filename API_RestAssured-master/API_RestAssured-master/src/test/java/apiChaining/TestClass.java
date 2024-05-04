package apiChaining;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TestClass extends ReusableMethods {

	@Test
	public void runtestCases() {

		Response response = getAllEmployee();
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(numberOfEmployees, 3);

		response = createNewEmployee("Johny", "Henry", "100000", "johny@gmail.com");
		Assert.assertEquals(response.statusCode(), 201);
		Assert.assertEquals(employeeName, "Johny");
		response = getAllEmployee();
		numberOfEmployees = response.jsonPath().getList("id").size();
		Assert.assertEquals(numberOfEmployees, 4);

		response = updateEmployee("5");
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(employeeName, "Tom");
		Assert.assertNotEquals(response.jsonPath().getString("firstName"), "Johny");

		response = getEmployee("5");
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(employeeName, "Tom");

		response = deleteEmployee("5");
		Assert.assertEquals(response.statusCode(), 200);
		response = getEmployee("5");
		Assert.assertNotEquals(response.jsonPath().getString("firstName"), "Tom");

		response = getEmployee("5");
		Assert.assertEquals(response.statusCode(), 400);

		response = getAllEmployee();
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(numberOfEmployees, 3);
	}

}
