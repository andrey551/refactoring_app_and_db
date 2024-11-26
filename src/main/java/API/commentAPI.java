
package API;

import Database.CommentTableRemote;
import Database.LocationTableRemote;
import Model.Comment;
import Raw.RawAccount;
import Raw.RawComment;
import Raw.WrapComment;
import Raw.coordinate;
import Utils.JSONBuilder;
import Utils.jwtHandler;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Dau Cong Tuan Anh
 */
@Path("/comments")
public class commentAPI {
    
    @EJB
    CommentTableRemote commentTableRemote;
    
    @EJB
    LocationTableRemote locationTableRemote ;
    
    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Path("/")
    public Response getComment(@Context HttpHeaders httpHeaders, coordinate coor) {
        List<String> headerList = httpHeaders.getRequestHeader("Authorization");
        if(headerList.isEmpty()) {
            return Response.status(401).build();
        }

        RawAccount ret = jwtHandler.verify(headerList.get(0));
        if(ret == null)
            return Response.status(400).entity("Session timeout!").build();
        Long locationId = locationTableRemote.getIdByCoordinate(coor);
        if(locationId != null) {
            List<RawComment> listComment = commentTableRemote.getCommentsByHospitalId(locationId);
            return Response
                    .status(200)
                    .entity(JSONBuilder.commentsJson(listComment))
                    .build();
        }

        return Response.status(403).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
    @Path("/addComment")
    public Response addComment(@Context HttpHeaders httpHeaders, WrapComment wrap) {
        coordinate coor = wrap.getCoor();
        Comment comment = wrap.getComment();
        System.out.println(coor.toString());
        System.out.println(comment.toString());
        List<String> headerList = httpHeaders.getRequestHeader("Authorization");
        if(headerList.isEmpty()) {
            return Response.status(401).build();
        }

        RawAccount ret = jwtHandler.verify(headerList.get(0));
        if(ret == null)
            return Response.status(400).entity("Session timeout!").build();
        Long locationId = locationTableRemote.getIdByCoordinate(coor);
        if(locationId != null) {
            comment.setUser_id(ret.getId());
            comment.setLocation_id(locationId);
            if(commentTableRemote.addComment(comment))
                return Response.status(200).build();
        }
        return Response.status(403).build();
    }

}
