package serializer_demo.protostuff;
import ch.qos.logback.classic.Logger;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.LoggerFactory;

public class ProtostuffDemo {
    /** 日志 */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ProtostuffDemo.class);
    public static void main(String[] args) {
        byte[] encode = encode();
        decode(encode);
    }

    /**
     * 编码
     * @return byte[] 编码的数据
     */
    private static byte[] encode() {
        // 创建一个 Person 对象并设置其属性
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(30);
        // 分配一个LinkedBuffer，用于序列化过程中的临时存储
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        // 使用ProtostuffIOUtil将Person对象序列化为字节数组
        byte[] serializedData = ProtostuffIOUtil.toByteArray(person, RuntimeSchema.getSchema(Person.class), buffer);
        // 释放LinkedBuffer
        buffer.clear();
        return serializedData;
    }

    /**
     * 解码
     * @param serializedData 编码数据
     */
    private static void decode(byte[] serializedData) {
        // 创建一个新的Person对象，用于接收反序列化后的数据
        Person deserializedPerson = new Person();
        // 使用ProtostuffIOUtil将字节数组反序列化为Person对象
        ProtostuffIOUtil.mergeFrom(serializedData, deserializedPerson, RuntimeSchema.getSchema(Person.class));
        // 此时，deserializedPerson对象应该与原始person对象具有相同的属性值
        logger.info("deserializedPerson:[{}]", deserializedPerson.toString());
    }
}
