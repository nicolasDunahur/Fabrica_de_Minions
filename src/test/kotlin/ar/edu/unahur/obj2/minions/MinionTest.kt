package ar.edu.unahur.obj2.minions

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class MinionTest : DescribeSpec({

  describe("minion  come fruta para recuperar energia") {


    Laboratorio.rolAsignado = Obrero
    val empleadoBiclope = Biclope(Obrero,0)
    val empleadoCiclope = Ciclople(Obrero,50)


    it("empleado come uva ") {
      empleadoCiclope.comerFruta(Uva)
      empleadoBiclope.comerFruta(Uva)

      empleadoCiclope.estamina.shouldBe(51)
      empleadoBiclope.estamina.shouldBe(1)
    }
    it("empleado come manzana") {
      empleadoCiclope.comerFruta(Manzana)
      empleadoBiclope.comerFruta(Manzana)

      empleadoCiclope.estamina.shouldBe(55)
      empleadoBiclope.estamina.shouldBe(5)
    }
    it("empleado como bananas") {
      empleadoCiclope.comerFruta(Banana)
      empleadoBiclope.comerFruta(Banana)

      empleadoCiclope.estamina.shouldBe(150)
      empleadoBiclope.estamina.shouldBe(10)
    }
    describe("experiencia que tiene los empleados por realizar tareas"){

      val maquinaQuimica = Maquina(5)
      val arreglarQuimica = ArreglarMaquina(mutableListOf("destornillador"),maquinaQuimica)

      repeat(2){
        Laboratorio.enviarTarea(empleadoCiclope,arreglarQuimica)
      }

      it("total de experiencia adquirida por el empleadoBiclope"){
        empleadoBiclope.experiencia().shouldBe(0)

      }
      it("experiencia del ciclope, realizo 2 tareas de reparacion"){
        empleadoCiclope.experiencia().shouldBe(40)

      }
    }

    describe("si lo empleados pueden realizar las tareas asignadas"){
      val pc = Maquina(5)
      val repararPc = ArreglarMaquina(mutableListOf("cd"),pc)

      it("empleado ciclope puede realizar tarea de reparacion "){
        repararPc.puedeSerRealizada(empleadoCiclope).shouldBeFalse()
      }

      val torno = Maquina(25)
      val repararTorno = ArreglarMaquina(mutableListOf(),torno)

      it("empleadoCiclope puede realizar tarea de raparacion "){
        repararTorno.puedeSerRealizada(empleadoCiclope).shouldBeTrue()
      }
    }
    describe("si el empleado puede defender el sector"){

    }

  }

  describe("Fuerza - ejemplo"){
    Laboratorio.rolAsignado = Soldado
    val sector1 = Sector(true,true,4)

    it("un Bíclope Soldado con 10 de estamina y 4 de daño extra"){
      val mimBiclople = Biclope(Soldado,10)
      mimBiclople.defender(sector1)
      mimBiclople.defender(sector1)
      mimBiclople.fuerza().shouldBe(11)
    }

    it("Cíclope Soldado estamina 10, daño extra 4") {

      val minCiclo = Ciclople(Soldado,10)
      minCiclo.defender(sector1)
      minCiclo.defender(sector1)
      minCiclo.fuerza().shouldBe(5)
    }


  }


})
