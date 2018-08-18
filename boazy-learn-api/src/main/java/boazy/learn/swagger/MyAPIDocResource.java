package boazy.learn.swagger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/18
 */
@Path("my-api-doc-resource")
public interface MyAPIDocResource {

    @POST
    @Path("list-path")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({"application/json;charset=UTF-8"})
    MyResponse<List<String>> listPath(MyRequest<String> parentPathReq);

}
