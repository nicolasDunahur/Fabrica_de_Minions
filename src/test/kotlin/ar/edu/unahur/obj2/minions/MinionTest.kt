package ar.edu.unahur.obj2.minions

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.shouldBe
import java.lang.ArithmeticException

class MinionTest : DescribeSpec({

  describe("Un minion biciclope  come fruta para recuperar energia") {

    val empleado2 = Biclope(2,5)

    empleado2.estamina.shouldBe(5)
    empleado2.comerFruta(Banana)
    empleado2.estamina.shouldBe(105)





  }
})
