package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

// 문자를 숫자로 만드는 컨버터
@Slf4j
public class StringToIntegerConverter implements Converter<String, Integer> {
    // String Integer 로 변환하기 때문에 소스가 String 이 된다.
    @Override
    public Integer convert(String source) {
        log.info("convert source={}", source);
        return Integer.valueOf(source);
    }
}
