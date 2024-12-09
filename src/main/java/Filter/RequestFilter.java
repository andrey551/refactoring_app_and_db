package Filter;

import Database.AccountTableRemote;
import jakarta.annotation.Priority;
import jakarta.ejb.EJB;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Priority(value=30)
public class RequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String token = containerRequestContext.getHeaderString("Authorization");
//        if(jwtHandler.verify(token) == null) {
//            containerRequestContext.abortWith(
//                    Response.status(Response.Status.UNAUTHORIZED).build()
//            );
//        }
    }
}
