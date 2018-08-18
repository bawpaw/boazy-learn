package boazy.learn.swagger.impl;

import boazy.learn.swagger.MyAPIDocResource;
import boazy.learn.swagger.MyRequest;
import boazy.learn.swagger.MyResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/18
 */
@OpenAPIDefinition(info = @Info(title = "My API Docs", description = "REST API", version = "1.0.0"
                , license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html"))
        , servers = @Server(url = "http://127.0.0.1:9001/rest", description = "demo url")
        , tags = @Tag(name = "MyTag", description = "demo tag")
)
public class MyAPIDocResourceDocImpl implements MyAPIDocResource {

    @Operation(description = "查询路径集", tags = {"MyTag"})
    @Override
    public MyResponse<List<String>> listPath(MyRequest<String> parentPathReq) {
        return null;
    }

}
