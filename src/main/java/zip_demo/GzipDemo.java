package zip_demo;

import java.nio.charset.StandardCharsets;

import ch.qos.logback.classic.Logger;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.json.JSONUtil;
import org.slf4j.LoggerFactory;

public class GzipDemo {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(GzipDemo.class);

    public static void main(String[] args) {
        gzip();
        unzip();
    }

    /**
     * gzip压缩
     */
    public static void gzip() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            builder.append("hello_");
        }
        String string = builder.toString();
        byte[] bs = string.getBytes(StandardCharsets.UTF_8);
        logger.info("压缩前长度:{}", bs.length);
        bs = ZipUtil.gzip(bs);
        logger.info("压缩后长度:{}", bs.length);
        // 压缩前长度:6000000    压缩后长度:8778
    }

    public static void unzip(){
        String str = "H4sIAAAAAAAAAO1XXW+bMBT9LzxnEoZAQp62rk1brflQGm1aq2ry4A68gGHGsLKq/302mI+htHL3nLwEn3OvDz42HPFk5MBKYDck58bi/mFifCfhDkKS0nPM8QpnxuLpeaKqeshApvghedVQ14GxaLDBFBI0J0YMJcQfys84LkBUTYwfKfNBkQEpSd7Xit5LlhZZPXwWyiyNYaA7M23XcxwLOWjumA5yp56EZZVsOUJPDB8zAGYsjPVmffHeNJpJbznmRT5EI6HwDsn/rSwXVxySbHe0VjJXqr65bnsSnO2rTCzUuN3vNuvLb9vN9XpfNwnqYxpAvVCgHNhd1ZSKMccsBL5qm2kRxwOwa0vSUt4N43uS9MhG7EAHEJpn4PNj2F1KW9+rAtNzgukOSlJCV/izOALmHFdycEMSwmukNtBPmeIJDWO4KuiNmHT3MrXCj6+wn0gcX1BIqqONr7CSWqU053IHXmgd8n+ilIZfmz55P8qhEgew4RGwpTyeytsvxVma5/0dt73/oglmvwqALWY4UbuZH9SJddRzAo+c4SV5PCtCVdfucX6Qe+LUz44cNSfAEQvxIwiKuNsEPjwq60JMce88iGdEtFqaEtbbJZCpNGxNDfs/NOp1WJpWWSOrrDesw9L0yhp5padhtRpaXlkjr/Q07FoDaXqFRl4hTQ2zVdFyC43c0lNxexUtv9DILz0VZCkZV9Myd2SZqyejJLT8ckd+6UlYDzIRMefYP+xrXL3Mh1CXKH5MRMhscbUkYdTmhehvM9L0ppbtzeZTb3YsQgf0KUJPEXqK0FOEniL0FKGnCJUT5G2aLa8vr/bvbZFMfoRpqF74aGbPptZ0Lj+Izbb6ggY965nqJ4IA08MOfmMWdJ/bAcS4WmHuR12ARM27eJ35tyTJYuhqe+IcOCZxRzDIxVKkP52qY0/nzsxz3Oe/g4qg6fkPAAA=";
        String jsonPrettyStr = JSONUtil.toJsonPrettyStr(decode(str));
        logger.info("jsonPrettyStr:{}", jsonPrettyStr);
    }
    /**
     * 解压数据
     */
    protected static String decode(String data) {
        if (Base64.isBase64(data)) {
            byte[] bs = Base64.decode(data);
            data = ZipUtil.unGzip(bs, CharsetUtil.UTF_8);
        }
        return data;
    }


    /***
     * 对数据进行Base64处理的作用主要体现在以下几个方面：
     *
     * 数据传输：在网络通信中，某些协议或接口可能只支持文本数据的传输，而无法直接处理二进制数据。Base64编码可以将二进制数据转换为文本数据，从而方便地在这些协议或接口中进行传输。这种转换确保了数据的完整性和可靠性，避免了在传输过程中可能出现的损坏或误解。
     * 数据存储：在某些场景下，需要将二进制数据存储到数据库或其他存储介质中，但这些介质可能只支持文本数据的存储。通过Base64编码，可以将二进制数据转换为文本数据，从而方便地存储到这些介质中。这有助于减少存储空间的占用，并提高数据的可读性和可维护性。
     * 数据兼容性：Base64编码是一种通用的编码方式，得到了广泛的支持和应用。在不同的编程语言和数据库系统中，都可以方便地进行Base64编码和解码操作。这使得Base64编码成为一种理想的数据交换格式，可以方便地在不同的系统之间进行数据传输和共享。
     * 数据安全性（有限）：虽然Base64编码并不提供真正的加密功能，但它可以将原始数据转换为一种不可读的格式，从而在一定程度上增加数据的安全性。然而，需要注意的是，Base64编码很容易被解码，因此它并不适合用于保护敏感信息的传输。对于需要高安全性保护的数据，应该使用专门的加密算法进行加密处理。
     * 数据简化处理：在前端开发中，经常需要将图片或其他资源以文本的形式嵌入到HTML或CSS中。通过使用Base64编码，可以将这些资源转换为文本数据，从而实现内嵌显示，减少了对外部资源的依赖。这有助于简化开发过程，并提高网页的加载速度和性能。
     */
}
