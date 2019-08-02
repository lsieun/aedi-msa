package lsieun.aedi.b;

import java.io.IOException;

import com.jetbrains.ls.responses.PingResponse;
import com.jetbrains.ls.responses.ResponseCode;

public class B_PingResponse {
    public static PingResponse a(String var0, String var1, char var2, char var3, int var4) throws IOException {
        new Exception().printStackTrace();

        PingResponse response = new PingResponse();
        response.setResponseCode(ResponseCode.OK);
        return response;
    }
}
