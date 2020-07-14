package com.macpi.test

import spock.lang.Specification
import spock.lang.Unroll

class GroovyCountingService extends Specification {

    CountingService countingService = new CountingService()

    @Unroll
    def "multiply #x times #y should return #result"() {
        expect:
        countingService.multiply(x, y) == result
        where:
        x  | y  || result
        1  | 2  || 2
        22 | 4  || 88
        10 | 10 || 10
    }
}
