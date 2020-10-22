package ar.edu.unahur.obj2.minions

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe

class MinionTest : DescribeSpec({

  describe("minion  come fruta para recuperar energia") {

    val laboratorio = Laboratorio()


    val empleadoBiclope = Biclope(Peon, 0)
    val empleadoCiclope = Ciclople(Peon, 50)


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
    describe("experiencia que tiene los empleados por realizar tareas") {

      val maquinaQuimica = ArreglarMaquina(mutableListOf("destornillador"), 5)

      repeat(2) {
        laboratorio.enviarTarea(empleadoCiclope, maquinaQuimica)
      }

      it("total de experiencia adquirida por el empleadoBiclope") {
        empleadoBiclope.experiencia().shouldBe(0)

      }
      it("experiencia del ciclope, realizo 2 tareas de reparacion") {
        empleadoCiclope.experiencia().shouldBe(40)

      }
    }
    describe(" fuerza de los empleados"){

      val ciclopeObrero = Ciclople(Peon,10)

      it("fuerza de los empleados con rol obrero"){

        empleadoBiclope.fuerza().shouldBe(2)
        ciclopeObrero.fuerza().shouldBe(3)
      }
      it("fuerza de los empleados con rol Soldado"){
        laboratorio.asignarRol(ciclopeObrero,Soldado)
        Soldado.danioExtra = 4

        ciclopeObrero.fuerza().shouldBe(5)


      }
    }

    describe("si lo empleados pueden realizar las tareas asignadas") {
      val pc = ArreglarMaquina(mutableListOf("cd", "tester"), 5)

      it("empleado ciclope no puede realizar tarea de reparacion, no tiene la herramientas necesarias ") {
        empleadoCiclope.puedeRealizarTarea(pc).shouldBeFalse()
      }

      val maquina = ArreglarMaquina(mutableListOf(), 25)

      it("empleadoCiclope puede realizar tarea de raparacion ") {

        empleadoCiclope.puedeRealizarTarea(maquina).shouldBeTrue()
      }

      describe("si el empleado puede defender el sector") {
        val sectorA = Sector(true,false,20)
        val defensa = DefenderSector(sectorA)
        val obreroBiclope = Biclope(Peon, 10)
        val obreroCicople = Ciclople(Peon,100)

        describe(" el empleado no puede realizar la tarea ,el empleado ahora tiene rol limpiador"){

          laboratorio.asignarRol(empleadoBiclope,Limpiador)

          it("no puede defender"){
            empleadoBiclope.puedeRealizarTarea(defensa).shouldBeFalse()
          }
        }
        describe("empleados con rol de Obreros"){

          it("pueden defender el sector"){


            defensa.puedeSerRealizada(obreroBiclope).shouldBeFalse()

            defensa.puedeSerRealizada(obreroCicople).shouldBeTrue()
          }
        }
        describe("empleados con rol soldado"){
          laboratorio.asignarRol(empleadoBiclope,Soldado)
          laboratorio.asignarRol(empleadoCiclope,Soldado)

          it( "el empleado no pude realizar la tarea de defender el sector"){
            obreroBiclope.puedeRealizarTarea(defensa).shouldBeFalse()
          }
          it("el empleado si puede realizar la tarea de defenser el sector "){

            obreroCicople.puedeRealizarTarea(defensa).shouldBeTrue()
          }
        }
      }

    }

  }
  describe("6. Laboratorio - esta en orden"){
    val sector1 = Sector(true,true,0)
    val sector2 = Sector(true,true,0)
    val sector3 = Sector(true,true,0)

    val obreroBiclope = Biclope(Peon, 10)
    val obreroCicople = Ciclople(Peon,100)

    val laboratorioX= Laboratorio()
    laboratorioX.sectores = mutableListOf<Sector>(sector1,sector2,sector3)
    laboratorioX.empleados = mutableListOf<Minion>(obreroBiclope,obreroCicople)

    it("Todes contentes, sin amenazas y limpio"){
      laboratorioX.enOrden().shouldBeTrue()
    }
    it("Infeliz, sin amenazas y limpio"){
      obreroBiclope.estamina = 8
      laboratorioX.enOrden().shouldBeFalse()
    }
  }
  describe("7. Laboratorio - Jornada Laboral"){
    val sector1 = Sector(true,false,4)
    val sector2 = Sector(true,false,4)
    val sector3 = Sector(true,false,0)

    val obreroBiclope = Biclope(Peon, 10)
    val obreroCicople = Ciclople(Peon,100)

    val defender1 = DefenderSector(sector1)
    val defender2 = DefenderSector(sector2)
    val repararPc = ArreglarMaquina(mutableListOf("cd", "tester"), 5)
    val limpiar3 = LimpiarSector(sector3)

    val laboratorioX= Laboratorio()
    laboratorioX.sectores = mutableListOf<Sector>(sector1,sector2,sector3)
    laboratorioX.empleados = mutableListOf<Minion>(obreroBiclope,obreroCicople)
    laboratorioX.tareasPendientes = mutableListOf<Tarea>(defender1,defender2,repararPc,limpiar3)

    it("Realizar todas las tareas"){
      laboratorioX.jonadaLaboral()
      laboratorioX.tareasPendientes.shouldBeEmpty()


    }
    it("No se puede realizar debido a que no hay tareas"){
      val laboratorioY = Laboratorio()
      obreroBiclope.estamina = 8
      
    }
  }



})
