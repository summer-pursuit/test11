package main.java.com.mywebserver;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.OutputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 * @author 29753
 */
public class DynamicContentHandler {
    public void handle(HttpRequest request, HttpResponse response, OutputStream outputStream) {
        String scriptPath = "web" + request.getPath();
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("groovy");

        if (engine == null) {
            response.setStatusCode(500);
            response.setBody("Script engine not found");
            response.send(outputStream);
            return;
        }

        try {
            engine.eval(new FileReader(scriptPath));
            Object result = engine.get("output");

            if (result == null) {
                response.setStatusCode(500);
                response.setBody("Script did not produce any output");
            } else {
                response.setStatusCode(200);
                response.addHeader("Content-Type", "text/html");
                response.setBody(result.toString());
            }
            response.send(outputStream);
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
            response.setStatusCode(500);
            response.setBody("Internal Server Error");
            response.send(outputStream);
        }
    }
}