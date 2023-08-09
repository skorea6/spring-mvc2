package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode // a와 b가 IpPort 객체일때, a와 b의 모든 필드의 값이 같다면 a.equals(b) 의 결과가 참이 된다.
public class IpPort {
    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
