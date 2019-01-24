package org.srinivas.siteworks.test.integration;
import org.junit.Ignore;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static play.test.Helpers.*;

public class IntegrationTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }
    
    
    @Ignore
    @Test
    public void testList() {
    
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/change/optimal?pence=576");

        Result result = route(app, request);
        final String body = contentAsString(result);
        assertThat(body, containsString("value"));
    }

  

}
