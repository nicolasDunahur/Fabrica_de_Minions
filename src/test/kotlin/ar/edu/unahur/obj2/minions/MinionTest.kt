package ar.edu.unahur.obj2.minions

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MinionTest : DescribeSpec({

  describe("minion  come fruta para recuperar energia") {

    val empleado2 = Biclope(2)
    val empleado1 = Ciclople(1)

    it("empleado come uva "){
      empleado1.comerFruta(Uva)
      empleado2.comerFruta(Uva)

      empleado1.estamina.shouldBe(1)
      empleado2.estamina.shouldBe(1)
    }
    it("empleado come manzana"){
      empleado1.comerFruta(Manzana)
      empleado2.comerFruta(Manzana)

      empleado1.estamina.shouldBe(5)
      empleado2.estamina.shouldBe(5)
    }
    it("empleado como bananas"){
      empleado1.comerFruta(Banana)
      empleado2.comerFruta(Banana)

      empleado1.estamina.shouldBe(100)
      empleado2.estamina.shouldBe(10)
    }


  }
})
