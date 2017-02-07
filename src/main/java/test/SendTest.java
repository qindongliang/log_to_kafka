package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by QinDongLiang on 2017/1/9.
 */
public class SendTest {

    static Logger logger= LoggerFactory.getLogger(SendTest.class);

    public static void main(String[] args) {

        for(int i=500;i<=510;i++) {
            logger.info("hadoop数据：{}",i);
        }


    }
}
