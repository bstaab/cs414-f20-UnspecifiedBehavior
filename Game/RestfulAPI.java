package Game;

import org.apache.log4j.BasicConfigurator;
import spark.Request;
import spark.Spark;
import spark.Response;
import java.util.HashMap;
import com.google.gson.Gson;


public class RestfulAPI {
    private ChessMatch match;
        public static void main(String[] args){
            new RestfulAPI();
        }
        public RestfulAPI(){
            BasicConfigurator.configure();
            configureServer();
            HashMap<String, String> map=new HashMap<>();
            this.match=new ChessMatch();
            processRestfulAPIRequest();
        }

        private static void configureServer(){
            Spark.port(8080);
        }

        private void processRestfulAPIRequest(){
            //Spark.get("/", this::echoRequest);
            Spark.post("/api/move", this::moveRequest);
            Spark.post("/api/match", this::matchRequest);
        }

        private String matchRequest(Request request, Response response){
            response.type("application/json");
            response.header("Access-Control-Allow-Origin", "*");
            User user = new Gson().fromJson(request.body(), User.class);
            this.match=new ChessMatch();
            match.setUser(user);
            response.status(200);
            return matchToJson(request, user);
        }

        private String moveRequest(Request request, Response response){
            response.type("application/json");
            response.header("Access-Control-Allow-Origin", "*");
            Move myMove = new Gson().fromJson(request.body(), Move.class);
            try{
                match.board.move(myMove.getFrom(), myMove.getTo());
            }catch(IllegalMoveException e){
                e.printStackTrace();
                response.status(400);//move was fucked
                return "bad move";
            }
            match.addMove(myMove);
            //change move to user.move at some point
            response.status(200); //ok
            return moveToJson(request, myMove);
        }

        private String echoRequest(Request request, Response response){
            response.type("application/json");
            response.header("Access-Control-Allow-Origin", "*");
            response.status(200); //ok
            return toJson(request);
        }

        private String moveToJson(Request request, Move move){
            return"{\n"
                    + "\"from\":\"" + move.getFrom() + "\",\n"
                    + "\"to\":\"" + move.getTo() + "\",\n"
                    + "}";
        }

    private String matchToJson(Request request, User user){
        return"{\n"
                + "\"name\":\"" + user.name + "\",\n"
                + "\"password\":\"" + user.password + "\",\n"
                + "}";
    }

        private String toJson(Request request){
            return"{\n"
                    + "\"attributes\":\""+request.attributes() + "\",\n"
                    + "\"body\":\"" + request.body() + "\",\n"
                    + "\"contentLength\":\"" + request.contentLength() + "\",\n"
                    + "\"contentPath\":\"" + request.contextPath() + "\",\n"
                    + "\"cookies\":\"" + request.cookies() + "\",\n"
                    + "\"headers\":\"" + request.headers() + "\",\n"
                    + "\"host\":\"" + request.host() + "\",\n"
                    + "\"ip\":\"" + request.ip() + "\",\n"
                    + "\"params\":\"" + request.params() + "\",\n"
                    + "\"pathInfo\":\"" + request.pathInfo() + "\",\n"
                    + "\"serverPort\":\"" + request.port() + "\",\n"
                    + "\"protocol\":\"" + request.protocol() + "\",\n"
                    + "\"queryParams\":\"" + request.queryParams() + "\",\n"
                    + "\"requestMethod\":\"" + request.requestMethod() + "\",\n"
                    + "\"scheme\":\"" + request.scheme() + "\",\n"
                    + "\"servletPath\":\"" + request.servletPath() + "\",\n"
                    + "\"session\":\"" + request.session() + "\",\n"
                    + "\"uri()\":\"" + request.uri() + "\",\n"
                    + "\"url()\":\"" + request.url() + "\",\n"
                    + "\"userAgent\":\"" + request.userAgent() + "\",\n"
                    + "}";
        }
}
