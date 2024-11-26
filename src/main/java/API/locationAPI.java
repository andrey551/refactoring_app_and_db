package API;

import Database.*;
import Model.*;
import Raw.*;
import Utils.jwtHandler;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;

import java.util.List;

import static Utils.JSONBuilder.SpecsJson;
import static Utils.JSONBuilder.locationsJson;

@Path("/locations")
public class locationAPI {
    @EJB
    LocationTableRemote locationTableRemote ;


    @EJB
    UserTableRemote userTableRemote;

    @EJB
    SpecializationTableRemote specializationTableRemote;
    
    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Path("/")
    public Response getLocation(
                                @Context HttpHeaders httpHeaders, 
                                RawLocationReq req) {
        
        List<String> headerList = httpHeaders.getRequestHeader("Authorization");
        
        if(headerList.isEmpty()) {
            return Response.status(401).build();
        }

        RawAccount ret = jwtHandler.verify(headerList.get(0));
        
        if(ret == null)
            return Response.status(400).entity("Session timeout!").build();
        
        User user = userTableRemote.getUserByAccountId(ret.getId());
        
        if(user != null) {
            List<Location> list = locationTableRemote.getLocs(req.getType());
            
            System.out.println(list.toString());
            
            return Response.status(200).entity(locationsJson(list)).build();
        }
        
        return Response.status(403).build();
    }

    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Path("/departments")
    public Response getDepartment(
                                    @Context HttpHeaders httpHeaders, 
                                    coordinate coor) {
        
        List<String> headerList = httpHeaders.getRequestHeader("Authorization");
        
        if(headerList.isEmpty()) {
            return Response.status(401).build();
        }

        RawAccount ret = jwtHandler.verify(headerList.get(0));
        
        if(ret == null)
            return Response.status(400).entity("Session timeout!").build();
        
        Long locationId = locationTableRemote.getIdByCoordinate(coor);
        
        if(locationId != null) {
            List<Specialization> listSpec = 
                    specializationTableRemote
                            .getSpecializationsByHospitalId(locationId);
            
            return Response.status(200).entity(SpecsJson(listSpec)).build();
        }
        return Response.status(403).build();
    }
}