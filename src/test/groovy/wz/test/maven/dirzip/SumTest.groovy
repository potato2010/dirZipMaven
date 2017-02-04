package wz.test.maven.dirzip

import spock.lang.Specification

/**
 * Created by admin on 2017/2/4.
 */
class SumTest extends Specification {
    void setup() {

    }

    void cleanup() {

    }

    def sum = new Sum()
    def "sum should return param1+param2"() {
        print('run-------------------------')
        expect:
        sum.sum(1,1) == 2
    }
}
